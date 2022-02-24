package com.tansci.domain.message.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "短信、邮件消息返回值")
public class MessageVo implements Serializable {

    @ApiModelProperty(value = "状态码")
    private String code;

    @ApiModelProperty(value = "状态码的描述")
    private String message;

    @ApiModelProperty(value = "请求ID")
    private String requestId;

    @ApiModelProperty(value = "发送回执ID")
    private String bizId;

    @ApiModelProperty(value = "状态：0、审核中，1、审核通过，2、审核失败")
    private Integer state;

    @ApiModelProperty(value = "审核备注")
    private String reason;

    @ApiModelProperty(value = "模板编码")
    private String templateCode;

}
