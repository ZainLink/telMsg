package com.zyportal2.system.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.zyportal2.system.entity.TelB;
import com.zyportal2.system.service.TakeTelService;
import com.zyportal2.system.util.ChangeCharset;
import com.zyportal2.system.util.HttpUtils;
import com.zyportal2.system.util.UnicodeUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thinkpad on 2019/6/8.
 */
public class TakeTelServiceImpl implements TakeTelService {

    public static final TelB telb = new TelB();

    @Override
    public void teltest() {
        try {
            telb.setClientVersion("iPhone8,2_12.1.2_2.6.9");
            telb.setIDFA("EC688CFE-EBA1-4D4D-88F3-1110A91496B6");
            telb.setLastDeviceId("EC688CFE-EBA1-4D4D-88F3-1110A91496B6");
            telb.setLastDeviceType("2");
            telb.setLastLoginSubChannel("AppleStore");
            telb.setLoginType(1);
            telb.setPassword("b9f14b963acdab52b42531f780569a8d");
            telb.setTelNum("18552026779");
            telb.setToken("y3KD9uNQGs7c0Xq04daY");
            String str = JSON.toJSON(telb).toString();
            String url = "http://114.215.107.25:9098/mythcall/api/Account_login.do";
            Map<String, String> param = new HashMap<>();
            param.put("account",str);
            String strs = HttpUtils.sendPostByTel(url, param);
            System.out.print(strs);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {
        TakeTelServiceImpl takeTelService = new TakeTelServiceImpl();
        takeTelService.teltest();
    }
}
