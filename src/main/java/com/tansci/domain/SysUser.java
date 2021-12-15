package com.tansci.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class SysUser {

    // 主键ID
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    // 用户名称
    private String username;

    // 密码
    private String password;

    // 用户昵称
    private String nickname;

    // 用户类型：0、超级管理员，1、管理员，2、普通用户
    private Integer type;
    @TableField(exist = false)
    private String typeName;

    // 性别：0、男，1、女
    private Integer gender;
    @TableField(exist = false)
    private String genderName;

    // 出生日期
    private LocalDate birthday;

    // 地址
    private String address;
    @TableField(exist = false)
    private String addressName;

    // 手机号
    private String phone;

    // 微信唯一标识
    private String openId;

    // 邮箱
    private String email;

    // 记住密码
    private Integer remember;

    // 删除状态：0、正常，1、已删除
    private Integer delFlag;
    @TableField(exist = false)
    private String delFlagName;

    // 更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone="GMT+8")
    private LocalDateTime updateTime;

    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone="GMT+8")
    private LocalDateTime createTime;

    // 备注
    private String remarks;

    @TableField(exist = false)
    private String role;

}
