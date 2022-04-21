package com.tansci.service.payment;

import com.tansci.domain.payment.dto.PaymentDto;
import com.tansci.domain.payment.vo.PaymentVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName： PayService.java
 * @ClassPath： com.tansci.service.payment.PayService.java
 * @Description： 支付接口
 * @Author： tanyp
 * @Date： 2022/4/21 10:58
 **/
public interface PayService {

    PaymentVo pay(PaymentDto dto);

    PaymentVo query(PaymentDto dto);

    PaymentVo close(PaymentDto dto);

    PaymentVo refund(PaymentDto dto);

    void wxNotify(HttpServletRequest request, HttpServletResponse response);

    void wxRefundNotify(HttpServletRequest request, HttpServletResponse response);

    void aliNotify(HttpServletRequest request, HttpServletResponse response);
}
