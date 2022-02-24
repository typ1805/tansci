package com.tansci.domain.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "菜单权限VO")
public class SysMenuRoleVo {

    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "父id")
    private Integer parentId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "中文名称")
    private String chineseName;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @ApiModelProperty(value = "菜单id")
    private Integer menuId;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "子集")
    private List<SysMenuRoleVo> children;

}
