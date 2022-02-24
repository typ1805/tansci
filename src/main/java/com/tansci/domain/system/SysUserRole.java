package com.tansci.domain.system;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "用户角色")
public class SysUserRole {

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

}
