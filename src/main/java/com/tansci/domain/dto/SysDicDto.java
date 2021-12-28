package com.tansci.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @path：com.kuiper.qms.domain.SysDicDto.java
 * @className：SysDicDto.java
 * @description：字典
 * @author：tanyp
 * @dateTime：2021/7/4 13:07
 * @editNote：
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SysDicDto {

    private Integer id;

    // 父ID
    private Integer parentId;

    // 分组名称
    private String groupName;

    // 类型：0、系统，1、业务
    private Integer type;

    // 值
    private Integer value;

    // 名称
    private String label;

    // 排序
    private Integer sort;

    // 开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    // 结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    // 关键字
    private String keyword;

}
