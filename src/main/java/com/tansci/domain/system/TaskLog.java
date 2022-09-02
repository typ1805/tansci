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

import java.time.LocalDateTime;

/**
 * @ClassName： TaskLog.java
 * @ClassPath： com.tansci.domain.system.TaskLog.java
 * @Description： 调度执行日志
 * @Author： tanyp
 * @Date： 2022/2/25 9:35
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "task_log")
@ApiModel(value = "调度执行日志")
public class TaskLog {

    @ApiModelProperty(value = "主键id")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "任务编码")
    private String taskId;

    @ApiModelProperty(value = "任务服务名称")
    private String serverName;

    @ApiModelProperty(value = "状态：0、未启动，1、正常")
    private Integer status;

    @ApiModelProperty(value = "状态")
    @TableField(exist = false)
    private String statusName;

    @ApiModelProperty(value = "执行时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime executionTime;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "描述")
    private String remarks;

}
