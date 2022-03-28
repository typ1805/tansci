package com.tansci.domain.payment.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @path：com.tansci.domain.message.dto.PaymentDto.java
 * @className：PaymentDto.java
 * @description：支付DTO
 * @author：tanyp
 * @dateTime：2022/3/28 11:06 
 * @editNote：
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "支付DTO")
public class PaymentDto {

    @ApiModelProperty(value = "支付方式（PayEnum）")
    private String payType;

    @ApiModelProperty(value = "订单号")
    private String orderId;

    @ApiModelProperty(value = "订单总金额，单位为分（整数）")
    private Integer amount;

    @ApiModelProperty(value = "用户唯一标识")
    private String openId;

    @ApiModelProperty(value = "商品描述")
    private String description;

}
