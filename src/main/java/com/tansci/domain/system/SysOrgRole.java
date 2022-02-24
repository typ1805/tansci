package com.tansci.domain.system;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @path：com.tansci.domain.SysOrgRole.java
 * @className：SysOrgRole.java
 * @description：组织角色
 * @author：tanyp
 * @dateTime：2021/10/23 13:39
 * @editNote：
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_org_role")
@ApiModel(value = "组织角色")
public class SysOrgRole {

    @ApiModelProperty(value = "组织id")
    private Integer orgId;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

}
