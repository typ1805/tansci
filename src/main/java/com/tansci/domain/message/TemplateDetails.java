package com.tansci.domain.message;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
public class TemplateDetails extends Model<TemplateDetails> {

    // 主键id
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    // 模板编码
    private String code;

    // 模板内容
    private String content;

    // 状态：0、成功，1：失败
    private Integer state;

    // 发送时间
    private LocalDateTime sendTime;

}