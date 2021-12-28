package com.tansci.domain.vo;

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
public class SysUserVo {

    // 用户名称
    private String username;

    // 用户昵称
    private String nickname;

    // 凭证
    private String token;

    // 登录时间
    private LocalDateTime loginTime;

}
