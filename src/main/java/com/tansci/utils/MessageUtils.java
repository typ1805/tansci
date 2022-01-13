package com.tansci.utils;

import com.tansci.domain.message.dto.TemplateParamDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName： MessageUtils.java
 * @ClassPath： com.tansci.utils.MessageUtils.java
 * @Description： 消息工具类
 * @Author： tanyp
 * @Date： 2021/6/15 9:21
 **/
@Slf4j
public class MessageUtils {
    private static final Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}");
    private static Matcher matcher;

    /**
     * @MonthName： assembly
     * @Description： 组装数据
     * @Author： tanyp
     * @Date： 2021/6/15 9:24
     * @Param： [content, dto]
     * @return： java.lang.String
     **/
    public static String assembly(String content, TemplateParamDto dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        try {
            matcher = pattern.matcher(content);
            while (matcher.find()) {
                String key = matcher.group();
                String keyclone = key.substring(2, key.length() - 1).trim();
                Field field = dto.getClass().getDeclaredField(keyclone);
                field.setAccessible(true);
                Object value = field.get(dto);
                if (value != null) {
                    content = content.replace(key, value.toString());
                }
            }
            return content;
        } catch (Exception e) {
            log.error("=====发送消息，组装数据异常，=========：{}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @MonthName： countStr
     * @Description： 判断str中包含key的个数
     * @Author： tanyp
     * @Date： 2021/6/15 9:34
     * @Param： [str1, str2]
     * @return： java.lang.Integer
     **/
    public static Integer countStr(String str, String key) {
        if (str == null || key == null || "".equals(str.trim()) || "".equals(key.trim())) {
            return 0;
        }
        int count = 0;
        int index = 0;
        while ((index = str.indexOf(key, index)) != -1) {
            index = index + key.length();
            count++;
        }
        return count;
    }

    /**
     * @MonthName： getIP
     * @Description： 获取URL的域名或IP与端口
     * @Author： tanyp
     * @Date： 2021/7/19 10:25
     * @Param： [url]
     * @return： java.lang.String
     **/
    public static String getIP(String url) {
        String re = "((http|ftp|https)://)(([a-zA-Z0-9._-]+)|([0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}))(([a-zA-Z]{2,6})|(:[0-9]{1,4})?)";
        String str = "";
        Pattern pattern = Pattern.compile(re);
        Matcher matcher = pattern.matcher(url);
        if (matcher.matches()) {
            str = url;
        } else {
            String[] split2 = url.split(re);
            if (split2.length > 1) {
                String substring = url.substring(0, url.length() - split2[1].length());
                str = substring;
            } else {
                str = split2[0];
            }
        }
        return str;
    }

    /**
     * @MonthName： getShortUrl
     * @Description： 生成唯一的短链接
     * @Author： tanyp
     * @Date： 2021/7/19 11:00
     * @Param： [url]
     * @return： java.lang.String
     **/
    public static String getShortUrl(String url) {
        String key = "";
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String sMD5EncryptResult = DigestUtils.md5DigestAsHex((key + url).getBytes());
        String hex = sMD5EncryptResult;
        String[] resUrl = new String[4];
        for (int i = 0; i < 4; i++) {
            String sTempSubString = hex.substring(i * 8, i * 8 + 8);
            long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
            String outChars = "";
            for (int j = 0; j < 6; j++) {
                long index = 0x0000003D & lHexLong;
                outChars += chars[(int) index];
                lHexLong = lHexLong >> 5;
            }
            resUrl[i] = outChars;
        }
        return resUrl[0];
    }

}
