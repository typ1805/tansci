package com.tansci.service.impl.message;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.message.TemplateDetails;
import com.tansci.mapper.message.TemplateDetailsMapper;
import com.tansci.service.message.TemplateDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName： TemplateDetailsServiceImpl.java
 * @ClassPath： com.tansci.service.impl.message.TemplateDetailsServiceImpl.java
 * @Description： 模板详情
 * @Author： tanyp
 * @Date： 2021/6/7 18:08
 **/
@Slf4j
@Service
public class TemplateDetailsServiceImpl extends ServiceImpl<TemplateDetailsMapper, TemplateDetails> implements TemplateDetailsService {

}
