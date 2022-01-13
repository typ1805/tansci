package com.tansci.service.impl.message;

import com.alibaba.fastjson.JSON;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.models.Config;
import com.tansci.common.constant.Constants;
import com.tansci.config.SmsConfig;
import com.tansci.domain.message.TemplateDetails;
import com.tansci.domain.message.dto.MessageDto;
import com.tansci.domain.message.dto.SmsTemplateDto;
import com.tansci.domain.message.vo.MessageVo;
import com.tansci.service.message.SmsService;
import com.tansci.service.message.TemplateDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * @ClassName： SmsServiceImpl.java
 * @ClassPath： com.tansci.service.impl.messag.SmsServiceImpl.java
 * @Description： 阿里云短信
 * @Author： tanyp
 * @Date： 2021/6/7 10:57
 **/
@Slf4j
@Service
public class AliSmsServiceImpl implements SmsService {

    @Autowired
    private SmsConfig smsConfig;

    @Autowired
    private TemplateDetailsService templateDetailsService;

    /**
     * @MonthName： createClient
     * @Description： SK初始化账号Client
     * @Author： tanyp
     * @Date： 2021/6/7 15:44
     * @Param： [accessId, accessKey, endpoint]
     * @return： com.aliyun.teaopenapi.Client
     **/
    public Client createClient() throws Exception {
        Config config = new Config();
        config.accessKeyId = smsConfig.getAccessId();
        config.accessKeySecret = smsConfig.getAccessKey();
        config.endpoint = smsConfig.getEndpoint();
        return new Client(config);
    }

    /**
     * @MonthName： send
     * @Description： 发短信
     * @Author： tanyp
     * @Date： 2021/6/7 14:50
     * @Param： [dto]
     * @return： MessageVo
     **/
    @Override
    public MessageVo send(MessageDto dto) {
        try {
            log.info("======发送短信开始，请求参数：{}", JSON.toJSON(dto));
            Client client = createClient();

            // 组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            // 外部流水扩展字段
            String outId = UUID.randomUUID().toString();
            request.setOutId(outId);
            // 支持对多个手机号码发送短信，手机号码之间以英文逗号（,）分隔。上限为1000个手机号码。批量调用相对于单条调用及时性稍有延迟。
            request.setPhoneNumbers(dto.getPhone());
            // 短信签名名称
            request.setSignName(smsConfig.getSignName());
            // 短信模板ID
            request.setTemplateCode(dto.getCode());
            // 短信模板变量对应的实际值，JSON格式。如果JSON中需要带换行符，请参照标准的JSON协议处理。
            request.setTemplateParam(JSON.toJSONString(dto.getParam()));

            // 发送短信
            SendSmsResponse res = client.sendSms(request);
            TemplateDetails details = new TemplateDetails();
            MessageVo message = MessageVo.builder().build();
            if (Objects.equals(Constants.NEWS_SUCCESS_CODE, res.body.getCode())) {
                log.info("======发送短信成功，返回值：{}", JSON.toJSON(res.body));
                message.setCode(Constants.NEWS_SUCCESS_CODE);
                message.setMessage(Constants.NEWS_SUCCESS_MESSAGE);
                details.setState(0);
            } else {
                log.info("======发送短信失败，返回值：{}", JSON.toJSON(res.body));
                message.setCode(Constants.NEWS_FAIL_CODE);
                message.setMessage(Constants.NEWS_FAIL_MESSAGE);
                details.setState(1);
            }
            details.setCode(dto.getCode());
            details.setContent(JSON.toJSONString(request));
            details.setSendTime(LocalDateTime.now());
            templateDetailsService.save(details);
            return message;
        } catch (Exception e) {
            log.error("======发送短信异常：{}", e.getMessage());
            e.printStackTrace();
            return MessageVo.builder().code(Constants.NEWS_FAIL_CODE).message(Constants.NEWS_FAIL_MESSAGE).build();
        }
    }

    /**
     * @MonthName： addSmsTemplate
     * @Description： 申请短信模板
     * @Author： tanyp
     * @Date： 2021/6/7 14:50
     * @Param： [template]
     * @return： MessageVo
     **/
    @Override
    public MessageVo addSmsTemplate(SmsTemplateDto template) {
        try {
            log.info("======申请短信模板，请求参数：{}", JSON.toJSON(template));
            Client client = createClient();
            AddSmsTemplateRequest request = new AddSmsTemplateRequest();
            request.setTemplateType(template.getTemplateType());
            request.setTemplateName(template.getTemplateName());
            request.setTemplateContent(template.getTemplateContent());
            request.setRemark(template.getRemark());

            AddSmsTemplateResponse res = client.addSmsTemplate(request);
            if (Objects.equals(Constants.NEWS_SUCCESS_CODE, res.body.getCode())) {
                log.info("======申请短信模板，返回值：{}", JSON.toJSON(res.body));
                return MessageVo.builder()
                        .code(Constants.NEWS_SUCCESS_CODE)
                        .message(Constants.NEWS_SUCCESS_MESSAGE)
                        .templateCode(res.getBody().templateCode)
                        .build();
            } else {
                log.info("======申请短信模板，返回值：{}", JSON.toJSON(res.body));
                return MessageVo.builder().code(Constants.NEWS_FAIL_CODE).message(Constants.NEWS_FAIL_MESSAGE).build();
            }
        } catch (Exception e) {
            log.error("======申请短信模板，异常：{}", e.getMessage());
            e.printStackTrace();
            return MessageVo.builder().code(Constants.NEWS_FAIL_CODE).message(Constants.NEWS_FAIL_MESSAGE).build();
        }
    }

    /**
     * @MonthName： deleteSmsTemplate
     * @Description： 删除短信模板
     * @Author： tanyp
     * @Date： 2021/6/7 14:50
     * @Param： [template]
     * @return： MessageVo
     **/
    @Override
    public MessageVo deleteSmsTemplate(SmsTemplateDto template) {
        try {
            log.info("======删除短信模板，请求参数：{}", JSON.toJSON(template));
            Client client = createClient();
            DeleteSmsTemplateRequest request = new DeleteSmsTemplateRequest();
            request.setTemplateCode(template.getTemplateCode());

            DeleteSmsTemplateResponse res = client.deleteSmsTemplate(request);
            if (Objects.equals(Constants.NEWS_SUCCESS_CODE, res.body.getCode())) {
                log.info("======删除短信模板，返回值：{}", JSON.toJSON(res.body));
                return MessageVo.builder().code(Constants.NEWS_SUCCESS_CODE).message(Constants.NEWS_SUCCESS_MESSAGE).build();
            } else {
                log.info("======删除短信模板，返回值：{}", JSON.toJSON(res.body));
                return MessageVo.builder().code(Constants.NEWS_FAIL_CODE).message(Constants.NEWS_FAIL_MESSAGE).build();
            }
        } catch (Exception e) {
            log.error("======删除短信模板，异常：{}", e);
            return MessageVo.builder().code(Constants.NEWS_FAIL_CODE).message(Constants.NEWS_FAIL_MESSAGE).build();
        }
    }

    /**
     * @MonthName： modifySmsTemplate
     * @Description： 修改未通过审核的短信模板
     * @Author： tanyp
     * @Date： 2021/6/7 14:50
     * @Param： [template]
     * @return： MessageVo
     **/
    @Override
    public MessageVo modifySmsTemplate(SmsTemplateDto template) {
        try {
            log.info("======修改未通过审核的短信模板，请求参数：{}", JSON.toJSON(template));
            Client client = createClient();
            ModifySmsTemplateRequest request = new ModifySmsTemplateRequest();
            request.setTemplateType(template.getTemplateType());
            request.setTemplateName(template.getTemplateName());
            request.setTemplateCode(template.getTemplateCode());
            request.setTemplateContent(template.getTemplateContent());
            request.setRemark(template.getRemark());

            ModifySmsTemplateResponse res = client.modifySmsTemplate(request);
            if (Objects.equals(Constants.NEWS_SUCCESS_CODE, res.body.getCode())) {
                log.info("======修改未通过审核的短信模板，返回值：{}", JSON.toJSON(res.body));
                return MessageVo.builder().code(Constants.NEWS_SUCCESS_CODE).message(Constants.NEWS_SUCCESS_MESSAGE).build();
            } else {
                log.info("======修改未通过审核的短信模板，返回值：{}", JSON.toJSON(res.body));
                return MessageVo.builder().code(Constants.NEWS_FAIL_CODE).message(Constants.NEWS_FAIL_MESSAGE).build();
            }
        } catch (Exception e) {
            log.error("======修改未通过审核的短信模板，异常：{}", e.getMessage());
            e.printStackTrace();
            return MessageVo.builder().code(Constants.NEWS_FAIL_CODE).message(Constants.NEWS_FAIL_MESSAGE).build();
        }
    }

    /**
     * @MonthName： querySmsTemplate
     * @Description： 查询短信模板的审核状态
     * @Author： tanyp
     * @Date： 2021/6/7 14:50
     * @Param： [template]
     * @return： MessageVo
     **/
    @Override
    public MessageVo querySmsTemplate(SmsTemplateDto template) {
        try {
            log.info("======查询短信模板的审核状态，请求参数：{}", JSON.toJSON(template));
            Client client = createClient();
            QuerySmsTemplateRequest request = new QuerySmsTemplateRequest();
            request.setTemplateCode(template.getTemplateCode());

            QuerySmsTemplateResponse res = client.querySmsTemplate(request);
            if (Objects.equals(Constants.NEWS_SUCCESS_CODE, res.body.getCode())) {
                log.info("======查询短信模板的审核状态，返回值：{}", JSON.toJSON(res.body));
                return MessageVo.builder()
                        .code(Constants.NEWS_SUCCESS_CODE)
                        .message(Constants.NEWS_SUCCESS_MESSAGE)
                        .state(res.body.getTemplateStatus())
                        .templateCode(res.body.templateCode)
                        .reason(res.body.reason)
                        .build();
            } else {
                log.info("======查询短信模板的审核状态，返回值：{}", JSON.toJSON(res.body));
                return MessageVo.builder().code(Constants.NEWS_FAIL_CODE).message(Constants.NEWS_FAIL_MESSAGE).build();
            }
        } catch (Exception e) {
            log.error("======查询短信模板的审核状态，异常：{}", e.getMessage());
            e.printStackTrace();
            return MessageVo.builder().code(Constants.NEWS_FAIL_CODE).message(Constants.NEWS_FAIL_MESSAGE).build();
        }
    }

}
