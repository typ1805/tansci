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
public class SysDic {

    // 主键id
    @TableId(type = IdType.AUTO)
    private Integer id;
    // 父ID
    private Integer parentId;
    // 分组名称
    private String groupName;

    // 类型：0、系统，1、业务
    private Integer type;
    @TableField(exist = false)
    private String typeName;

    // 值
    private Integer dicValue;
    // 名称
    private String dicLabel;
    // 排序
    private Integer sort;
    // 预留字段1
    private String text1;
    // 预留字段3
    private String text2;
    // 预留字段2
    private String text3;
    // 更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone="GMT+8")
    private LocalDateTime updateTime;
    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone="GMT+8")
    private LocalDateTime createTime;
    // 描述
    private String remarks;

    @TableField(exist = false)
    private List<SysDic> children;

}
