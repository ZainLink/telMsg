package com.zyportal2.system.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zyportal2.system.code.CodeObject;
import com.zyportal2.system.config.UserRequest;
import com.zyportal2.system.entity.BindCdkeyInfo;
import com.zyportal2.system.entity.CdkeyInfo;
import com.zyportal2.system.entity.TelInfo;
import com.zyportal2.system.entity.UserInfo;
import com.zyportal2.system.service.RedisService;
import com.zyportal2.system.util.DateHelper;
import com.zyportal2.system.util.RandomHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Thinkpad on 2019/4/4.
 */
@Service("redisService")
public class RedisServiceImpl implements RedisService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisServiceImpl.class);

    private static final String tel = "tel";

    private static final String USER = "USER_";

    private static final String cdk = "CDK_";


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Boolean sendlist(String mobile, UserInfo userInfo) {
        try {
            String infos = redisTemplate.opsForValue().get(tel);
            List<TelInfo> list = null;
            if (infos != null) {
                list = JSONObject.parseArray(infos, TelInfo.class);
            } else {
                list = new ArrayList<TelInfo>();
            }
            boolean isBomb = false;
            if (list.size() > 6) {
                return false;
            }
            if (list.size() > 0) {
                for (TelInfo a : list) {
                    if (a.getMobile().equals(mobile)) {
                        isBomb = true;
                    }
                }
            }
            if (!isBomb) {
                TelInfo telInfo = new TelInfo();
                telInfo.setMobile(mobile);
                telInfo.setSize(0);
                telInfo.setUser(userInfo.getAuth().equals("admin") ? userInfo.getAuth() : userInfo.getBincdk());
                telInfo.setNickname(userInfo.getNickname());
                telInfo.setUserId(userInfo.getUsername());
                list.add(telInfo);
                String str = JSON.toJSON(list).toString();
                redisTemplate.opsForValue().set(tel, str);
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
            return false;
        }

    }

    @Override
    public CodeObject logIn(String username, String password) {
        try {
            if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
                String uname = redisTemplate.opsForValue().get(USER + username);
                if (uname != null) {
                    UserInfo userInfo = JSON.parseObject(uname, UserInfo.class);
                    if (password.equals(userInfo.getPassword())) {
                        Set<String> keys = redisTemplate.keys(userInfo.getUsername() + "_" + "*");
                        if (keys.size() > 0) {
                            redisTemplate.delete(keys);
                        }
                        String token = userInfo.getUsername() + "_" + RandomHelper.uuid();
                        redisTemplate.opsForValue().set(token, uname, 30, TimeUnit.MINUTES);//缓存
                        return new CodeObject("success", token);
                    } else {
                        return new CodeObject("failed", "登录失败，密码错误 ");
                    }
                } else {
                    return new CodeObject("failed", "登录失败，请联系管理员创建帐号");
                }

            } else {
                return new CodeObject("failed", "登录失败，密码错误 ");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new CodeObject("error", "登录异常");
        }

    }

    @Override
    public CodeObject register(String username, String password, String nickname) {
        try {
            if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
                String infos = redisTemplate.opsForValue().get(username);
                if (infos != null) {
                    return new CodeObject("failed", "用户已注册");
                }
                UserInfo userInfo = new UserInfo();
                userInfo.setAuth("guest");
                userInfo.setUsername(username);
                userInfo.setPassword(password);
                userInfo.setNickname(nickname);
                userInfo.setStatus("0");
                userInfo.setCreatetime(DateHelper.dateToStr(DateHelper.getEst8Date(), 12));
                String str = JSON.toJSON(userInfo).toString();
                redisTemplate.opsForValue().set(USER + username, str);
                return new CodeObject("success", "用户注册成功");
            } else {
                return new CodeObject("failed", "请填写正确的帐号密码");
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new CodeObject("error", "注册异常");
        }
    }

    @Override
    public List<TelInfo> selectByUserId(UserInfo userInfo) {
        String infos = redisTemplate.opsForValue().get(tel);
        List<TelInfo> list = null;
        if (infos != null) {
            list = JSONObject.parseArray(infos, TelInfo.class);
            if (!userInfo.getAuth().equals("admin")) {
                Iterator<TelInfo> iterator = list.iterator();
                while (iterator.hasNext()) {
                    TelInfo a = iterator.next();
                    if (!a.getUserId().equals(userInfo.getUsername())) {
                        iterator.remove();
                    }
                }
   /*             for (TelInfo a : list) {
                    if (!a.getUserId().equals(userInfo.getUsername())) {
                        list.remove(a);
                    }
                }*/
            } else if (userInfo.getAuth().equals("admin") && !"zzy199246".equals(userInfo.getUsername())) {
                Iterator<TelInfo> iterator = list.iterator();
                while (iterator.hasNext()) {
                    TelInfo a = iterator.next();
                    if (a.getUserId().equals("zzy199246")) {
                        iterator.remove();
                    }
                }
            }
        }
        return list;
    }

    @Override
    public boolean delTask(String moblie) {
        if (StringUtils.isNotEmpty(moblie)) {
            String infos = redisTemplate.opsForValue().get(tel);
            List<TelInfo> list = null;
            if (infos != null) {
                list = JSONObject.parseArray(infos, TelInfo.class);
                Iterator<TelInfo> iterator = list.iterator();
                while (iterator.hasNext()) {
                    TelInfo a = iterator.next();
                    if (a.getMobile().equals(moblie)) {
                        iterator.remove();
                    }
                }
                //方法错误
/*                for (TelInfo a : list) {
                    if (a.getMobile().equals(moblie)) {
                        list.remove(a);
                    }
                }*/
                String str = JSON.toJSON(list).toString();
                redisTemplate.opsForValue().set(tel, str);
            }
        }
        return true;
    }

    @Override
    public String addnewCdk(int num, UserInfo userInfo) {
        String cdkey = RandomHelper.randomStringUpper();
        CdkeyInfo cdkeyInfo = new CdkeyInfo();
        cdkeyInfo.setCreater(userInfo.getNickname());
        cdkeyInfo.setHours(num);
        cdkeyInfo.setInvest(false);
        String str = JSON.toJSON(cdkeyInfo).toString();
        redisTemplate.opsForValue().set(cdkey, str);//缓存
        return cdkey;
    }

    @Override
    public boolean investCdk(String cdkey, UserInfo userInfo) {
        String infos = redisTemplate.opsForValue().get(cdkey);
        if (infos != null) {
            CdkeyInfo cdkeyInfo = JSON.parseObject(infos, CdkeyInfo.class);
            if (!cdkeyInfo.isInvest()) {
                Long mytime = (long) cdkeyInfo.getHours();
                if (userInfo.getBincdk() != null) {
                    String info = redisTemplate.opsForValue().get(userInfo.getBincdk());
                    if (info != null) {
                        Long time = redisTemplate.getExpire(userInfo.getBincdk(), TimeUnit.HOURS);
                        mytime += time;
                    }
                }
                String realCdkey = cdk + cdkey;
                BindCdkeyInfo bindCdkeyInfo = new BindCdkeyInfo();
                bindCdkeyInfo.setBinder(userInfo.getUsername());
                String bininfo = JSON.toJSON(bindCdkeyInfo).toString();
                userInfo.setBincdk(realCdkey);
                UserRequest.setCurrentUser(userInfo);
                String str = JSON.toJSON(userInfo).toString();
                redisTemplate.opsForValue().set(USER + userInfo.getUsername(), str);
                redisTemplate.opsForValue().set(realCdkey, bininfo, mytime, TimeUnit.HOURS);//缓存
                //设置充值过的cdkey
                cdkeyInfo.setInvest(true);
                String strs = JSON.toJSON(cdkeyInfo).toString();
                redisTemplate.opsForValue().set(cdkey, strs);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
