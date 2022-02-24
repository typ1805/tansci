package com.tansci.domain.message.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName： TemplateParam.java
 * @ClassPath： com.tansci.domain.message.dto.TemplateParam.java
 * @Description： 短信模板参数
 * @Author： tanyp
 * @Date： 2021/6/7 12:01
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "短信模板参数")
public class TemplateParamDto implements Serializable {

    @ApiModelProperty(value = "模板参数1")
    private String param1;

    @ApiModelProperty(value = "模板参数2")
    private String param2;

    @ApiModelProperty(value = "模板参数3")
    private String param3;

    @ApiModelProperty(value = "模板参数4")
    private String param4;

    @ApiModelProperty(value = "模板参数5")
    private String param5;

    @ApiModelProperty(value = "模板参数6")
    private String param6;

    @ApiModelProperty(value = "模板参数7")
    private String param7;

    @ApiModelProperty(value = "模板参数8")
    private String param8;

    @ApiModelProperty(value = "模板参数9")
    private String param9;

    @ApiModelProperty(value = "模板参数11")
    private String param10;

    @ApiModelProperty(value = "模板参数12")
    private String param11;

    @ApiModelProperty(value = "模板参数13")
    private String param12;

    @ApiModelProperty(value = "模板参数14")
    private String param13;

    @ApiModelProperty(value = "模板参数15")
    private String param14;

    @ApiModelProperty(value = "模板参数16")
    private String param15;

    @ApiModelProperty(value = "模板参数17")
    private String param16;

    @ApiModelProperty(value = "模板参数18")
    private String param17;

    @ApiModelProperty(value = "模板参数19")
    private String param18;

    @ApiModelProperty(value = "模板参数19")
    private String param19;

    @ApiModelProperty(value = "模板参数20")
    private String param20;

}
