package com.tansci.service.message;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.message.Template;
import com.tansci.domain.message.dto.TemplateDto;

/**
 * @ClassName： TemplateService
 * @ClassPath： com.tansci.service.message.TemplateService.java
 * @Description： 模板配置
 * @Author： tanyp
 * @Date： 2021/4/22 10:34
 **/
public interface TemplateService extends IService<Template> {

    IPage<Template> page(Page page, TemplateDto dto);

    Integer saveTemplate(Template template);

    Integer updateTemplate(Template template);

    Integer delTemplate(String id);
}
