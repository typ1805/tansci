package com.tansci.domain.message;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName： TemplateDetails.java
 * @ClassPath： com.tansci.domain.message.TemplateDetails.java
 * @Description： 模板详情
 * @Author： tanyp
 * @Date： 2021/6/7 18:03
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("template_details")
@ApiModel(value = "模板详情")
public class TemplateDetails extends Model<TemplateDetails> {

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "模板编码")
    private String code;

    @ApiModelProperty(value = "模板内容")
    private String content;

    @ApiModelProperty(value = "状态：0、成功，1：失败")
    private Integer state;

    @ApiModelProperty(value = "状态")
    @TableField(exist = false)
    private String stateName;

    @ApiModelProperty(value = "发送时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime sendTime;

}
