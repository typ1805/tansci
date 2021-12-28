package com.tansci.common.exception;

import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName： ExceptionHandle.java
 * @ClassPath： com.tansci.common.exception.ExceptionHandle.java
 * @Description： 全局异常统一处理
 * @Author： tanyp
 * @Date： 2021/10/22 17:05
 **/
@Slf4j
@RestControllerAdvice
public class ExceptionHandle {

    /**
     * @MonthName： handleException
     * @Description： 统一的异常处理方法
     * @Author： tanyp
     * @Date： 2021/10/22 17:16
     * @Param： [e]
     * @return： com.kuiper.qms.common.Wrapper
     **/
    @ExceptionHandler(value = Exception.class)
    public Wrapper handleException(Exception e) {
        if (e instanceof BusinessException) {
            BusinessException ex = (BusinessException) e;
            log.error("系统自定义业务异常：{}", ex.getMessage());
            return WrapMapper.wrap(ex.getCode(), ex.getMessage(), null);
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            log.error("参数校验异常：{}", ex.getBindingResult().getFieldError().getDefaultMessage());
            return WrapMapper.wrap(Wrapper.ILLEGAL_ARGUMENT_CODE_, "参数有误：" + ex.getBindingResult().getFieldError().getDefaultMessage(), null);
        } else {
            log.error("统一系统异常：{}", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE, null);
        }
    }

}
