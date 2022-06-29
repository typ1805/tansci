package com.tansci.domain.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName： SysMenuDto.java
 * @ClassPath： com.tansci.system.dto.SysMenu.java
 * @Description： 菜单DTO
 * @Author： tanyp
 * @Date： 2021/7/20 16:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "菜单DTO")
public class SysMenuDto {

    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "状态: 0、未上架，1、正常，2、下架")
    private Integer status;

    @ApiModelProperty(value = "类型集合：0、按钮，1、菜单，2、嵌套链接，3、跳转链接")
    private List<Integer> types;

    @ApiModelProperty(value = "父id")
    private Integer parentId;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

}
