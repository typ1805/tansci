package com.tansci.service.impl.payment;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.tansci.common.constant.PayEnum;
import com.tansci.common.exception.BusinessException;
import com.tansci.config.PaymentConfig;
import com.tansci.domain.payment.dto.PaymentDto;
import com.tansci.domain.payment.vo.PaymentVo;
import com.tansci.service.payment.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * @path：com.tansci.service.impl.payment.AliPaymentServiceImpl.java
 * @className：AliPaymentServiceImpl.java
 * @description：支付宝支付
 * @author：tanyp
 * @dateTime：2022/3/28 11:32
 * @editNote：
 */
@Slf4j
@Service
public class AliPaymentServiceImpl implements PaymentService {

    @Override
    public PaymentVo pay(PaymentDto dto) {
        PaymentVo paymentVo = PaymentVo.builder().orderId(dto.getOrderId()).build();

        /**
         * out_trade_no：商户订单号：64个字符以内，仅支持字母、数字、下划线且需保证在商户端不重复
         * total_amount：订单总金额: 单位为元，精确到小数点后两位，取值范围：[0.01,100000000]
         * subject：订单标题: 不可使用特殊字符，如 /，=，& 等
         * product_code：产品码: 商家和支付宝签约的产品码
         * quit_url：用户付款中途退出返回商户网站的地址
         */

        try {
            switch (dto.getPayType()) {
                case "WAP":
                    // 手机网站支付
                    Map<String, Object> wapReq = new HashMap<>();
                    wapReq.put("out_trade_no", dto.getOrderId());
                    wapReq.put("total_amount", dto.getAmount());
                    wapReq.put("subject", dto.getDescription());
                    wapReq.put("product_code", "QUICK_WAP_WAY");
                    wapReq.put("quit_url", dto.getQuitUrl());

                    AlipayTradeWapPayRequest wapRequest = new AlipayTradeWapPayRequest();
                    wapRequest.setBizContent(JSON.toJSONString(wapReq));
                    wapRequest.setNotifyUrl(PaymentConfig.ALI_NOTIFY_URL);

                    AlipayTradeWapPayResponse wapResponse = new DefaultAlipayClient(
                            PaymentConfig.ALI_GATEWAY_URL,
                            PaymentConfig.ALI_APP_ID,
                            PaymentConfig.ALI_PRIVATE_KEY,
                            PaymentConfig.ALI_FORMAT,
                            PaymentConfig.ALI_CHARSET,
                            PaymentConfig.ALI_PUBLIC_KEY,
                            PaymentConfig.ALI_SIGN_TYPE
                    ).pageExecute(wapRequest);

                    log.info("支付返回信息:{}", JSON.toJSON(wapResponse));

                    // 返回表单html
                    String wapBody = wapResponse.getBody();
                    if (!Objects.isNull(wapBody) && !Objects.equals("", wapBody)) {
                        paymentVo.setHtml(wapBody);
                        paymentVo.setPaymentId(wapResponse.getTradeNo());
                        paymentVo.setState(PayEnum.ALI_WAIT_BUYER_PAY.getValue());
                        paymentVo.setMessage(PayEnum.ALI_WAIT_BUYER_PAY.getKey());
                    } else {
                        paymentVo.setState(PayEnum.ALI_PAYERROR.getValue());
                        paymentVo.setMessage(wapResponse.getMsg());
                    }
                    break;
                case "PAGE":
                    // 电脑网站支付
                    Map<String, Object> pageReq = new HashMap<>();
                    pageReq.put("out_trade_no", dto.getOrderId());
                    pageReq.put("total_amount", dto.getAmount());
                    pageReq.put("subject", dto.getDescription());
                    pageReq.put("product_code", "FAST_INSTANT_TRADE_PAY");

                    AlipayTradePagePayRequest pageRequest = new AlipayTradePagePayRequest();
                    pageRequest.setNotifyUrl(PaymentConfig.ALI_NOTIFY_URL);
                    pageRequest.setBizContent(JSON.toJSONString(pageReq));

                    AlipayTradePagePayResponse pageResponse = new DefaultAlipayClient(
                            PaymentConfig.ALI_GATEWAY_URL,
                            PaymentConfig.ALI_APP_ID,
                            PaymentConfig.ALI_PRIVATE_KEY,
                            PaymentConfig.ALI_FORMAT,
                            PaymentConfig.ALI_CHARSET,
                            PaymentConfig.ALI_PUBLIC_KEY,
                            PaymentConfig.ALI_SIGN_TYPE
                    ).pageExecute(pageRequest);

                    log.info("支付返回信息:{}", JSON.toJSON(pageResponse));
                    // 返回表单html
                    String pageBody = pageResponse.getBody();
                    if (!Objects.isNull(pageBody) && !Objects.equals("", pageBody)) {
                        paymentVo.setHtml(pageBody);
                        paymentVo.setPaymentId(pageResponse.getTradeNo());
                        paymentVo.setState(PayEnum.ALI_WAIT_BUYER_PAY.getValue());
                        paymentVo.setMessage(PayEnum.ALI_WAIT_BUYER_PAY.getKey());
                    } else {
                        paymentVo.setState(PayEnum.ALI_PAYERROR.getValue());
                        paymentVo.setMessage(pageResponse.getMsg());
                    }
                    break;
                case "APP":
                    // app支付接口2.0
                    Map<String, Object> appReq = new HashMap<>();
                    appReq.put("out_trade_no", dto.getOrderId());
                    appReq.put("total_amount", dto.getAmount());
                    appReq.put("subject", dto.getDescription());
                    appReq.put("product_code", "QUICK_MSECURITY_PAY");

                    AlipayTradeAppPayRequest appRequest = new AlipayTradeAppPayRequest();
                    appRequest.setNotifyUrl(PaymentConfig.ALI_NOTIFY_URL);
                    appRequest.setBizContent(JSON.toJSONString(appReq));

                    AlipayTradeAppPayResponse appResponse = new DefaultAlipayClient(
                            PaymentConfig.ALI_GATEWAY_URL,
                            PaymentConfig.ALI_APP_ID,
                            PaymentConfig.ALI_PRIVATE_KEY,
                            PaymentConfig.ALI_FORMAT,
                            PaymentConfig.ALI_CHARSET,
                            PaymentConfig.ALI_PUBLIC_KEY,
                            PaymentConfig.ALI_SIGN_TYPE
                    ).sdkExecute(appRequest);

                    log.info("支付返回信息:{}", JSON.toJSON(appResponse));
                    // 返回预定的支付码body
                    String orderBody = appResponse.getBody();
                    if (!Objects.isNull(orderBody) && !Objects.equals("", orderBody)) {
                        paymentVo.setHtml(orderBody);
                        paymentVo.setPaymentId(appResponse.getTradeNo());
                        paymentVo.setState(PayEnum.ALI_WAIT_BUYER_PAY.getValue());
                        paymentVo.setMessage(PayEnum.ALI_WAIT_BUYER_PAY.getKey());
                    } else {
                        paymentVo.setState(PayEnum.ALI_PAYERROR.getValue());
                        paymentVo.setMessage(appResponse.getMsg());
                    }
                    break;
                case "NATIVE":
                    // 预支付NATIVE
                    Map<String, Object> nativeReq = new HashMap<>();
                    nativeReq.put("out_trade_no", dto.getOrderId());
                    nativeReq.put("total_amount", dto.getAmount());
                    nativeReq.put("subject", dto.getDescription());

                    AlipayTradePrecreateRequest nativeRequest = new AlipayTradePrecreateRequest();
                    nativeRequest.setNotifyUrl(PaymentConfig.ALI_NOTIFY_URL);
                    nativeRequest.setBizContent(JSON.toJSONString(nativeReq));

                    AlipayTradePrecreateResponse nativeResponse = new DefaultAlipayClient(
                            PaymentConfig.ALI_GATEWAY_URL,
                            PaymentConfig.ALI_APP_ID,
                            PaymentConfig.ALI_PRIVATE_KEY,
                            PaymentConfig.ALI_FORMAT,
                            PaymentConfig.ALI_CHARSET,
                            PaymentConfig.ALI_PUBLIC_KEY,
                            PaymentConfig.ALI_SIGN_TYPE
                    ).execute(nativeRequest);

                    log.info("支付返回信息:{}", JSON.toJSON(nativeRequest));
                    if (Objects.equals("10000", nativeResponse.getCode())) {
                        paymentVo.setCodeUrl(nativeResponse.getQrCode());
                        paymentVo.setState(PayEnum.ALI_WAIT_BUYER_PAY.getValue());
                        paymentVo.setMessage(PayEnum.ALI_WAIT_BUYER_PAY.getKey());
                    } else {
                        paymentVo.setState(PayEnum.ALI_PAYERROR.getValue());
                        paymentVo.setMessage(nativeResponse.getMsg());
                    }
                    break;
                default:
                    throw new BusinessException("不支持此支付类型");
            }
        } catch (AlipayApiException e) {
            log.error("请求支付宝异常:{}", e.getMessage());
            throw new BusinessException(e.getErrMsg());
        }
        return paymentVo;
    }

    @Override
    public PaymentVo query(PaymentDto dto) {
        PaymentVo paymentVo = PaymentVo.builder().orderId(dto.getOrderId()).build();

        /**
         * out_trade_no：订单支付时传入的商户订单号,和支付宝交易号不能同时为空，trade_no,out_trade_no如果同时存在优先取trade_no
         * trade_no：支付宝交易号，和商户订单号不能同时为空
         */

        try {
            Map param = new HashMap();
            param.put("out_trade_no", dto.getOrderId());
            param.put("trade_no", dto.getPaymentId());

            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
            request.setBizContent(JSON.toJSONString(param));

            AlipayTradeQueryResponse response = new DefaultAlipayClient(
                    PaymentConfig.ALI_GATEWAY_URL,
                    PaymentConfig.ALI_APP_ID,
                    PaymentConfig.ALI_PRIVATE_KEY,
                    PaymentConfig.ALI_FORMAT,
                    PaymentConfig.ALI_CHARSET,
                    PaymentConfig.ALI_PUBLIC_KEY,
                    PaymentConfig.ALI_SIGN_TYPE
            ).execute(request);

            log.info("查询返回信息：{}", JSON.toJSON(response));
            if (Objects.equals("10000", response.getCode())) {
                paymentVo.setPaymentId(response.getTradeNo());
                paymentVo.setState(PayEnum.getVlaueByGroup(response.getTradeStatus(), "pay_ali_status"));
            }
            paymentVo.setMessage(response.getMsg());
        } catch (AlipayApiException e) {
            log.error("请求支付宝查询订单信息异常:{}", e.getMessage());
            throw new BusinessException(e.getErrMsg());
        }
        return paymentVo;
    }

    @Override
    public PaymentVo close(PaymentDto dto) {
        PaymentVo paymentVo = PaymentVo.builder().orderId(dto.getOrderId()).build();

        /**
         * out_trade_no：订单支付时传入的商户订单号,和支付宝交易号不能同时为空，trade_no,out_trade_no如果同时存在优先取trade_no
         * trade_no：支付宝交易号，和商户订单号不能同时为空
         */

        try {
            Map param = new HashMap();
            param.put("out_trade_no", dto.getOrderId());
            param.put("trade_no", dto.getPaymentId());

            AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
            request.setNotifyUrl(PaymentConfig.ALI_GATEWAY_URL);
            request.setBizContent(JSON.toJSONString(param));

            AlipayTradeCloseResponse response = new DefaultAlipayClient(
                    PaymentConfig.ALI_GATEWAY_URL,
                    PaymentConfig.ALI_APP_ID,
                    PaymentConfig.ALI_PRIVATE_KEY,
                    PaymentConfig.ALI_FORMAT,
                    PaymentConfig.ALI_CHARSET,
                    PaymentConfig.ALI_PUBLIC_KEY,
                    PaymentConfig.ALI_SIGN_TYPE
            ).execute(request);

            log.info("关闭返回信息：{}", JSON.toJSON(response));
            if (Objects.equals("10000", response.getCode())) {
                paymentVo.setPaymentId(response.getTradeNo());
                paymentVo.setState(PayEnum.ALI_TRADE_CLOSED.getValue());
            }
            paymentVo.setMessage(response.getMsg());
        } catch (AlipayApiException e) {
            log.error("请求支付宝关闭订单异常:{}", e.getMessage());
            throw new BusinessException(e.getErrMsg());
        }
        return paymentVo;
    }

    @Override
    public PaymentVo refund(PaymentDto dto) {
        PaymentVo paymentVo = PaymentVo.builder().orderId(dto.getOrderId()).refundId(dto.getRefundId()).build();

        /**
         * out_trade_no：订单支付时传入的商户订单号,和支付宝交易号不能同时为空，trade_no,out_trade_no如果同时存在优先取trade_no
         * trade_no：支付宝交易号，和商户订单号不能同时为空
         * refund_amount：退款金额
         */

        try {
            Map param = new HashMap();
            param.put("out_trade_no", dto.getOrderId());
            param.put("trade_no", dto.getPaymentId());
            param.put("refund_amount", dto.getAmount());

            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
            request.setNotifyUrl(PaymentConfig.ALI_GATEWAY_URL);
            request.setBizContent(JSON.toJSONString(param));

            AlipayTradeRefundResponse response = new DefaultAlipayClient(
                    PaymentConfig.ALI_GATEWAY_URL,
                    PaymentConfig.ALI_APP_ID,
                    PaymentConfig.ALI_PRIVATE_KEY,
                    PaymentConfig.ALI_FORMAT,
                    PaymentConfig.ALI_CHARSET,
                    PaymentConfig.ALI_PUBLIC_KEY,
                    PaymentConfig.ALI_SIGN_TYPE
            ).execute(request);

            log.info("退款返回信息：{}", JSON.toJSON(response));
            if (Objects.equals("10000", response.getCode())) {
                paymentVo.setPaymentId(response.getTradeNo());
                paymentVo.setRefundNo(response.getTradeNo());
                paymentVo.setAmount(response.getRefundFee());
                paymentVo.setState(PayEnum.ALI_TRADE_CLOSED.getValue());
            }
            paymentVo.setMessage(response.getMsg());
        } catch (AlipayApiException e) {
            log.error("请求支付宝订单退款异常:{}", e.getMessage());
            throw new BusinessException(e.getErrMsg());
        }
        return paymentVo;
    }

    @Override
    public PaymentVo payNotify(HttpServletRequest request, HttpServletResponse response) {
        PaymentVo paymentVo = new PaymentVo();
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }

            // 乱码解决，这段代码在出现乱码时使用
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        try {
            String appId = new String(request.getParameter("app_id").getBytes("ISO-8859-1"), "UTF-8");
            // 验证app_id是否为该商户本身
            if (!Objects.equals(PaymentConfig.ALI_APP_ID, appId)) {
                log.error("非法的回调请求，请求appId：{}", appId);
                return null;
            }

            // 验证签名
            if (AlipaySignature.rsaCheckV1(params, PaymentConfig.ALI_PUBLIC_KEY, PaymentConfig.ALI_CHARSET, PaymentConfig.ALI_SIGN_TYPE)) {
                // 商户订单号
                String outTradeNo = null;
                if (!Objects.isNull(request.getParameter("out_trade_no"))) {
                    outTradeNo = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
                }
                paymentVo.setOrderId(outTradeNo);

                // 支付宝交易号
                String tradeNo = null;
                if (!Objects.isNull(request.getParameter("trade_no"))) {
                    tradeNo = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
                }
                paymentVo.setPaymentId(tradeNo);

                // 交易状态
                String tradeStatus = null;
                if (!Objects.isNull(request.getParameter("trade_status"))) {
                    tradeStatus = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
                }
                paymentVo.setState(PayEnum.getVlaueByGroup(tradeStatus, "pay_ali_status"));

                // 支付总金额
                String totalAmount = null;
                if (!Objects.isNull(request.getParameter("total_amount"))) {
                    totalAmount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
                }
                paymentVo.setAmount(totalAmount);

                // 支付时间
                String gmtPayment = null;
                if (!Objects.isNull(request.getParameter("gmt_payment"))) {
                    gmtPayment = new String(request.getParameter("gmt_payment").getBytes("ISO-8859-1"), "UTF-8");
                }
                paymentVo.setPayTime(gmtPayment);

                /**
                 * 退款回调是返回
                 */
                String outBizNo = null;
                if (!Objects.isNull(request.getParameter("out_biz_no"))) {
                    outBizNo = new String(request.getParameter("out_biz_no").getBytes("ISO-8859-1"), "UTF-8");
                }
                paymentVo.setOrderId(outBizNo);

                // 本次退款是否发生了资金变化
                String fundChange = null;
                if (!Objects.isNull(request.getParameter("fund_change"))) {
                    fundChange = new String(request.getParameter("fund_change").getBytes("ISO-8859-1"), "UTF-8");
                }
//                paymentVo.setAmount(fundChange);

                // 退款总金额
                String refundFee = null;
                if (!Objects.isNull(request.getParameter("refund_fee"))) {
                    refundFee = new String(request.getParameter("refund_fee").getBytes("ISO-8859-1"), "UTF-8");
                }
                paymentVo.setAmount(refundFee);

                // 退款时间
                String gmtRefund = null;
                if (!Objects.isNull(request.getParameter("gmt_refund"))) {
                    gmtRefund = new String(request.getParameter("gmt_refund").getBytes("ISO-8859-1"), "UTF-8");
                }
                paymentVo.setRefundTime(gmtRefund);
                return paymentVo;
            } else {
                log.error("验证失败");
            }
        } catch (Exception e) {
            log.error("验证失败:{}", e);
        }
        return null;
    }

    @Override
    public PaymentVo refundNotify(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

}
