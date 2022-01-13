package com.tansci.domain.system.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @path：com.tansci.domain.vo.SysMenuRoleVo.java
 * @className：SysMenuRoleVo.java
 * @description：菜单权限
 * @author：tanyp
 * @dateTime：2021/7/23 22:32
 * @editNote：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysMenuRoleVo {

    private Integer id;

    private Integer parentId;

    private String name;

    private String chineseName;

    private Integer roleId;

    private Integer menuId;

    private Integer sort;

    private List<SysMenuRoleVo> children;

}
