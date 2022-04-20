package com.tansci.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @path：com.tansci.common.constant.PayConstants.java
 * @className：PayConstants.java
 * @description：支付枚举
 * @author：tanyp
 * @dateTime：2022/3/28 13:40
 * @editNote：
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum PayEnum {

    /**
     * 微信支付类型
     */
    WX("WX", "pay_wx_type", "微信"),

    WX_MICROPAY("MICROPAY", "pay_wx_type", "扫码支付"),

    WX_JSAPI("JSAPI", "pay_wx_type", "JSAPI支付"),

    WX_MWEB("MWEB", "pay_wx_type", "H5支付"),

    WX_NATIVE("NATIVE", "pay_wx_type", "Native支付"),

    WX_APP("APP", "pay_wx_type", "APP支付"),

    /**
     * 微信支付状态
     */
    // 成功
    WX_SUCCESS("SUCCESS", "pay_wx_status", "2"),
    // 订单已支付
    WX_ORDERPAID("ORDERPAID", "pay_wx_status", "2"),
    // 转入退款
    WX_REFUND("REFUND", "pay_wx_status", "2"),
    // 退款处理中
    WX_PROCESSING("PROCESSING", "pay_wx_status", "1"),
    // 退款关闭
    WX_REFUNDCLOSE("REFUNDCLOSE", "pay_wx_status", "1"),
    // 未支付
    WX_NOTPAY("NOTPAY", "pay_wx_status", "1"),
    // 已关闭
    WX_CLOSED("CLOSED", "pay_wx_status", "1"),
    // 已撤销
    WX_REVOKED("REVOKED", "pay_wx_status", "1"),
    // 用户支付中
    WX_USERPAYING("USERPAYING", "pay_wx_status", "1"),
    // 支付失败
    WX_PAYERROR("PAYERROR", "pay_wx_status", "3"),
    // 退款失败
    WX_FAIL("FAIL", "pay_wx_status", "3"),
    // 异常
    WX_CHANGE("CHANGE", "pay_wx_status", "3"),

    // ==============================================================================

    /**
     * 支付宝支付类型
     */
    ALI("ALI", "pay_ali_type", "支付宝"),

    ALI_APP("APP", "pay_ali_type", "APP支付"),

    ALI_WAP("WAP", "pay_ali_type", "手机网站支付"),

    ALI_PAGE("PAGE", "pay_ali_type", "电脑网站支付"),

    ALI_NATIVE("NATIVE", "pay_ali_type", "预创建"),

    /**
     * 支付宝支付状态
     */
    // 交易创建
    ALI_WAIT_BUYER_PAY("WAIT_BUYER_PAY", "pay_ali_status", "1"),
    // 交易关闭
    ALI_TRADE_CLOSED("TRADE_CLOSED", "pay_ali_status", "1"),
    // 支付成功
    ALI_TRADE_SUCCESS("TRADE_SUCCESS", "pay_ali_status", "2"),
    // 交易完成
    ALI_TRADE_FINISHED("TRADE_FINISHED", "pay_ali_status", "2"),
    // 支付失败
    ALI_PAYERROR("PAYERROR", "pay_ali_status", "3"),


    ;

    private String key;
    private String group;
    private String value;

    /**
     * @methodName：getVlaueByGroup
     * @description：根据key和group获取value
     * @author：tanyp
     * @dateTime：2021/7/18 13:09
     * @Params： [key, group]
     * @Return： java.lang.String
     * @editNote：
     */
    public static String getVlaueByGroup(String key, String group) {
        for (PayEnum item : PayEnum.values()) {
            if (Objects.equals(key, item.key) && Objects.equals(group, item.group)) {
                return item.getValue();
            }
        }
        return null;
    }

}
