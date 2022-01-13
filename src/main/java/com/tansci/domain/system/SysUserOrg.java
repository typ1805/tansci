package com.tansci.domain.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName： SysUserOrg.java
 * @ClassPath： com.tansci.domain.SysUserOrg.java
 * @Description： 用户组织
 * @Author： tanyp
 * @Date： 2021/12/15 10:08
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user_org")
public class SysUserOrg {

    private String userId;

    private Integer orgId;

}
