package com.tansci.service.impl.message;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tansci.common.constant.Constants;
import com.tansci.common.exception.BusinessException;
import com.tansci.domain.message.Template;
import com.tansci.domain.message.TemplateDetails;
import com.tansci.domain.message.dto.MessageDto;
import com.tansci.domain.message.vo.MessageVo;
import com.tansci.service.message.MailService;
import com.tansci.service.message.TemplateDetailsService;
import com.tansci.service.message.TemplateService;
import com.tansci.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * @ClassName： MailServiceImpl.java
 * @ClassPath： com.tansci.service.impl.message.MailServiceImpl.java
 * @Description： 邮件
 * @Author： tanyp
 * @Date： 2021/6/7 9:18
 **/
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String sender;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateService templateService;
    @Autowired
    private TemplateDetailsService templateDetailsService;

    /**
     * @MonthName： sendSimple
     * @Description： 普通邮件发送
     * @Author： tanyp
     * @Date： 2021/6/7 9:30
     * @Param： [dto]
     * @return： void
     **/
    @Override
    public MessageVo sendSimple(MessageDto dto) {
        try {
            log.info("=======普通邮件发送开始，请求参数：{}", JSON.toJSON(dto));
            // 构建一个邮件对象
            SimpleMailMessage message = new SimpleMailMessage();
            // 设置邮件主题
            message.setSubject(dto.getSubject());
            // 设置邮件发送者，这个跟application.yml中设置的要一致
            message.setFrom(sender);
            // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
            // message.setTo("10*****16@qq.com","12****32*qq.com");
            message.setTo(dto.getRecipient());

            // 设置邮件抄送人，可以有多个抄送人
            if (Objects.nonNull(dto.getCc())) {
                message.setCc(dto.getCc());
            }

            // 设置隐秘抄送人，可以有多个
            if (Objects.nonNull(dto.getBcc())) {
                message.setBcc(dto.getBcc());
            }

            // 设置邮件发送日期
            message.setSentDate(new Date());
            // 设置邮件的正文
            Template template = templateService.getOne(Wrappers.<Template>lambdaQuery().eq(Template::getCode, dto.getCode()));
            if (Objects.isNull(template)) {
                throw new BusinessException("邮件模板编码不存在，请核实！");
            }
            dto.setText(template.getCode());
            String text = MessageUtils.assembly(dto.getText(), dto.getParam());
            if (Objects.isNull(text)) {
                throw new BusinessException("邮件模板参数有误，请核查！");
            }
            message.setText(text);
            // 发送邮件
            javaMailSender.send(message);
            log.info("=======普通邮件发送结束");

            TemplateDetails details = new TemplateDetails();
            details.setCode(dto.getCode());
            details.setState(0);
            details.setContent(JSON.toJSONString(message));
            details.setSendTime(LocalDateTime.now());
            templateDetailsService.save(details);
            return MessageVo.builder().code(Constants.NEWS_SUCCESS_CODE).message(Constants.NEWS_SUCCESS_MESSAGE).build();
        } catch (MailException e) {
            log.error("====邮件====sendSimple=====异常：{}", e);
            return MessageVo.builder().code(Constants.NEWS_FAIL_CODE).message(Constants.NEWS_FAIL_MESSAGE).build();
        }
    }

    /**
     * @MonthName： sendAttachFile
     * @Description： 带附件的邮件
     * @Author： tanyp
     * @Date： 2021/6/7 9:30
     * @Param： [dto]
     * @return： void
     **/
    @Override
    public MessageVo sendAttachFile(MessageDto dto) {
        try {
            log.info("=======带附件的邮件开始，请求参数：{}", JSON.toJSON(dto));
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            // true表示构建一个可以带附件的邮件对象
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
            message.setSubject(dto.getSubject());
            message.setFrom(sender);
            message.setTo(dto.getRecipient());
            if (Objects.nonNull(dto.getCc())) {
                message.setCc(dto.getCc());
            }
            if (Objects.nonNull(dto.getBcc())) {
                message.setBcc(dto.getBcc());
            }
            message.setSentDate(new Date());

            Template template = templateService.getOne(Wrappers.<Template>lambdaQuery().eq(Template::getCode, dto.getCode()));
            if (Objects.isNull(template)) {
                throw new BusinessException("邮件模板编码不存在，请核实！");
            }
            dto.setText(template.getContent());
            String text = MessageUtils.assembly(dto.getText(), dto.getParam());
            if (Objects.isNull(text)) {
                throw new BusinessException("邮件模板参数有误，请核查！");
            }
            message.setText(text);

            // 第一个参数是自定义的名称，后缀需要加上，第二个参数是文件的位置
            dto.getAttachments().forEach(file -> {
                try {
                    message.addAttachment(file.getName(), file);
                } catch (MessagingException e) {
                    log.error("=========邮件附件解析异常：{}", e);
                }
            });

            javaMailSender.send(mimeMessage);
            log.info("=======带附件的邮件结束");

            TemplateDetails details = new TemplateDetails();
            details.setCode(dto.getCode());
            details.setState(0);
            details.setContent(JSON.toJSONString(message));
            details.setSendTime(LocalDateTime.now());
            templateDetailsService.save(details);
            return MessageVo.builder().code(Constants.NEWS_SUCCESS_CODE).message(Constants.NEWS_SUCCESS_MESSAGE).build();
        } catch (MessagingException e) {
            log.error("==========邮件====sendAttachFile=====异常：{}", e);
            return MessageVo.builder().code(Constants.NEWS_FAIL_CODE).message(Constants.NEWS_FAIL_MESSAGE).build();
        }
    }

    /**
     * @MonthName： sendImgRes
     * @Description： 带图片资源的邮件
     * @Author： tanyp
     * @Date： 2021/6/7 9:30
     * @Param： [dto]
     * @return： void
     **/
    @Override
    public MessageVo sendImgRes(MessageDto dto) {
        try {
            log.info("=======带图片资源的邮件开始，请求参数：{}", JSON.toJSON(dto));
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
            message.setSubject(dto.getSubject());
            message.setFrom(sender);
            message.setTo(dto.getRecipient());
            if (Objects.nonNull(dto.getCc())) {
                message.setCc(dto.getCc());
            }
            if (Objects.nonNull(dto.getBcc())) {
                message.setBcc(dto.getBcc());
            }
            message.setSentDate(new Date());

            Template template = templateService.getOne(Wrappers.<Template>lambdaQuery().eq(Template::getCode, dto.getCode()));
            if (Objects.isNull(template)) {
                throw new BusinessException("邮件模板编码不存在，请核实！");
            }
            dto.setText(template.getContent());
            String text = MessageUtils.assembly(dto.getText(), dto.getParam());
            if (Objects.isNull(text)) {
                throw new BusinessException("邮件模板参数有误，请核查！");
            }
            message.setText(text);

            // 第一个参数指的是html中占位符的名字，第二个参数就是文件的位置
            dto.getAttachments().forEach(file -> {
                try {
                    message.addInline(file.getName(), new FileSystemResource(file));
                } catch (MessagingException e) {
                    log.error("=========邮件图片解析异常：{}", e);
                }
            });
            javaMailSender.send(mimeMessage);
            log.info("=======带图片资源的邮件结束");

            TemplateDetails details = new TemplateDetails();
            details.setCode(dto.getCode());
            details.setState(0);
            details.setContent(JSON.toJSONString(message));
            details.setSendTime(LocalDateTime.now());
            templateDetailsService.save(details);
            return MessageVo.builder().code(Constants.NEWS_SUCCESS_CODE).message(Constants.NEWS_SUCCESS_MESSAGE).build();
        } catch (MessagingException e) {
            log.error("====邮件====sendImgRes=====异常：{}", e);
            return MessageVo.builder().code(Constants.NEWS_FAIL_CODE).message(Constants.NEWS_FAIL_MESSAGE).build();
        }
    }


}
