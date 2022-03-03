package com.tansci.domain.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName： AuthorizedVo.java
 * @ClassPath： com.tansci.domain.system.vo.AuthorizedVo.java
 * @Description： 三方授权登录VO
 * @Author： tanyp
 * @Date： 2022/3/3 11:36
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "三方授权登录VO")
public class AuthorizedVo {

    @ApiModelProperty(value = "随机数")
    private String state;

    @ApiModelProperty(value = "二维码")
    private String qrcode;

    private String msg;

    private Integer status;

    private String username;

    private String nickname;

    private String token;

    private LocalDateTime loginTime;

}
