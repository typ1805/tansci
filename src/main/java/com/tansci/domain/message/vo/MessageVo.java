package com.tansci.domain.message.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName： MessageVo.java
 * @ClassPath： com.tansci.domain.message.vo.MessageVo.java
 * @Description： 短信、邮件消息返回值
 * @Author： tanyp
 * @Date： 2021/6/7 11:35
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageVo implements Serializable {

    // 状态码
    private String code;

    // 状态码的描述
    private String message;

    // 请求ID
    private String requestId;

    // 发送回执ID
    private String bizId;

    // 状态：0、审核中，1、审核通过，2、审核失败
    private Integer state;

    // 审核备注
    private String reason;

    // 模板编码
    private String templateCode;

}
