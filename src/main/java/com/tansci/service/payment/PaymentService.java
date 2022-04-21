package com.tansci.service.payment;

import com.tansci.domain.payment.dto.PaymentDto;
import com.tansci.domain.payment.vo.PaymentVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @path：com.tansci.service.payment.PaymentService.java
 * @className：PaymentService.java
 * @description：支付
 * @author：tanyp
 * @dateTime：2022/3/28 10:15
 * @editNote：
 */
public interface PaymentService {

    /**
     * @methodName：pay
     * @description：支付
     * @author：tanyp
     * @dateTime：2022/3/28 11:36
     * @Params：
     * @Return：
     * @editNote：
     */
    PaymentVo pay(PaymentDto dto);

    /**
     * @methodName：query
     * @description：查询
     * @author：tanyp
     * @dateTime：2022/3/28 11:36
     * @Params：
     * @Return：
     * @editNote：
     */
    PaymentVo query(PaymentDto dto);

    /**
     * @methodName：close
     * @description：关闭订单
     * @author：tanyp
     * @dateTime：2022/3/28 11:36
     * @Params：
     * @Return：
     * @editNote：
     */
    PaymentVo close(PaymentDto dto);

    /**
     * @methodName：refund
     * @description：订单退款
     * @author：tanyp
     * @dateTime：2022/3/28 11:36
     * @Params：
     * @Return：
     * @editNote：
     */
    PaymentVo refund(PaymentDto dto);

    /**
     * @methodName：payNotify
     * @description：支付回调
     * @author：tanyp
     * @dateTime：2022/3/28 11:36
     * @Params：
     * @Return：
     * @editNote：
     */
    PaymentVo payNotify(HttpServletRequest request, HttpServletResponse response);

    /**
     * @methodName：refundNotify
     * @description：退款回调
     * @author：tanyp
     * @dateTime：2022/3/28 11:36
     * @Params：
     * @Return：
     * @editNote：
     */
    PaymentVo refundNotify(HttpServletRequest request, HttpServletResponse response);

}
