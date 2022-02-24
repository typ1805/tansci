package com.tansci.domain.system;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "用户组织")
public class SysUserOrg {

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "组织id")
    private Integer orgId;

}
