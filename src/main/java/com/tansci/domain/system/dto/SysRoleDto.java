package com.tansci.domain.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @path：com.kuiper.qms.domain.dto.SysRoleDto.java
 * @className：SysRoleDto.java
 * @description：权限
 * @author：tanyp
 * @dateTime：2021/7/23 23:08
 * @editNote：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "权限DTO")
public class SysRoleDto {

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @ApiModelProperty(value = "组织id")
    private Integer orgId;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "菜单ids")
    private List<Integer> menuIds;

    @ApiModelProperty(value = "用户ids")
    private List<String> userIds;

}
