package com.zyportal2.system.controller;

import com.zyportal2.system.util.IpUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Thinkpad on 2019/4/4
 */
@Component
@Controller
@RequestMapping("/html")
public class MainController {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @RequestMapping("/main")
    public String mainPage() {
        return "home";
    }

    @RequestMapping("/login")
    public String login(
            HttpServletRequest request
    ) {
        return "Login";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/chat")
    public String weChat() {
        return "index";
    }

    @RequestMapping("/msgSend")
    public String msgSend() {
        return "bomb";
    }

    @RequestMapping("/sendList")
    public String msgSendList() {
        return "list";
    }

    @RequestMapping("/userInfo")
    public String userInfo() {
        return "user";
    }


}
