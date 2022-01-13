package com.tansci.domain.system.dto;

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
public class SysRoleDto {

    private Integer roleId;

    private Integer orgId;

    private String userId;

    private List<Integer> menuIds;

    private List<String> userIds;

}
