package com.tansci.domain.message;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName： Template.java
 * @ClassPath： com.tansci.domain.message.Template.java
 * @Description： 短信、邮件消息模板
 * @Author： tanyp
 * @Date： 2021/6/7 11:35
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("template")
@ApiModel(value = "消息模板")
public class Template implements Serializable {

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "模板编码")
    private String code;

    @ApiModelProperty(value = "模板类型：0：验证码，1：短信通知，2：推广短信，3：国际/港澳台消息")
    private Integer type;

    @ApiModelProperty(value = "模板类型")
    @TableField(exist = false)
    private String typeName;

    @ApiModelProperty(value = "模板名称")
    private String name;

    @ApiModelProperty(value = "模板内容")
    private String content;

    @ApiModelProperty(value = "业务类型：0：短信，1：邮件")
    private Integer businessType;

    @ApiModelProperty(value = "业务类型")
    @TableField(exist = false)
    private String businessTypeName;

    @ApiModelProperty(value = "状态：0、审核中，1、通过，2、未通过")
    private Integer state;

    @ApiModelProperty(value = "状态")
    @TableField(exist = false)
    private String stateName;

    @ApiModelProperty(value = "删除转态：0：正常，1：已删除")
    private Integer delFlag;

    @ApiModelProperty(value = "创建人")
    private String creater;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "备注")
    private String remark;

}
