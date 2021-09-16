package com.zyportal2.system.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zyportal2.system.entity.TelInfo;
import com.zyportal2.system.service.SendTaskService;
import com.zyportal2.system.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Thinkpad on 2019/4/15.
 */
@Service("sendTaskService")
public class SendTaskServiceImpl implements SendTaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendTaskServiceImpl.class);

    private static final String tel = "tel";

    private static final int time = 120000;

    private static final int seconds = 2000;


    private static final String cdk = "CDK_";

    @Autowired
    private SmsService smsService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void sendTask() throws Exception {
        this.send1();
        Thread.sleep(time);
        this.send2();
        Thread.sleep(time);
        this.send3();
        Thread.sleep(time);
        this.send4();
        Thread.sleep(time);
        this.send5();
        Thread.sleep(time);
        this.send6();
        Thread.sleep(time);
        this.send7();
        Thread.sleep(time);
        this.send8();
        Thread.sleep(time);
        this.send9();
        Thread.sleep(time);
        this.send10();
        Thread.sleep(time);
        this.send11();
        Thread.sleep(time);
        this.send12();
        Thread.sleep(time);
        this.send13();
        Thread.sleep(time);
        this.send14();
        Thread.sleep(time);
        this.send15();
        Thread.sleep(time);
        this.send16();
        Thread.sleep(time);
        this.send17();
        Thread.sleep(time);
        this.send18();
        Thread.sleep(time);
        this.send19();
        Thread.sleep(time);
        this.send20();
        Thread.sleep(time);
        this.send21();
        Thread.sleep(time);
        this.send22();
        Thread.sleep(time);
        this.send23();
        Thread.sleep(time);
        this.send24();
        Thread.sleep(time);
        this.send25();
        Thread.sleep(time);
        this.send26();
        Thread.sleep(time);
        this.send27();
        Thread.sleep(time);
        this.send28();
        Thread.sleep(time);
        this.send29();
        Thread.sleep(time);
        this.send30();
        Thread.sleep(time);
        this.send31();
        Thread.sleep(time);
        this.send32();
        Thread.sleep(time);
        this.send33();
        Thread.sleep(time);
        this.send34();
        Thread.sleep(time);
        this.send35();
        Thread.sleep(time);
        this.send36();
        Thread.sleep(time);
        this.send37();
        Thread.sleep(time);
        this.send38();
        Thread.sleep(time);
        this.send39();
        Thread.sleep(time);
        this.send40();
        Thread.sleep(time);
        this.send41();
        Thread.sleep(time);
        this.send42();
        Thread.sleep(time);
        this.send43();
        Thread.sleep(time);
        this.send44();
        Thread.sleep(time);
    }

    @Override
    public void sendAll(String mobile) throws Exception {
        smsService.sendinfo1(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo2(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo3(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo4(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo5(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo6(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo7(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo8(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo9(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo10(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo11(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo12(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo13(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo14(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo15(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo16(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo17(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo18(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo19(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo20(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo21(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo22(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo23(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo24(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo25(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo26(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo27(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo28(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo29(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo30(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo31(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo32(mobile);
        Thread.sleep(seconds);
        smsService.senInfo33(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo34(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo35(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo36(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo37(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo38(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo39(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo40(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo41(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo42(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo43(mobile);
        Thread.sleep(seconds);
        smsService.sendInfo44(mobile);

    }


    public void send1() {
        // 1
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendinfo1(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }

    }

    public void send2() {
        // 1
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo2(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    public void send3() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo3(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send4() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo4(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send5() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo5(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send6() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo6(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send7() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo7(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send8() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo8(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send9() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo9(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send10() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo10(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send11() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo11(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send12() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo12(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send13() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo13(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send14() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo14(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send15() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo15(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send16() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo16(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send17() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo17(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send18() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo18(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send19() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo19(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send20() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo20(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send21() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo21(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send22() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo22(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send23() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo23(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send24() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo24(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send25() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo25(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send26() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo26(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send27() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo27(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send28() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo28(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send29() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo29(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send30() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo30(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send31() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo31(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send32() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo32(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send33() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.senInfo33(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send34() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo34(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send35() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo35(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send36() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo36(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send37() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo37(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send38() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo38(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send39() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo39(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send40() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo40(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send41() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo41(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send42() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo42(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send43() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo43(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    @Override
    public void send44() {
        List<TelInfo> list = this.selectSendList();
        if (list != null) {
            for (TelInfo a : list) {
                smsService.sendInfo44(a.getMobile());
                int size = a.getSize() + 1;
                a.setSize(size);
                LOGGER.info("手机号：" + a.getMobile() + "已发送" + size + "次");
            }
            String str = JSON.toJSON(list).toString();
            redisTemplate.opsForValue().set(tel, str);
        }
    }

    public List<TelInfo> selectSendList() {
        String infos = redisTemplate.opsForValue().get(tel);
        List<TelInfo> list = null;
        if (infos != null) {
            list = JSONObject.parseArray(infos, TelInfo.class);
            Iterator<TelInfo> iterator = list.iterator();
            while (iterator.hasNext()) {
                TelInfo a = iterator.next();
                if (!a.getUser().equals("admin")) {
                    if (a.getUser() != null) {
                        String mcdk = redisTemplate.opsForValue().get(a.getUser());
                        if (mcdk == null) {
                            iterator.remove();
                        }
                    }
                }
            }
/*            for (TelInfo a : list) {
                if (!a.getUser().equals("admin")) {
                    if (a.getUser() != null) {
                        String mcdk = redisTemplate.opsForValue().get(cdk + a.getUser());
                        if (mcdk == null) {
                            list.remove(a);
                        }
                    }
                }
            }*/
        }
        return list;
    }

    public static void main(String args[]) {

    }
}

