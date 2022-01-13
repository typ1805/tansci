package com.tansci.domain.system;

import com.baomidou.mybatisplus.annotation.TableName;
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
public class SysOrgRole {

    // 组织id
    private Integer orgId;

    // 角色id
    private Integer roleId;

}
