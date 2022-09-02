package com.tansci.service.impl.message;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.common.constant.Enums;
import com.tansci.domain.message.TemplateDetails;
import com.tansci.mapper.message.TemplateDetailsMapper;
import com.tansci.service.message.TemplateDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

    @Override
    public IPage<TemplateDetails> page(Page page, TemplateDetails details) {
        IPage<TemplateDetails> iPage = this.baseMapper.selectPage(page,
                Wrappers.lambdaQuery()
        );

        if (Objects.nonNull(iPage.getRecords()) && iPage.getRecords().size() > 0) {
            iPage.getRecords().forEach(item -> {
                item.setStateName(Objects.nonNull(item.getState()) ? Enums.getVlaueByGroup(item.getState(), "message_log_status") : null);
            });
        }
        return iPage;
    }

}
