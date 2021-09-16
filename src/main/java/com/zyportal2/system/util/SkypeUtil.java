package com.zyportal2.system.util;

import com.zyportal2.system.service.SkypeService;
import com.zyportal2.system.serviceImpl.SkypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by Thinkpad-W530 on 2020/9/23.
 */
@Service("skypeUtil")
public class SkypeUtil extends Thread implements Serializable {


    @Autowired
    private SkypeService skypeService;


    public SkypeUtil() {
        super();
    }

    public SkypeUtil(String tel) {
        this.tel = tel;
    }

    private String tel;

    @Override
    public void run() {
        final long timeInterval = 300000;
        try {
            Process p = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Microsoft\\Skype for Desktop\\Skype.exe");

            Thread.sleep(1500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                skypeService.takeTel(tel);
                Thread.sleep(timeInterval);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}
