package com.tansci.domain.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName： TaskConfigDto.java
 * @ClassPath： com.tansci.domain.system.dto.TaskConfigDto.java
 * @Description： 任务配置
 * @Author： tanyp
 * @Date： 2022/2/25 9:35
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "任务配置DTO")
public class TaskConfigDto {

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "任务服务名称")
    private String code;

    @ApiModelProperty(value = "任务编码")
    private String taskId;

    @ApiModelProperty(value = "任务执行规则时间：cron表达式")
    private String expression;

    @ApiModelProperty(value = "任务名称")
    private String name;

    @ApiModelProperty(value = "状态：0、未启动，1、正常")
    private Integer status;

}
