package com.tansci.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @path：com.tansci.domain.LogErrorInfo.java
 * @className：LogErrorInfo.java
 * @description：操作日志异常信息
 * @author：tanyp
 * @dateTime：2021/12/02 11:33
 * @editNote：
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "log_error_info")
public class LogErrorInfo {

    // 主键id
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    // 请求参数
    private String reqParam;

    // 异常名称
    private String name;

    // 异常信息
    private String message;

    // 操作用户id
    private String userId;

    // 操作用户名称
    private String userName;

    // 请求方法
    private String method;

    // 请求url
    private String uri;

    // 请求IP
    private String ip;

    // 版本号
    private String version;

    // 创建时间
    private LocalDateTime createTime;

}
