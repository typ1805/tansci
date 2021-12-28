package com.tansci.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @path：com.kuiper.qms.domain.dto.RuleDto.java
 * @className：RuleDto.java
 * @description：质检规则
 * @author：tanyp
 * @dateTime：2021/10/30 21:12 
 * @editNote：
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleDto {

    private Integer id;

    private List<Integer> ids;

    private String name;

    private List<String> userIdList;

}
