package com.tansci.domain.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName： SysUserRole.java
 * @ClassPath： com.kuiper.qms.domain.SysUserRole.java
 * @Description： 用户角色
 * @Author： tanyp
 * @Date： 2021/7/20 16:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "sys_user_role")
public class SysUserRole {

    // 用户id
    private String userId;

    // 角色id
    private Integer roleId;

}
