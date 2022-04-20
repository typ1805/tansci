package com.tansci.service.impl.payment;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tansci.common.constant.Constants;
import com.tansci.common.constant.PayEnum;
import com.tansci.common.exception.BusinessException;
import com.tansci.config.PaymentConfig;
import com.tansci.domain.payment.dto.PaymentDto;
import com.tansci.domain.payment.vo.PaymentVo;
import com.tansci.service.payment.PaymentService;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.*;
import com.wechat.pay.contrib.apache.httpclient.notification.Notification;
import com.wechat.pay.contrib.apache.httpclient.notification.NotificationHandler;
import com.wechat.pay.contrib.apache.httpclient.notification.NotificationRequest;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @path：com.tansci.service.impl.payment.WxPaymentServiceImpl.java
 * @className：WxPaymentServiceImpl.java
 * @description：微信支付
 * @author：tanyp
 * @dateTime：2022/3/28 11:31
 * @editNote：
 */
@Slf4j
@Service
public class WxPaymentServiceImpl implements PaymentService {

    // 验签器
    private Verifier verifier;

    private static CloseableHttpClient httpClient = null;

    /**
     * @methodName：httpClient
     * @description：初始化httpClient
     * @author：tanyp
     * @dateTime：2022/3/28 11:51
     * @Params： []
     * @Return： org.apache.http.impl.client.CloseableHttpClient
     * @editNote：
     */
    public CloseableHttpClient httpClient() throws IOException {
        // 加载商户私钥（privateKey：私钥字符串）
        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(new ByteArrayInputStream(PaymentConfig.WX_PRIVATE_KEY.getBytes("utf-8")));

        // 加载平台证书（mchId：商户号,mchSerialNo：商户证书序列号,apiV3Key：V3密钥）
        AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
                new WechatPay2Credentials(PaymentConfig.WX_MCH_ID, new PrivateKeySigner(PaymentConfig.WX_MCH_SERIAL_NO, merchantPrivateKey)), PaymentConfig.WX_APIV3_KEY.getBytes("utf-8"));

        // 初始化httpClient
        return httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(PaymentConfig.WX_MCH_ID, PaymentConfig.WX_MCH_SERIAL_NO, merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier)).build();
    }

    /**
     * @methodName：after
     * @description：释放httpClient
     * @author：tanyp
     * @dateTime：2022/3/28 11:51
     * @Params： [httpClient]
     * @Return： void
     * @editNote：
     */
    public static void after(CloseableHttpClient httpClient) throws IOException {
        httpClient.close();
    }

    @Override
    public PaymentVo pay(PaymentDto dto) {
        PaymentVo paymentVo = PaymentVo.builder().orderId(dto.getOrderId()).build();
        try {
            // 请求body参数
            Map param = new HashMap();
            param.put("mchid", PaymentConfig.WX_MCH_ID);
            param.put("appid", PaymentConfig.WX_APP_ID);
            param.put("out_trade_no", dto.getOrderId());
            param.put("description", dto.getDescription());
            param.put("notify_url", PaymentConfig.WX_NNOTIFY_URL);
            Map amount = new HashMap();
            amount.put("total", dto.getAmount());
            amount.put("currency", "CNY");
            param.put("amount", amount);

            CloseableHttpClient httpClient = httpClient();
            HttpPost httpPost = new HttpPost();
            httpPost.setHeader("Accept", "application/json");
            StringEntity entity = null;

            CloseableHttpResponse response = null;
            Integer statusCode = null;

            try {
                switch (dto.getPayType()) {
                    case "WAP": // H5下单
                        Map sceneInfo = new HashMap();
                        sceneInfo.put("payer_client_ip", dto.getClientIp());
                        Map h5Info = new HashMap();
                        h5Info.put("type", "Wap");
                        sceneInfo.put("h5_info", h5Info);
                        param.put("scene_info", sceneInfo);

                        entity = new StringEntity(JSONObject.toJSONString(param));
                        entity.setContentType("application/json");
                        httpPost.setEntity(entity);
                        httpPost.setURI(URI.create("https://api.mch.weixin.qq.com/v3/pay/transactions/h5"));

                        // 完成签名并执行请求
                        response = httpClient.execute(httpPost);
                        statusCode = response.getStatusLine().getStatusCode();
                        if (Objects.equals(Constants.SUCCESS, statusCode)) {
                            log.info("=====WAP(H5)====请求支付接口成功，响应参数：{} " + EntityUtils.toString(response.getEntity()));
                            // 生成二维码
                            String bodyString = EntityUtils.toString(response.getEntity());
                            JSONObject jsonBody = JSONObject.parseObject(bodyString);

                            paymentVo.setHtml(jsonBody.get("h5_url").toString());
                            paymentVo.setMessage(PayEnum.WX_USERPAYING.getKey());
                            paymentVo.setState(PayEnum.WX_USERPAYING.getValue());
                        } else {
                            log.info("=====WAP(H5)====请求支付接口失败，状态：{}，响应参数：{} ", statusCode, EntityUtils.toString(response.getEntity()));
                            paymentVo.setMessage(PayEnum.WX_PAYERROR.getKey());
                            paymentVo.setState(PayEnum.WX_PAYERROR.getValue());
                        }
                        break;
                    case "NATIVE":
                        entity = new StringEntity(JSONObject.toJSONString(param));
                        entity.setContentType("application/json");
                        httpPost.setEntity(entity);
                        httpPost.setURI(URI.create("https://api.mch.weixin.qq.com/v3/pay/transactions/native"));

                        // 完成签名并执行请求
                        response = httpClient.execute(httpPost);
                        statusCode = response.getStatusLine().getStatusCode();
                        if (Objects.equals(Constants.SUCCESS, statusCode)) {
                            log.info("=====NATIVE====请求支付接口成功，响应参数：{} " + EntityUtils.toString(response.getEntity()));
                            // 生成二维码
                            String bodyString = EntityUtils.toString(response.getEntity());
                            JSONObject jsonBody = JSONObject.parseObject(bodyString);

                            paymentVo.setCodeUrl(jsonBody.get("code_url").toString());
                            paymentVo.setMessage(PayEnum.WX_USERPAYING.getKey());
                            paymentVo.setState(PayEnum.WX_USERPAYING.getValue());
                        } else {
                            log.info("=====NATIVE====请求支付接口失败，状态：{}，响应参数：{} ", statusCode, EntityUtils.toString(response.getEntity()));
                            paymentVo.setMessage(PayEnum.WX_PAYERROR.getKey());
                            paymentVo.setState(PayEnum.WX_PAYERROR.getValue());
                        }
                        break;
                    case "MICROPAY":
                        // v3 暂不支持 TODO

                        break;
                    case "JSAPI":
                        Map payer = new HashMap();
                        payer.put("openid", dto.getOpenId());
                        param.put("payer", payer);

                        entity = new StringEntity(JSONObject.toJSONString(param));
                        entity.setContentType("application/json");
                        httpPost.setEntity(entity);
                        httpPost.setURI(URI.create("https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi"));

                        // 完成签名并执行请求
                        response = httpClient.execute(httpPost);
                        statusCode = response.getStatusLine().getStatusCode();
                        if (Objects.equals(Constants.SUCCESS, statusCode)) {
                            log.info("=====JSAPI====请求支付接口成功，响应参数：{} " + EntityUtils.toString(response.getEntity()));
                            // 生成二维码
                            String bodyString = EntityUtils.toString(response.getEntity());
                            JSONObject jsonBody = JSONObject.parseObject(bodyString);

                            paymentVo.setPrepayId(jsonBody.get("prepay_id").toString());
                            paymentVo.setMessage(PayEnum.WX_USERPAYING.getKey());
                            paymentVo.setState(PayEnum.WX_USERPAYING.getValue());
                        } else {
                            log.info("=====JSAPI====请求支付接口失败，状态：{}，响应参数：{} ", statusCode, EntityUtils.toString(response.getEntity()));
                            paymentVo.setMessage(PayEnum.WX_PAYERROR.getKey());
                            paymentVo.setState(PayEnum.WX_PAYERROR.getValue());
                        }
                        break;
                    case "APP":
                        entity = new StringEntity(JSONObject.toJSONString(param));
                        entity.setContentType("application/json");
                        httpPost.setEntity(entity);
                        httpPost.setURI(URI.create("https://api.mch.weixin.qq.com/v3/pay/transactions/app"));

                        // 完成签名并执行请求
                        response = httpClient.execute(httpPost);
                        statusCode = response.getStatusLine().getStatusCode();
                        if (Objects.equals(Constants.SUCCESS, statusCode)) {
                            log.info("=====APP=====请求支付接口成功，响应参数：{} " + EntityUtils.toString(response.getEntity()));
                            String bodyString = EntityUtils.toString(response.getEntity());
                            JSONObject jsonBody = JSONObject.parseObject(bodyString);

                            paymentVo.setPrepayId(jsonBody.get("prepay_id").toString());
                            paymentVo.setMessage(PayEnum.WX_USERPAYING.getKey());
                            paymentVo.setState(PayEnum.WX_USERPAYING.getValue());
                        } else {
                            log.info("=====APP=====请求支付接口失败，状态：{}，响应参数：{} ", statusCode, EntityUtils.toString(response.getEntity()));
                            paymentVo.setMessage(PayEnum.WX_PAYERROR.getKey());
                            paymentVo.setState(PayEnum.WX_PAYERROR.getValue());
                        }
                        break;
                    default:
                        throw new BusinessException("不支持此支付类型");
                }
            } finally {
                if (Objects.nonNull(response)) {
                    response.close();
                }
                if (Objects.nonNull(httpClient)) {
                    after(httpClient);
                }
            }
        } catch (Exception e) {
            paymentVo.setMessage(PayEnum.WX_NOTPAY.getKey());
            paymentVo.setState(PayEnum.WX_NOTPAY.getValue());
            log.error("请求微信支付异常，异常信息:{}", e);
        }
        return paymentVo;
    }

    @Override
    public PaymentVo query(PaymentDto dto) {
        PaymentVo paymentVo = PaymentVo.builder().orderId(dto.getOrderId()).build();
        try {
            URIBuilder uriBuilder = new URIBuilder("https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/" + dto.getOrderId() + "?mchid=" + PaymentConfig.WX_MCH_ID);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.addHeader("Accept", "application/json");

            CloseableHttpClient httpClient = httpClient();
            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(httpGet);
                int statusCode = response.getStatusLine().getStatusCode();
                if (Objects.equals(Constants.SUCCESS, statusCode)) {
                    log.info("请求查询订单接口成功，响应参数：{} " + EntityUtils.toString(response.getEntity()));
                    String bodyString = EntityUtils.toString(response.getEntity());
                    JSONObject jsonBody = JSONObject.parseObject(bodyString);

                    paymentVo.setPaymentId(jsonBody.getString("transaction_id"));
                    paymentVo.setPayTime(jsonBody.getString("success_time"));
                    paymentVo.setMessage(jsonBody.getString("trade_state_desc"));
                    paymentVo.setState(PayEnum.getVlaueByGroup(jsonBody.getString("trade_state"), "pay_wx_status"));
                } else {
                    log.info("请求查询订单接口失败，状态：{}，响应参数：{} ", statusCode, EntityUtils.toString(response.getEntity()));
                }
            } finally {
                if (Objects.nonNull(response)) {
                    response.close();
                }
                if (Objects.nonNull(httpClient)) {
                    after(httpClient);
                }
            }
        } catch (Exception e) {
            paymentVo.setMessage(PayEnum.WX_NOTPAY.getKey());
            paymentVo.setState(PayEnum.WX_NOTPAY.getValue());
            log.error("请求微信查询订单异常，异常信息:{}", e);
        }
        return paymentVo;
    }

    @Override
    public PaymentVo close(PaymentDto dto) {
        PaymentVo paymentVo = PaymentVo.builder().orderId(dto.getOrderId()).build();
        try {
            HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/" + dto.getOrderId() + "/close");
            httpPost.addHeader("Accept", "application/json");
            httpPost.addHeader("Content-type", "application/json; charset=utf-8");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(bos, objectMapper.createObjectNode().put("mchid", PaymentConfig.WX_MCH_ID));
            httpPost.setEntity(new StringEntity(bos.toString("UTF-8"), "UTF-8"));

            CloseableHttpClient httpClient = httpClient();
            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(httpPost);
                int statusCode = response.getStatusLine().getStatusCode();
                if (Objects.equals(Constants.SUCCESS, statusCode)) {
                    log.info("请求关闭订单接口成功，响应参数：{} " + EntityUtils.toString(response.getEntity()));
                    // 无返回值
                    paymentVo.setMessage(PayEnum.WX_CLOSED.getKey());
                    paymentVo.setState(PayEnum.WX_CLOSED.getValue());
                } else {
                    log.info("请求关闭订单接口失败，状态：{}，响应参数：{} ", statusCode, EntityUtils.toString(response.getEntity()));
                }
            } finally {
                if (Objects.nonNull(response)) {
                    response.close();
                }
                if (Objects.nonNull(httpClient)) {
                    after(httpClient);
                }
            }
        } catch (Exception e) {
            paymentVo.setMessage(PayEnum.WX_NOTPAY.getKey());
            paymentVo.setState(PayEnum.WX_NOTPAY.getValue());
            log.error("请求微信关闭订单异常，异常信息:{}", e);
        }
        return paymentVo;
    }

    @Override
    public PaymentVo refund(PaymentDto dto) {
        PaymentVo paymentVo = PaymentVo.builder().orderId(dto.getOrderId()).refundId(dto.getRefundId()).build();
        try {
            Map param = new HashMap();
            param.put("out_trade_no", dto.getOrderId());
            param.put("out_refund_no", dto.getRefundId());
            Map account = new HashMap();
            account.put("refund", dto.getAmount());
            account.put("total", dto.getAmount());
            account.put("currency", "CNY");
            param.put("funds_account", account);

            HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/refund/domestic/refunds");
            httpPost.addHeader("Accept", "application/json");
            httpPost.addHeader("Content-type", "application/json; charset=utf-8");
            StringEntity entity = new StringEntity(JSONObject.toJSONString(param));
            entity.setContentType("application/json");
            httpPost.setEntity(entity);

            CloseableHttpClient httpClient = httpClient();
            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(httpPost);
                int statusCode = response.getStatusLine().getStatusCode();
                if (Objects.equals(Constants.SUCCESS, statusCode)) {
                    log.info("请求退款接口成功，响应参数：{} " + EntityUtils.toString(response.getEntity()));
                    String bodyString = EntityUtils.toString(response.getEntity());
                    JSONObject jsonBody = JSONObject.parseObject(bodyString);

                    paymentVo.setPayTime(jsonBody.getString("success_time"));
                    paymentVo.setRefundNo(jsonBody.getString("refund_id"));
                    paymentVo.setMessage(PayEnum.WX_PROCESSING.getKey());
                    paymentVo.setState(PayEnum.getVlaueByGroup(jsonBody.getString("status"), "pay_wx_status"));
                } else {
                    log.info("请求退款接口失败，状态：{}，响应参数：{} ", statusCode, EntityUtils.toString(response.getEntity()));
                }
            } finally {
                if (Objects.nonNull(response)) {
                    response.close();
                }
                if (Objects.nonNull(httpClient)) {
                    after(httpClient);
                }
            }
        } catch (Exception e) {
            paymentVo.setMessage(PayEnum.WX_NOTPAY.getKey());
            paymentVo.setState(PayEnum.WX_NOTPAY.getValue());
            log.error("请求微信退款订单异常，异常信息:{}", e);
        }
        return paymentVo;
    }

    @Override
    public void payNotify(HttpServletRequest request, HttpServletResponse response) {
        Notification notification = null;
        try {
            // 构建request，传入必要参数
            NotificationRequest _request = new NotificationRequest.Builder()
//                    .withSerialNumber(wechatPaySerial) todo
                    .withNonce(request.getHeader("Wechatpay-Nonce"))
                    .withTimestamp(request.getHeader("Wechatpay-Timestamp"))
                    .withSignature(request.getHeader("Wechatpay-Signature"))
//                    .withBody(body) todo
                    .build();
            NotificationHandler handler = new NotificationHandler(verifier, PaymentConfig.WX_APIV3_KEY.getBytes(StandardCharsets.UTF_8));
            // 验签和解析请求体
            notification = handler.parse(_request);

        } catch (Exception e) {
            log.error("请求微信支付回调异常，异常信息:{}", e);
        }
    }

    @Override
    public void refundNotify(HttpServletRequest request, HttpServletResponse response) {

    }
}
