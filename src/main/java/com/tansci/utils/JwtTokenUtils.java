package com.tansci.utils;

import com.alibaba.fastjson.JSONObject;
import com.tansci.domain.system.SysUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName： JwtTokenUtils.java
 * @ClassPath： com.tansci.utils.JwtTokenUtils.java
 * @Description： JWT工具类
 * @Author： tanyp
 * @Date： 2021/12/02 17:38
 **/
public class JwtTokenUtils {

    public static final String TOKEN_HEADER = "Authorization";

    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SECRET = "jwtsecretdemo";

    private static final String ISS = "echisan";

    // 角色的key
    private static final String ROLE_CLAIMS = "rol";

    // 用户信息key
    private static final String USER_CLAIMS = "user";

    // 过期时间是3600*4秒，既4个小时
    private static final long EXPIRATION = 14400L;

    // 选择了记住我之后的过期时间为7天
    private static final long EXPIRATION_REMEMBER = 604800L;

    /**
     * @MonthName： createToken
     * @Description： 创建token
     * @Author： tanyp
     * @Date： 2021/12/02 10:40
     * @Param： [username, isRememberMe]
     * @return： java.lang.String
     **/
    public static String createToken(SysUser user, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        Map<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, user.getRole());
        map.put(USER_CLAIMS, user);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setClaims(map)
                .setIssuer(ISS)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    /**
     * @MonthName： getUsername
     * @Description： 从token中获取用户名
     * @Author： tanyp
     * @Date： 2021/12/02 10:41
     * @Param： [token]
     * @return： java.lang.String
     **/
    public static String getUsername(String token) {
        return getTokenBody(token).getSubject();
    }

    /**
     * @MonthName： getUserRole
     * @Description： 获取用户角色
     * @Author： tanyp
     * @Date： 2021/12/02 13:00
     * @Param： [token]
     * @return： java.lang.String
     **/
    public static String getUserRole(String token) {
        return (String) getTokenBody(token).get(ROLE_CLAIMS);
    }

    /**
     * @MonthName： getUser
     * @Description： 获取用户信息
     * @Author： tanyp
     * @Date： 2021/12/02 13:00
     * @Param： [token]
     * @return： java.lang.String
     **/
    public static SysUser getUser(String token) {
        Object obj = getTokenBody(token).get(USER_CLAIMS);
        return JSONObject.parseObject(JSONObject.toJSONBytes(obj), SysUser.class);
    }

    /**
     * @MonthName： isExpiration
     * @Description： 是否已过期
     * @Author： tanyp
     * @Date： 2021/12/02 10:41
     * @Param： [token]
     * @return： boolean
     **/
    public static boolean isExpiration(String token) {
        try {
            return getTokenBody(token).getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }

    }

    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

}
