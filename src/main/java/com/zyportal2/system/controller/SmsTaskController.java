package com.zyportal2.system.controller;

import com.zyportal2.system.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Thinkpad on 2019/4/4.
 */
@RestController
@RequestMapping("/task")
public class SmsTaskController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsTaskController.class);

    @Autowired
    private SmsService smsService;



    //测试专用接口
    @RequestMapping("/test")
    public void endMission() {
/*        smsService.sendinfo1("18552026779");*/
    }


}
