package com.tansci.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @path：com.tansci.domain.LogInfo.java
 * @className：LogInfo.java
 * @description：操作日志信息
 * @author：tanyp
 * @dateTime：2021/12/02 11:33
 * @editNote：
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "log_info")
public class LogInfo {

    // 主键id
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    // 功能模块
    private String module;

    // 操作类型
    private String type;

    // 操作描述
    private String message;

    // 请求参数
    private String reqParam;

    // 响应参数
    private String resParam;

    // 耗时
    private Long takeUpTime;

    // 操作用户id
    private String userId;

    // 操作用户名称
    private String userName;

    // 操作方法
    private String method;

    // 请求url
    private String uri;

    // 请求IP
    private String ip;

    // 版本号
    private String version;

    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone="GMT+8")
    private LocalDateTime createTime;

}
