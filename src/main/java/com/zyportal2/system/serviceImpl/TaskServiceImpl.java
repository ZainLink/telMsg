package com.zyportal2.system.serviceImpl;

import com.zyportal2.system.service.SendTaskService;
import com.zyportal2.system.service.SmsService;
import com.zyportal2.system.service.TaskService;
import com.zyportal2.system.util.SkypeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Thinkpad on 2019/4/4.
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService, CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);

    private static final int time = 150000;

    private static ExecutorService pool = Executors.newSingleThreadExecutor();


    @Autowired
    private SkypeUtil skypeUtil;


    @Override
    public void smsTask() throws Exception {


    }


    @Override
    public void run(String... strings) throws Exception {
        LOGGER.info("-----------------开始执行任务-------------------");
        startMission();
    }


    public void startMission() {
        skypeUtil.start();
    }
}
