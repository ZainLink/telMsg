package com.zyportal2.system.controller;

import com.zyportal2.system.code.CodeObject;
import com.zyportal2.system.config.UserRequest;
import com.zyportal2.system.entity.TelInfo;
import com.zyportal2.system.entity.UserInfo;
import com.zyportal2.system.service.RedisService;
import com.zyportal2.system.service.SendTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Thinkpad on 2019/4/4.
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private SendTaskService sendTaskService;


    private static final String cdk = "CDK_";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public final static String TEL_WHITELIST[] = {"18552026779", "13666641420", "18683935277", "19983168077"};

    private static ExecutorService pool = Executors.newCachedThreadPool();


    //用户注册
    @RequestMapping("/register")
    public CodeObject register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("nickname") String nickname
    ) {
        return redisService.register(username, password, nickname);
    }

    //用户登录
    @RequestMapping("/login")
    public CodeObject login(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        return redisService.logIn(username, password);
    }


    //加入轰炸队列
    @RequestMapping("/SendStart")
    public CodeObject sendStart(
            @RequestParam("mobile") String mobile,
            @RequestParam("type") String type
    ) {
        UserInfo userInfo = UserRequest.getCurrentUser();
        if (Arrays.asList(TEL_WHITELIST).contains(mobile)) {
            return new CodeObject("failed", "你爸爸你也敢炸？");
        }

        if (!userInfo.getAuth().equals("admin")) {
            if ("1".equals(type)) {
                return new CodeObject("failed", "无此权限，请升级VIP");
            }
            if (userInfo.getBincdk() == null) {
                return new CodeObject("failed", "帐号余额不足");
            }
            String mcdk = redisTemplate.opsForValue().get(userInfo.getBincdk());
            if (mcdk != null) {
                Boolean a = redisService.sendlist(mobile, userInfo);
                if (!a) {
                    return new CodeObject("failed", "该号码已经处于发送状态或队列上限已满（上限为6）");
                } else {
                    return new CodeObject("success", "已加入发送队列");
                }
            } else {
                return new CodeObject("failed", "帐号余额不足");
            }
        } else {
            if ("1".equals(type)) {
                try {
                    pool.execute(new Runnable() {
                        public void run() {
                            try {
                                sendTaskService.sendAll(mobile);
                            } catch (Exception e) {//先从队列移除,等待下次加入队列
                                e.printStackTrace();
                                LOGGER.error(e.getMessage());
                            }
                        }
                    });
                    return new CodeObject("success", "发送成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    return new CodeObject("error", "发送异常");
                }
            } else {
                Boolean a = redisService.sendlist(mobile, userInfo);
                if (!a) {
                    return new CodeObject("failed", "该号码已经处于发送状态或队列上限已满（上限为6）");
                } else {
                    return new CodeObject("success", "已加入发送队列");
                }
            }

        }
    }

    @RequestMapping("/selectBombList")
    public List<TelInfo> selectList() {
        UserInfo userInfo = UserRequest.getCurrentUser();
        return redisService.selectByUserId(userInfo);
    }

    @RequestMapping("/delTask")
    public CodeObject delTask(
            @RequestParam("mobile") String mobile
    ) {
        return redisService.delTask(mobile) ? new CodeObject("success", "删除成功") : new CodeObject("failed", "删除失败");
    }


    @RequestMapping("/getCdkeyByAdmin")
    public CodeObject getCdkeyByAdmin(
            @RequestParam("num") int num
    ) {
        UserInfo userInfo = UserRequest.getCurrentUser();
        if (!userInfo.getAuth().equals("admin")) {
            return new CodeObject("失败", "无此权限");
        } else {
            String cdkey = redisService.addnewCdk(num, userInfo);
            return new CodeObject("成功", cdkey);
        }
    }


    @RequestMapping("/useCdkey")
    public CodeObject useCdkey(
            @RequestParam("cdkey") String cdkey
    ) {
        UserInfo userInfo = UserRequest.getCurrentUser();
        if (redisService.investCdk(cdkey, userInfo)) {
            return new CodeObject("success", "充值成功");
        } else {
            return new CodeObject("failed", "充值失败，请联系系统管理员");
        }
    }

    @RequestMapping("/getExpTime")
    public CodeObject getExpTime() {
        UserInfo userInfo = UserRequest.getCurrentUser();
        if (userInfo.getBincdk() != null) {
            String infos = redisTemplate.opsForValue().get(userInfo.getBincdk());
            if (infos == null) {
                return new CodeObject("failed", "帐号余额不足");
            } else {
                Long time = redisTemplate.getExpire(userInfo.getBincdk(), TimeUnit.HOURS);
                return new CodeObject("success", "剩余" + time + "小时");
            }
        } else {
            return new CodeObject("failed", "账号还未充值");
        }
    }

    @RequestMapping("/getNickName")
    public CodeObject getNickName() {
        UserInfo userInfo = UserRequest.getCurrentUser();
        return new CodeObject("success", userInfo.getNickname());
    }
}
