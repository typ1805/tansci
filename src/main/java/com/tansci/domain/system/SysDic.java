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
import java.util.List;

/**
 * @path：com.tansci.domain.SysDic.java
 * @className：SysDic.java
 * @description：字典
 * @author：tanyp
 * @dateTime：2021/7/4 13:07
 * @editNote：
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_dic")
@ApiModel(value = "字典")
public class SysDic {

    @ApiModelProperty(value = "主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "父ID")
    private Integer parentId;

    @ApiModelProperty(value = "分组名称")
    private String groupName;

    @ApiModelProperty(value = "类型：0、系统，1、业务")
    private Integer type;

    @ApiModelProperty(value = "类型")
    @TableField(exist = false)
    private String typeName;

    @ApiModelProperty(value = "值")
    private Integer dicValue;

    @ApiModelProperty(value = "名称")
    private String dicLabel;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "预留字段1")
    private String text1;

    @ApiModelProperty(value = "预留字段3")
    private String text2;

    @ApiModelProperty(value = "预留字段2")
    private String text3;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "描述")
    private String remarks;

    @ApiModelProperty(value = "子集")
    @TableField(exist = false)
    private List<SysDic> children;

}
