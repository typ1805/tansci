package com.tansci.domain.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @path：com.kuiper.qms.domain.dto.SysUserDto.java
 * @className：SysUserDto.java
 * @description：用户DTO
 * @author：tanyp
 * @dateTime：2021/6/19 22:28
 * @editNote：
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SysUserDto {

    private String id;

    // 用户名称
    private String username;

    // 用户昵称
    private String nickname;

    // 新密码
    private String password;

    // 旧密码
    private String oldPassword;

    private List<Integer> orgIds;

    // 开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    // 结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

}
