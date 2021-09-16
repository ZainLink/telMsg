package com.zyportal2.system.Filter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;


/**
 * Created by Thinkpad on 2019/4/15.
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 携带Token的HTTP头
     */
    public static final String TOKEN_HEADER = "Authorization";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        httpResponse.addHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT,DELETE, OPTIONS, HEAD");
        String authHeader = httpRequest.getHeader(TOKEN_HEADER);
        if (authHeader == null) {
            response.sendRedirect("/html/login");
            return false;
        }
        String auth = redisTemplate.opsForValue().get(authHeader);
        if (auth != null) {
            //刷新token
            redisTemplate.expire(authHeader, 30, TimeUnit.MINUTES);
            return true;
        } else {
            response.sendRedirect("/html/login");
            return false;
        }


    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
