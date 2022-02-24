package com.tansci.domain.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "操作日志信息")
public class LogInfo {

    @ApiModelProperty(value = "主键id")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "功能模块")
    private String module;

    @ApiModelProperty(value = "操作类型")
    private String type;

    @ApiModelProperty(value = "操作描述")
    private String message;

    @ApiModelProperty(value = "请求参数")
    private String reqParam;

    @ApiModelProperty(value = "响应参数")
    private String resParam;

    @ApiModelProperty(value = "耗时")
    private Long takeUpTime;

    @ApiModelProperty(value = "操作用户id")
    private String userId;

    @ApiModelProperty(value = "操作用户名称")
    private String userName;

    @ApiModelProperty(value = "操作方法")
    private String method;

    @ApiModelProperty(value = "请求url")
    private String uri;

    @ApiModelProperty(value = "请求IP")
    private String ip;

    @ApiModelProperty(value = "版本号")
    private String version;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime createTime;

}
