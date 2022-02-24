package com.tansci.domain.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName： SysUserVo.java
 * @ClassPath： com.tansci.domain.vo.SysUserVo.java
 * @Description： 用户
 * @Author： tanyp
 * @Date： 2021/10/22 16:33
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "用户VO")
public class SysUserVo {

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "凭证")
    private String token;

    @ApiModelProperty(value = "登录时间")
    private LocalDateTime loginTime;

}
