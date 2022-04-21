package com.tansci.service.impl.payment;

import com.tansci.common.exception.BusinessException;
import com.tansci.domain.payment.dto.PaymentDto;
import com.tansci.domain.payment.vo.PaymentVo;
import com.tansci.service.payment.PayService;
import com.tansci.service.payment.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName： PayServiceImpl.java
 * @ClassPath： com.tansci.service.impl.payment.PayServiceImpl.java
 * @Description： 支付接口
 * @Author： tanyp
 * @Date： 2022/4/21 11:03
 **/
@Slf4j
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    @Qualifier("wxPaymentServiceImpl")
    private PaymentService wxPaymentService;

    @Autowired
    @Qualifier("aliPaymentServiceImpl")
    private PaymentService aliPaymentService;

    @Override
    public PaymentVo pay(PaymentDto dto) {
        // 创建订单信息
        PaymentVo paymentVo = null;
        switch (dto.getPayType()) {
            case "WX":
                paymentVo = wxPaymentService.pay(dto);
                break;
            case "ALI":
                paymentVo = aliPaymentService.pay(dto);
                break;
            default:
                throw new BusinessException("不支持此支付渠道");
        }
        return paymentVo;
    }

    @Override
    public PaymentVo query(PaymentDto dto) {
        PaymentVo paymentVo = null;
        switch (dto.getPayType()) {
            case "WX":
                paymentVo = wxPaymentService.query(dto);
                break;
            case "ALI":
                paymentVo = aliPaymentService.query(dto);
                break;
            default:
                throw new BusinessException("不支持此支付渠道");
        }
        return paymentVo;
    }

    @Override
    public PaymentVo close(PaymentDto dto) {
        PaymentVo paymentVo = null;
        switch (dto.getPayType()) {
            case "WX":
                paymentVo = wxPaymentService.close(dto);
                break;
            case "ALI":
                paymentVo = aliPaymentService.close(dto);
                break;
            default:
                throw new BusinessException("不支持此支付渠道");
        }
        return paymentVo;
    }

    @Override
    public PaymentVo refund(PaymentDto dto) {
        PaymentVo paymentVo = null;
        switch (dto.getPayType()) {
            case "WX":
                paymentVo = wxPaymentService.refund(dto);
                break;
            case "ALI":
                paymentVo = aliPaymentService.refund(dto);
                break;
            default:
                throw new BusinessException("不支持此支付渠道");
        }
        return paymentVo;
    }

    @Override
    public void wxNotify(HttpServletRequest request, HttpServletResponse response) {
        PaymentVo paymentVo = wxPaymentService.payNotify(request, response);
    }

    @Override
    public void wxRefundNotify(HttpServletRequest request, HttpServletResponse response) {
        PaymentVo paymentVo = wxPaymentService.refundNotify(request, response);
    }

    @Override
    public void aliNotify(HttpServletRequest request, HttpServletResponse response) {
        PaymentVo paymentVo = aliPaymentService.payNotify(request, response);
    }

}
