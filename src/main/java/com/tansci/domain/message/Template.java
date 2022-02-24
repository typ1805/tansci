package com.tansci.domain.message;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Template implements Serializable {

    // 主键id
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    // 模板编码
    private String code;

    // 模板类型：0：验证码，1：短信通知，2：推广短信，3：国际/港澳台消息
    private Integer type;
    @TableField(exist = false)
    private String typeName;

    // 模板名称
    private String name;

    // 模板内容
    private String content;

    // 业务类型：0：短信，1：邮件
    private Integer businessType;
    @TableField(exist = false)
    private String businessTypeName;

    // 状态：0、审核中，1、通过，2、未通过
    private Integer state;
    @TableField(exist = false)
    private String stateName;

    // 删除转态：0：正常，1：已删除
    private Integer delFlag;

    // 创建人
    private String creater;

    // 更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime updateTime;

    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime createTime;

    // 备注
    private String remark;

}
