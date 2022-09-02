package com.tansci.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @path：com.tansci.common.constant.Enums.java
 * @className：Enums.java
 * @description：常用枚举
 * @author：tanyp
 * @dateTime：2021/10/22 16:14
 * @editNote：
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Enums {

    /**
     * 系统字典类型
     */
    SYS_DIC_TYPE(0, "dic_type", "系统类"),
    SERVE_DIC_TYPE(1, "dic_type", "业务类"),

    /**
     * 认证相关
     */
    AUTH_NO_TOKEN(401, "auth_type", "用户凭证已过期，请重新登录！"),
    AUTH_NO_ACCESS(403, "auth_type", "无访问权限，请核实!"),
    AUTH_NONEXISTENT(404, "auth_type", "请求路径不存在"),

    /**
     * 公共
     */
    NOT_DEL_FALG(0, "del_falg", "正常"),
    IS_DEL_FALG(1, "del_falg", "已删除"),

    /**
     * 角色类型
     */
    ROLE_SYSTEM(0, "role_type", "平台角色"),
    ROLE_NOT_SYSTEM(1, "role_type", "非平台角色"),

    /**
     * 用户信息相关
     */
    USER_GENDER_MALE(0, "user_gender", "男"),
    USER_GENDER_GIRL(1, "user_gender", "女"),

    /**
     * 消息状态
     */
    MESSAGE_UNTREATED(0, "message_status", "未处理"),
    MESSAGE_PROCESSED(1, "message_status", "已处理"),

    /**
     * 消息发送状态
     */
    MESSAGE_LOG_SUCCESS(0, "message_log_status", "成功"),
    MESSAGE_LOG_FAIL(1, "message_log_status", "失败"),

    /**
     * 菜单类型
     */
    MENU_TYPE_0(0, "menu_type", "按钮"),
    MENU_TYPE_1(1, "menu_type", "菜单"),
    MENU_TYPE_2(2, "menu_type", "嵌套链接"),
    MENU_TYPE_3(3, "menu_type", "跳转链接"),

    /**
     * 状态
     */
    STATUS_0(0, "status", "未启用"),
    STATUS_1(1, "status", "启用"),

    /**
     * 调度执行状态
     */
    TASK_LOG_SUCCESS(0, "task_log_status", "成功"),
    TASK_LOG_FAIL(1, "task_log_status", "失败"),

    ;

    private Integer key;
    private String group;
    private String value;

    /**
     * @methodName：getValue
     * @description：根据key获取value
     * @author：tanyp
     * @dateTime：2021/7/18 13:09
     * @Params： [key]
     * @Return： java.lang.String
     * @editNote：
     */
    public static String getValue(Integer key) {
        for (Enums item : Enums.values()) {
            if (Objects.equals(key, item.key)) {
                return item.getValue();
            }
        }
        return null;
    }

    /**
     * @methodName：getVlaueByGroup
     * @description：根据key和group获取value
     * @author：tanyp
     * @dateTime：2021/7/18 13:09
     * @Params： [key, group]
     * @Return： java.lang.String
     * @editNote：
     */
    public static String getVlaueByGroup(Integer key, String group) {
        for (Enums item : Enums.values()) {
            if (Objects.equals(key, item.key) && Objects.equals(group, item.group)) {
                return item.getValue();
            }
        }
        return null;
    }

}
