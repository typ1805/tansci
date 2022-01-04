package com.tansci.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName： SysMenuRole.java
 * @ClassPath： com.tansci.domain.SysMenuRole.java
 * @Description： 菜单角色
 * @Author： tanyp
 * @Date： 2021/7/20 16:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "sys_menu_role")
public class SysMenuRole {

    // 角色id
    private Integer roleId;

    // 菜单id
    private Integer menuId;

    // 当前角色低
    @TableField(exist = false)
    private Integer thisRoleId;

}
