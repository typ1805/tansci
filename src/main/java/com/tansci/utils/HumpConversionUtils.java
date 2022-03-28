package com.tansci.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @path：com.tansci.utils.HumpConversionUtils.java
 * @className：HumpConversionUtils.java
 * @description：驼峰规范属性转下划线 (Bean 》 Map)
 * 支持递归转换，如属性为JavaBean时
 * @author：tanyp
 * @dateTime：2022/03/28 11:49
 * @editNote：
 */
public class HumpConversionUtils {

    private static String compile = "[A-Z]";

    private HumpConversionUtils() {
    }

    /**
     * @methodName：transform
     * @description：驼峰转下划线（对象）
     * @author：tanyp
     * @dateTime：2022/03/28 11:49
     * @Params： [object]
     * @Return： java.util.Map
     * @editNote：
     */
    public static <T> Map transform(T object) {
        if (object == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                String fieldName = fields[i].getName();
                if ("serialVersionUID".equalsIgnoreCase(fieldName)) {
                    continue;
                }
                // 转换驼峰形式属性名称成下划线风格，获取map的key 例：fieldName 》 field_name
                String transformFieldName = HumpConversionUtils.getTransformFieldName(fieldName);
                Object FieldValue = null;
                String name = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                String type = fields[i].getGenericType().toString();
                Method m = object.getClass().getMethod("get" + name);
                switch (type) {
                    // 如果有需要,可以仿照下面继续进行扩充,再增加对其它类型的判断
                    case "class java.lang.String":
                    case "class java.lang.Boolean":
                    case "class java.util.Date":
                    case "class java.lang.Integer":
                    case "class java.lang.Long":
                        // 调用getter方法获取属性值
                        FieldValue = m.invoke(object);
                        break;
                    default:
                        // 属性类型为bean,则递归
                        Object obj = m.invoke(object);
                        FieldValue = HumpConversionUtils.transform(obj);
                }
                map.put(transformFieldName, FieldValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * @methodName：getTransformFieldName
     * @description：驼峰转下划线（字符串）
     * @author：tanyp
     * @dateTime：2022/03/28 11:49
     * @Params： [fieldName:属性名称]
     * @Return： java.lang.String
     * @editNote：
     */
    private static String getTransformFieldName(String fieldName) {
        Pattern humpPattern = Pattern.compile(compile);
        Matcher matcher = humpPattern.matcher(fieldName);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}