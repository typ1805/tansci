package com.tansci.domain.system;

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
 * @path：com.tansci.domain.SysOrg.java
 * @className：SysOrg.java
 * @description：组织
 * @author：tanyp
 * @dateTime：2021/10/23 13:35 
 * @editNote：
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_org")
public class SysOrg {

    // 主键id
    @TableId(type = IdType.AUTO)
    private Integer id;

    // 组织名称
    private String name;

    // 父id
    private Integer parentId;

    // 排序
    private Integer sort;

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

    @TableField(exist = false)
    private List<SysOrg> children;

    @TableField(exist = false)
    private String userId;

}
