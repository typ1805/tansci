package com.tansci.service.message;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.message.TemplateDetails;

/**
 * @ClassName： TemplateDetailsService.java
 * @ClassPath： com.tansci.service.message.TemplateDetailsService.java
 * @Description： 模板详情
 * @Author： tanyp
 * @Date： 2021/6/7 18:08
 **/
public interface TemplateDetailsService extends IService<TemplateDetails> {

    IPage<TemplateDetails> page(Page page, TemplateDetails details);

}
