package com.tansci.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName： SysMenu.java
 * @ClassPath： com.tansci.domain.SysMenu.java
 * @Description： 菜单
 * @Author： tanyp
 * @Date： 2021/7/20 16:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "sys_menu")
public class SysMenu {

    // 主键id
    @TableId(type = IdType.AUTO)
    private Integer id;

    // 菜单名称
    private String name;

    // 菜单路由
    private String path;

    // 组件名称
    private String component;

    // 菜单图标
    private String icon;

    // 状态: 0、未上架，1、正常，2、下架
    private Integer status;
    @TableField(exist = false)
    private String statusName;

    // 类型：0、前端菜单，1、后端菜单
    private Integer type;
    @TableField(exist = false)
    private String typeName;

    // 中文名称
    private String chineseName;

    // 英文名称
    private String englishName;

    // 父id
    private Integer parentId;

    // 排序
    private Integer sort;

    // 是否登录后才能访问：0、是，1、否
    private Integer requireAuth;

    // 菜单切换时是否保活：0.是，1、否
    private Integer keepAlive;

    // 是否可用：0、是，1、否
    private Integer enabled;

    // 更新时间
    private LocalDateTime updateTime;

    // 创建时间
    private LocalDateTime createTime;

    @TableField(exist = false)
    private List<SysMenu> children;

    @TableField(exist = false)
    private Integer roleId;

}
