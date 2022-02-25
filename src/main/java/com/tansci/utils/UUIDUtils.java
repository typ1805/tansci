package com.tansci.utils;

import java.util.UUID;

/**
 * @ClassName： UUIDUtils.java
 * @ClassPath： com.tansci.utils.UUIDUtils.java
 * @Description： 工具类
 * @Author： tanyp
 * @Date： 2021/6/8 11:21
 **/
public class UUIDUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getUUID(Integer len) {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, len);
    }

}
