package cn.toy.www.security;


import cn.toy.www.util.HttpUtils;
import cn.toy.www.util.UUIDHelper;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.util.encoders.UrlBase64;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author: zhaobingyu
 * @Date: 2:51 PM 04/07/2018
 */
public class UserUtils {

    private static final String AUTHORIZATION = "authorization";
    /**
     * 混淆的随机字符串
     */
    private static final String SALT = "pc%rW44G#G";

    /**
     * SHA加密
     */
    private static final String KEY_SHA = "SHA";

    /**
     * 获取当前请求的token
     * @return
     */
    public static String getCurrentToken() {
        return HttpUtils.getHeaders(HttpUtils.getHttpServletRequest()).get(AUTHORIZATION);
    }

    public static TokenUser getCurrentUser() {

        return getCurrentUser(getCurrentToken());
    }

    public static TokenUser getCurrentUser(String token) {
        if (Objects.equals(null , token)) {
            TokenUser tokenUser = new TokenUser();
            tokenUser.setId(0L);
            tokenUser.setName("nobody");
            return tokenUser;
        }
        String playLoad = token.split("\\.")[1];
        return JSONObject.parseObject(new String(Base64.decodeBase64(playLoad)), TokenUser.class);
    }

    /**
     * 获取当前请求的用户Id
     * @return
     */
    public static String getCurrentPrinciple() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 判读当前token用户是否为接口所需的参数username
     *
     * @param username
     * @return
     */
    public static boolean isMyself(String username) {
        return username.equals(getCurrentPrinciple());
    }

    /**
     * 获取当前请求Authentication
     *
     * @return
     */
    public static Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前请求的权限信息
     * @return
     */
    public static List<SimpleGrantedAuthority> getCurrentAuthorities() {
        return (List<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

    /**
     * @param role
     * @return
     */
    public static boolean hasRole(String role) {
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }
        boolean hasRole = false;
        List<SimpleGrantedAuthority> list = getCurrentAuthorities();
        for (SimpleGrantedAuthority s : list) {
            if (role.equals(s.getAuthority())) {
                hasRole = true;
                break;
            }
        }
        return hasRole;
    }

    /**
     * 通过一个token获取一个token的合法签名
     * @param token
     * @return
     */
    public static String getSign(String token) {
        if (Objects.isNull(token) || token.length() <= 0)
        {
            //如果token是null返回一个唯一值，使其无法合法校验
            return UUIDHelper.getUUID();
        }

        //带上当天日期，使签名只能当天内使用
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy=MM=dd");

        token = SALT + token + SALT + simpleDateFormat.format(new Date());
        token = new String(UrlBase64.encode(token.getBytes()));
        token = DigestUtils.md5DigestAsHex(token.getBytes());
        token = DigestUtils.md5DigestAsHex(token.getBytes());

        return token;
    }


    public static void main(String[] args) {


        String token =
                "bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlcyI6bnVsbCwic2NvcGUiOlsic2VydmljZSJdLCJuYW1lIjoi5LuY56uL6I65IiwibW9iaWxlIjoiMTM5NDQ4NDI2NzciLCJpZCI6ODI3Nzk1MzUxNjMzOTIsInVzZXJOYW1lIjoiMTM5NDQ4NDI2NzciLCJleHAiOjE1ODY4Nzk1NDIsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iXSwianRpIjoiNTgzNTE3ZmQtMzc0NS00NjVjLWI4MjAtOWE2MWUzYjM2NTc4IiwiY2xpZW50X2lkIjoieG14Yy1zZXJ2aWNlIn0.q3ifAmdCChtWuYLqaS358r2IjxIigLAuSKnkq7a2azawA3KDrwacaf3iGAYU3mQxg8X-JyecTh4k4CxMR5iXrtjAXv-RAbF63iv7clteSoJK8e7HAaCMorcb1d_y1MzDOF4vDxw4kopsmMVRmnQu3SzpM_cbrC0CD25Q3q1tjQA4JxtVQapSAyyQpw_2GUk5uKpiF6UANBijgDHY4Th7CQjPA0fOpC2qpOHiEN_ml5DHOB3-lGJ0jShbfRHTqeYFwXSeY34gOgSx58M5cb7ZcfxW7DcrfeuSR8j1OvvMD1b6a2bedg7I3rtqtRwowzKWGh1IdVYohxNbtLximJUn3A";
        String playLoad = token.split("\\.")[1];
        System.out.println(JSONObject.parseObject(new String(Base64.decodeBase64(playLoad)), TokenUser.class));

//        System.out.println(JSONObject.parseObject(new String(Base64.decodeBase64(s[1]))));
//
//
//        System.out.println(JSONObject.toJSONString(JSONObject.parseObject(new String(Base64.decodeBase64(s[1])), TokenUser.class)));
//        TokenUser currentUser = getCurrentUser(token);
//
//        System.out.println("当前token用 -> " + currentUser.getRoles());
    }

}
