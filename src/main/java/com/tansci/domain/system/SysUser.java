package com.tansci.domain.system;

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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @path：com.tansci.domain.SysUser.java
 * @className：SysUser.java
 * @description： 用户
 * @author：tanyp
 * @dateTime：2021/12/02 20:02
 * @editNote：
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
@ApiModel(value = "用户")
public class SysUser {

    @ApiModelProperty(value = "主键ID")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "用户类型：0、超级管理员，1、管理员，2、普通用户")
    private Integer type;

    @ApiModelProperty(value = "用户类型")
    @TableField(exist = false)
    private String typeName;

    @ApiModelProperty(value = "性别：0、男，1、女")
    private Integer gender;

    @ApiModelProperty(value = "性别")
    @TableField(exist = false)
    private String genderName;

    @ApiModelProperty(value = "出生日期")
    private LocalDate birthday;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "微信唯一标识")
    private String openId;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "记住密码")
    private Integer remember;

    @ApiModelProperty(value = "删除状态：0、正常，1、已删除")
    private Integer delFlag;

    @ApiModelProperty(value = "删除状态")
    @TableField(exist = false)
    private String delFlagName;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "权限")
    @TableField(exist = false)
    private String role;

    @ApiModelProperty(value = "组织id")
    @TableField(exist = false)
    private Integer orgId;

    @ApiModelProperty(value = "值IDS")
    @TableField(exist = false)
    private List<Integer> orgIds;

}
