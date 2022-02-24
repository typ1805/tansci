package com.tansci.service.impl.message;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.common.constant.Constants;
import com.tansci.common.exception.BusinessException;
import com.tansci.domain.message.Template;
import com.tansci.domain.message.dto.SmsTemplateDto;
import com.tansci.domain.message.dto.TemplateDto;
import com.tansci.domain.message.vo.MessageVo;
import com.tansci.domain.system.SysDic;
import com.tansci.mapper.message.TemplateMapper;
import com.tansci.service.message.SmsService;
import com.tansci.service.message.TemplateService;
import com.tansci.service.system.SysDicService;
import com.tansci.utils.SecurityUserUtils;
import com.tansci.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @ClassName： TemplateServiceImpl.java
 * @ClassPath： com.tansci.service.impl.message.TemplateServiceImpl.java
 * @Description： 模板配置
 * @Author： tanyp
 * @Date： 2021/4/22 10:34
 **/
@Slf4j
@Service
public class TemplateServiceImpl extends ServiceImpl<TemplateMapper, Template> implements TemplateService {

    @Autowired
    @Qualifier("aliSmsServiceImpl")
    private SmsService aliSmsServiceImpl;

    @Autowired
    private SysDicService sysDicService;

    @Override
    public IPage<Template> page(Page page, TemplateDto dto) {
        IPage<Template> pageList = this.baseMapper.selectPage(page,
                Wrappers.<Template>lambdaQuery()
                        .eq(Template::getDelFlag, 0)
                        .eq(Objects.nonNull(dto.getType()), Template::getType, dto.getType())
                        .eq(Objects.nonNull(dto.getBusinessType()), Template::getBusinessType, dto.getBusinessType())
                        .like(Objects.nonNull(dto.getName()), Template::getName, dto.getName())
                        .orderByDesc(Template::getCreateTime)
        );

        List<SysDic> tDicList = new ArrayList<>();
        List<SysDic> tbDicList = new ArrayList<>();
        List<SysDic> sDicList = new ArrayList<>();
        if (Objects.nonNull(pageList.getRecords()) || pageList.getRecords().size() > 0) {
            tDicList = sysDicService.list(Wrappers.<SysDic>lambdaQuery().ne(SysDic::getDicValue, -1).eq(SysDic::getGroupName, "template_type").orderByAsc(SysDic::getSort));
            sDicList = sysDicService.list(Wrappers.<SysDic>lambdaQuery().ne(SysDic::getDicValue, -1).eq(SysDic::getGroupName, "template_state").orderByAsc(SysDic::getSort));
            tbDicList = sysDicService.list(Wrappers.<SysDic>lambdaQuery().ne(SysDic::getDicValue, -1).eq(SysDic::getGroupName, "template_business_type").orderByAsc(SysDic::getSort));
        }

        List<SysDic> finalTDicList = tDicList;
        List<SysDic> finalTbDicList = tbDicList;
        List<SysDic> finalSDicList = sDicList;
        pageList.getRecords().forEach(item -> {
            Optional<SysDic> tOptional = finalTDicList.stream().filter(t -> Objects.equals(item.getType(), t.getDicValue())).findFirst();
            if (tOptional.isPresent()) {
                item.setTypeName(tOptional.get().getDicLabel());
            }
            Optional<SysDic> tdOptional = finalTbDicList.stream().filter(t -> Objects.equals(item.getBusinessType(), t.getDicValue())).findFirst();
            if (tdOptional.isPresent()) {
                item.setBusinessTypeName(tdOptional.get().getDicLabel());
            }
            Optional<SysDic> sOptional = finalSDicList.stream().filter(t -> Objects.equals(item.getState(), t.getDicValue())).findFirst();
            if (sOptional.isPresent()) {
                item.setStateName(sOptional.get().getDicLabel());
            }
        });
        return pageList;
    }

    @Transactional
    @Override
    public Integer saveTemplate(Template template) {
        log.info("=========saveTemplate:{}", JSON.toJSON(template));
        String id = UUIDUtils.getUUID();
        template.setId(id);
        template.setDelFlag(0);
        template.setCreater(SecurityUserUtils.getUser().getId());
        template.setUpdateTime(LocalDateTime.now());
        template.setCreateTime(LocalDateTime.now());
        int row = 0;
        switch (template.getBusinessType()) {
            case 0:
                // 短信
                template.setState(0);
                row = this.baseMapper.insert(template);
                if (row > 0) {
                    // 请求阿里云短信API，添加模板
                    MessageVo message = aliSmsServiceImpl.addSmsTemplate(
                            SmsTemplateDto.builder()
                                    .templateType(template.getType())
                                    .templateName(template.getName())
                                    .templateContent(template.getContent())
                                    .remark(template.getRemark())
                                    .build()
                    );

                    // 更新模板
                    Template tem = new Template();
                    tem.setId(id);
                    if (message.getCode().equals(Constants.NEWS_SUCCESS_CODE)) {
                        tem.setCode(message.getTemplateCode());
                        tem.setState(0);
                    } else {
                        tem.setState(2);
                    }
                    tem.setUpdateTime(LocalDateTime.now());
                    this.updateById(tem);
                }
                break;
            case 1:
                // 邮件
                template.setState(1);
                template.setCode("YJ" + System.currentTimeMillis());
                row = this.baseMapper.insert(template);
                break;
            default:
                throw new BusinessException("添加失败，业务类型不存在！");
        }
        return row;
    }

    @Transient
    @Override
    public Integer updateTemplate(Template template) {
        log.info("=========updateTemplate:{}", JSON.toJSON(template));
        template.setUpdateTime(LocalDateTime.now());
        int row = 0;
        switch (template.getBusinessType()) {
            case 0:
                // 短信
                template.setState(0);
                row = this.baseMapper.updateById(template);
                if (row > 0) {
                    // 请求阿里云短信API，修改模板
                    MessageVo message = aliSmsServiceImpl.modifySmsTemplate(
                            SmsTemplateDto.builder()
                                    .templateCode(template.getCode())
                                    .templateType(template.getType())
                                    .templateName(template.getName())
                                    .templateContent(template.getContent())
                                    .remark(template.getRemark())
                                    .build()
                    );

                    // 更新模板
                    if (!message.getCode().equals(Constants.NEWS_SUCCESS_CODE)) {
                        template.setState(2);
                        template.setUpdateTime(LocalDateTime.now());
                        this.baseMapper.updateById(template);
                    }
                }
                break;
            case 1:
                // 邮件
                template.setState(1);
                row = this.baseMapper.updateById(template);
                break;
            default:
                throw new BusinessException("编辑失败，业务类型不存在！");
        }
        return row;
    }

    @Transient
    @Override
    public Integer delTemplate(String id) {
        log.info("=========delTemplate:{}", id);
        Template template = this.baseMapper.selectById(id);
        if (Objects.isNull(template)) {
            throw new BusinessException("删除失败，模板不存在！");
        }

        int row = 0;
        switch (template.getBusinessType()) {
            case 0:
                // 请求阿里云短信API，修改模板
                MessageVo message = aliSmsServiceImpl.deleteSmsTemplate(SmsTemplateDto.builder().templateCode(template.getCode()).build());

                // 更新模板
                if (!message.getCode().equals(Constants.NEWS_SUCCESS_CODE)) {
                    template.setDelFlag(1);
                    template.setUpdateTime(LocalDateTime.now());
                    this.baseMapper.updateById(template);
                }
                break;
            case 1:
                // 邮件
                template.setDelFlag(1);
                row = this.baseMapper.updateById(template);
                break;
            default:
                throw new BusinessException("删除失败，业务类型不存在！");
        }
        return row;
    }

}
