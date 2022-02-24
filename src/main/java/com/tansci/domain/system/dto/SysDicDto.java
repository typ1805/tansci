package com.tansci.domain.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @path：com.kuiper.qms.domain.SysDicDto.java
 * @className：SysDicDto.java
 * @description：字典
 * @author：tanyp
 * @dateTime：2021/7/4 13:07
 * @editNote：
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "字典")
public class SysDicDto {

    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "父ID")
    private Integer parentId;

    @ApiModelProperty(value = "分组名称")
    private String groupName;

    @ApiModelProperty(value = "类型：0、系统，1、业务")
    private Integer type;

    @ApiModelProperty(value = "值")
    private Integer value;

    @ApiModelProperty(value = "名称")
    private String label;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "关键字")
    private String keyword;

}
