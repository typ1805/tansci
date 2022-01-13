package com.tansci.common.aop;

import com.alibaba.fastjson.JSON;
import com.tansci.common.annotation.Log;
import com.tansci.domain.system.LogErrorInfo;
import com.tansci.domain.system.LogInfo;
import com.tansci.service.system.LogErrorInfoService;
import com.tansci.service.system.LogInfoService;
import com.tansci.utils.IPUtils;
import com.tansci.utils.SecurityUserUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @path：com.tansci.common.aop.LogAspect.java
 * @className：LogAspect.java
 * @description：切面处理类，操作日志异常日志记录处理
 * @author：tanyp
 * @dateTime：2021/12/02 13:57
 * @editNote：
 */
@Aspect
@Component
public class LogAspect {

    /**
     * 操作版本号
     * 项目启动时从命令行传入，例如：java -jar xxx.war --version=201902
     */
    @Value("${version}")
    private String version;

    /**
     * 统计请求的处理时间
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Autowired
    private LogInfoService logInfoService;

    @Autowired
    private LogErrorInfoService logErrorInfoService;

    /**
     * @methodName：logPoinCut
     * @description：设置操作日志切入点 记录操作日志 在注解的位置切入代码
     * @author：tanyp
     * @dateTime：2021/12/02 14:22
     * @Params： []
     * @Return： void
     * @editNote：
     */
    @Pointcut("@annotation(com.tansci.common.annotation.Log)")
    public void logPoinCut() {
    }

    /**
     * @methodName：exceptionLogPoinCut
     * @description：设置操作异常切入点记录异常日志 扫描所有controller包下操作
     * @author：tanyp
     * @dateTime：2021/12/02 14:22
     * @Params： []
     * @Return： void
     * @editNote：
     */
    @Pointcut("execution(* com.tansci.controller..*.*(..))")
    public void exceptionLogPoinCut() {
    }

    @Before("logPoinCut()")
    public void doBefore() {
        // 接收到请求，记录请求开始时间
        startTime.set(System.currentTimeMillis());
    }

    /**
     * @methodName：doAfterReturning
     * @description：正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
     * @author：tanyp
     * @dateTime：2021/12/02 14:21
     * @Params： [joinPoint, keys]
     * @Return： void
     * @editNote：
     */
    @AfterReturning(value = "logPoinCut()", returning = "keys")
    public void doAfterReturning(JoinPoint joinPoint, Object keys) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        LogInfo logInfo = LogInfo.builder().build();
        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();

            // 获取切入点所在的方法
            Method method = signature.getMethod();

            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();

            // 获取操作
            Log log = method.getAnnotation(Log.class);
            if (Objects.nonNull(log)) {
                logInfo.setModule(log.modul());
                logInfo.setType(log.type());
                logInfo.setMessage(log.desc());
            }

            logInfo.setId(UUID.randomUUID().toString());
            logInfo.setMethod(className + "." + method.getName()); // 请求的方法名
            logInfo.setReqParam(JSON.toJSONString(converMap(request.getParameterMap()))); // 请求参数
            logInfo.setResParam(JSON.toJSONString(keys)); // 返回结果
            logInfo.setUserId(SecurityUserUtils.getUser().getId()); // 请求用户ID
            logInfo.setUserName(SecurityUserUtils.getUser().getUsername()); // 请求用户名称
            logInfo.setIp(IPUtils.getIpAddress(request)); // 请求IP
            logInfo.setUri(request.getRequestURI()); // 请求URI
            logInfo.setCreateTime(LocalDateTime.now()); // 创建时间
            logInfo.setVersion(version); // 操作版本
            logInfo.setTakeUpTime(System.currentTimeMillis() - startTime.get()); // 耗时
            logInfoService.save(logInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @methodName：doAfterThrowing
     * @description：异常返回通知，用于拦截异常日志信息 连接点抛出异常后执行
     * @author：tanyp
     * @dateTime：2021/12/02 14:23
     * @Params： [joinPoint, e]
     * @Return： void
     * @editNote：
     */
    @AfterThrowing(pointcut = "exceptionLogPoinCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();

            // 获取切入点所在的方法
            Method method = signature.getMethod();

            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();

            logErrorInfoService.save(
                    LogErrorInfo.builder()
                            .id(UUID.randomUUID().toString())
                            .reqParam(JSON.toJSONString(converMap(request.getParameterMap()))) // 请求参数
                            .method(className + "." + method.getName()) // 请求方法名
                            .name(e.getClass().getName()) // 异常名称
                            .message(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace())) // 异常信息
                            .userId(SecurityUserUtils.getUser().getId()) // 操作员ID
                            .userName(SecurityUserUtils.getUser().getUsername()) // 操作员名称
                            .uri(request.getRequestURI()) // 操作URI
                            .ip(IPUtils.getIpAddress(request)) // 操作员IP
                            .version(version) // 版本号
                            .createTime(LocalDateTime.now()) // 发生异常时间
                            .build()
            );
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /**
     * @methodName：converMap
     * @description：转换request 请求参数
     * @author：tanyp
     * @dateTime：2021/12/02 14:12
     * @Params： [paramMap]
     * @Return： java.util.Map<java.lang.String, java.lang.String>
     * @editNote：
     */
    public Map<String, String> converMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<String, String>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
    }

    /**
     * @methodName：stackTraceToString
     * @description：转换异常信息为字符串
     * @author：tanyp
     * @dateTime：2021/12/02 14:12
     * @Params： [exceptionName, exceptionMessage, elements]
     * @Return： java.lang.String
     * @editNote：
     */
    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strbuff = new StringBuffer();
        for (StackTraceElement stet : elements) {
            strbuff.append(stet + "<br/>");
        }
        String message = exceptionName + ":" + exceptionMessage + "<br/>" + strbuff.toString();
        return message;
    }
}
