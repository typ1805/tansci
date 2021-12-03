package com.tansci.common;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * @path：com.tansci.common.WrapMapper.java
 * @className：WrapMapper.java
 * @description：返回包装类
 * @author：tanyp
 * @dateTime：2021/12/02 15:38
 * @editNote：
 */
public class WrapMapper {

    private WrapMapper() {
    }

    public static <E> Wrapper<E> wrap(int code, String message, E o) {
        return new Wrapper<E>(code, message, o);
    }

    public static <E> Wrapper<E> wrap(int code, String message) {
        return new Wrapper<E>(code, message);
    }

    public static <E> Wrapper<E> wrap(int code) {
        return wrap(code, null);
    }

    public static <E> Wrapper<E> wrap(Exception e) {
        return new Wrapper<E>(Wrapper.ERROR_CODE, e.getMessage());
    }

    public static <E> E unWrap(Wrapper<E> wrapper) {
        return wrapper.getResult();
    }

    public static <E> Wrapper<E> illegalArgument() {
        return wrap(Wrapper.ILLEGAL_ARGUMENT_CODE_, Wrapper.ILLEGAL_ARGUMENT_MESSAGE);
    }

    public static <E> Wrapper<E> error() {
        return wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE);
    }

    public static <E> Wrapper<E> error(String message) {
        return wrap(Wrapper.ERROR_CODE, StringUtils.isBlank(message) ? Wrapper.ERROR_MESSAGE : message);
    }

    public static <E> Wrapper<E> error(int code, String message) {
        return wrap(code, StringUtils.isBlank(message) ? Wrapper.ERROR_MESSAGE : message);
    }

    public static <E> Wrapper<E> ok() {
        return new Wrapper<E>();
    }

    public static <E> Wrapper<E> wrap(E o) {
        return new Wrapper<>(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, o);
    }

    public static <E> Wrapper<E> ok(E o) {
        return new Wrapper<>(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, o);
    }

    public static <E> Wrapper<E> success() {
        return new Wrapper<>(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE);
    }

}
