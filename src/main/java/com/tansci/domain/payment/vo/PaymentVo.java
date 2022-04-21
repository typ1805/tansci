package com.tansci.domain.payment.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @path：com.tansci.domain.payment.vo.PaymentVo.java
 * @className：PaymentVo.java
 * @description：支付VO
 * @author：tanyp
 * @dateTime：2022/3/28 11:06 
 * @editNote：
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "支付VO")
public class PaymentVo {

    @ApiModelProperty(value = "订单号")
    private String orderId;

    @ApiModelProperty(value = "支付订单号")
    private String paymentId;

    @ApiModelProperty(value = "退款单号")
    private String refundId;

    @ApiModelProperty(value = "三方退款单号")
    private String refundNo;

    @ApiModelProperty(value = "支付时间")
    private String payTime;

    @ApiModelProperty(value = "支付状态")
    private String state;

    @ApiModelProperty(value = "支付状态描述")
    private String message;

    @ApiModelProperty(value = "二维码链接")
    private String codeUrl;

    @ApiModelProperty(value = "交易金额")
    private String amount;

    @ApiModelProperty(value = "html表单")
    private String html;

    @ApiModelProperty(value = "退款时间")
    private String refundTime;

    @ApiModelProperty(value = "预支付交易会话标识")
    private String prepayId;

}
