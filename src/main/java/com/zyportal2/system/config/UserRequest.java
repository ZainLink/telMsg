package com.zyportal2.system.config;


import com.alibaba.fastjson.JSON;
import com.zyportal2.system.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Thinkpad on 2019/4/18.
 */
@Component
public class UserRequest {
    /**
     * 携带Token的HTTP头
     */
    public static final String TOKEN_HEADER = "Authorization";

    private static UserRequest userRequest;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @PostConstruct
    public void init() {
        userRequest = this;
        userRequest.redisTemplate = this.redisTemplate;
    }

    public static UserInfo getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authHeader = request.getHeader(TOKEN_HEADER);
        String auth = userRequest.redisTemplate.opsForValue().get(authHeader);
        UserInfo userInfo = JSON.parseObject(auth, UserInfo.class);
        return userInfo;
    }

    public static void setCurrentUser(UserInfo user) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authHeader = request.getHeader(TOKEN_HEADER);
        String str = JSON.toJSON(user).toString();
        userRequest.redisTemplate.opsForValue().set(authHeader, str);//缓存
    }
}
