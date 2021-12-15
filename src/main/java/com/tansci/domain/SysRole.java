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
 * @ClassName： SysRole.java
 * @ClassPath： com.tansci.domain.SysRole.java
 * @Description： 权限
 * @Author： tanyp
 * @Date： 2021/7/20 16:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "sys_role")
public class SysRole {

    // 主键id
    @TableId(type = IdType.AUTO)
    private Integer id;

    // 菜单名称
    private String name;

    // 类型：
    private Integer type;
    @TableField(exist = false)
    private String typeName;

    // 状态
    private Integer status;
    @TableField(exist = false)
    private String statusName;

    // 更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone="GMT+8")
    private LocalDateTime updateTime;

    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone="GMT+8")
    private LocalDateTime createTime;

    @TableField(exist = false)
    private List<String> userIds;

}
