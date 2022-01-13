package com.tansci.mapper.message;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.message.Template;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName： TemplateMapper.java
 * @ClassPath： com.tansci.mapper.message.TemplateMapper.java
 * @Description： 模板配置
 * @Author： tanyp
 * @Date： 2021/4/22 10:34
 **/
@Mapper
public interface TemplateMapper extends BaseMapper<Template> {
}
