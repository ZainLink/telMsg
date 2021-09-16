package com.zyportal2.system.controller;

import com.alibaba.fastjson.JSON;
import com.zyportal2.system.config.RedisUtil;
import com.zyportal2.system.entity.MsgInfo;


import com.zyportal2.system.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Thinkpad on 2019/4/2.
 */
@ServerEndpoint("/websocket/{sid}")
@Service("webSocketServer")
public class WebSocketServer {
    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    //此处是解决无法注入的关键
    private static ApplicationContext applicationContext;

    private RedisUtil redisUtil;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocketServer.applicationContext = applicationContext;
    }

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收sid
    private String sid = "";


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) throws Exception {
        redisUtil = applicationContext.getBean(RedisUtil.class);
        MsgInfo msgInfo = new MsgInfo();
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        log.info("有新用户开始监听:" + sid + ",当前在线人数为" + getOnlineCount());
        if (sid == null) {
            msgInfo.setType("error");
            String str = JSON.toJSON(msgInfo).toString();
            sendMessage(str);
        } else {
            String auth = redisUtil.get(sid);
            this.sid = sid;
            if (auth == null) {
                msgInfo.setType("error");
                String str = JSON.toJSON(msgInfo).toString();
                sendMessage(str);
            } else {
                redisUtil.refreshKey(sid);
            }
        }


    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        log.info(this.sid + "连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        MsgInfo msgInfo = new MsgInfo();
        String auth = redisUtil.get(sid);
        if (auth == null) {
            msgInfo.setType("error");
            String str = JSON.toJSON(msgInfo).toString();
            sendMessage(str);
        } else {
            String userjson = redisUtil.get(sid);
            UserInfo userInfo = JSON.parseObject(userjson, UserInfo.class);
            log.info("收到来自用户" + userInfo.getNickname() + "的信息:" + message);
            MsgInfo msgInfos = JSON.parseObject(message, MsgInfo.class);
            msgInfos.setFromUser(userInfo.getNickname());
            String str = JSON.toJSON(msgInfos).toString();
            redisUtil.refreshKey(sid);
            //群发消息
            for (WebSocketServer item : webSocketSet) {
                try {
                    if (!item.sid.equals(sid)) {
                        item.sendMessage(str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message, @PathParam("sid") String sid) throws IOException {
        log.info("推送消息到用户" + sid + "，推送内容:" + message);
        for (WebSocketServer item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if (sid == null) {
                    item.sendMessage(message);
                } else if (item.sid.equals(sid)) {
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
