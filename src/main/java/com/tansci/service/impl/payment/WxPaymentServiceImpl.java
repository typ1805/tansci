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
import com.tansci.utils.QRCodeUtils;
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
            // 订单金额信息
            Map amountMap = new HashMap();
            amountMap.put("total", dto.getAmount());
            amountMap.put("currency", "CNY");
            // 支付者信息
            Map payerMap = new HashMap();
            amountMap.put("openid", dto.getOpenId());
            // 请求body参数
            Map map = new HashMap();
            map.put("mchid", PaymentConfig.WX_MCH_ID);
            map.put("appid", PaymentConfig.WX_APP_ID);
            map.put("out_trade_no", dto.getOrderId());
            map.put("description", dto.getDescription());
            map.put("notify_url", PaymentConfig.WX_NNOTIFY_URL);
            map.put("amount", amountMap);
            map.put("payer", payerMap);

            CloseableHttpClient httpClient = httpClient();
            StringEntity entity = new StringEntity(JSONObject.toJSONString(map));
            entity.setContentType("application/json");
            CloseableHttpResponse response = null;
            try {
                switch (dto.getPayType()) {
                    case "NATIVE":
                        // 请求URL
                        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi");
                        httpPost.setEntity(entity);
                        httpPost.setHeader("Accept", "application/json");
                        // 完成签名并执行请求
                        response = httpClient.execute(httpPost);
                        int statusCode = response.getStatusLine().getStatusCode();
                        if (Objects.equals(Constants.SUCCESS, statusCode)) {
                            log.info("请求支付接口成功，响应参数：{} " + EntityUtils.toString(response.getEntity()));
                            // 生成二维码 TODO
                            String bodyAsString = EntityUtils.toString(response.getEntity());


                            String qrcode = QRCodeUtils.crateQRCode("", null, null);
                            paymentVo.setCodeUrl(qrcode);
                            paymentVo.setMessage(PayEnum.WX_USERPAYING.getKey());
                            paymentVo.setState(PayEnum.WX_USERPAYING.getValue());
                        } else {
                            log.info("请求支付接口失败，状态：{}，响应参数：{} ", statusCode, EntityUtils.toString(response.getEntity()));
                            paymentVo.setMessage(PayEnum.WX_PAYERROR.getKey());
                            paymentVo.setState(PayEnum.WX_PAYERROR.getValue());
                        }
                        break;
                    case "MICROPAY":
                        break;
                    case "JSAPI":
                        break;
                    case "MWEB":
                        break;
                    case "APP":
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
                    // TODO
                    String bodyAsString = EntityUtils.toString(response.getEntity());

                    paymentVo.setPaymentId("");
                    paymentVo.setMessage(PayEnum.WX_USERPAYING.getKey());
                    paymentVo.setState(PayEnum.WX_USERPAYING.getValue());
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
                    // TODO
                    String bodyAsString = EntityUtils.toString(response.getEntity());

                    paymentVo.setPaymentId("");
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
        return null;
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
