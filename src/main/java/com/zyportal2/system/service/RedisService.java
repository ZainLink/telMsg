package com.zyportal2.system.service;

import com.zyportal2.system.code.CodeObject;
import com.zyportal2.system.entity.TelInfo;
import com.zyportal2.system.entity.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Thinkpad on 2019/4/4.
 */
public interface RedisService {
    public Boolean sendlist(String mobile, UserInfo userInfo);

    public CodeObject logIn(String username, String password);

    public CodeObject register(String username, String password, String nickname);

    public List<TelInfo> selectByUserId(UserInfo userInfo);

    public boolean delTask(String moblie);

    public String addnewCdk(int num, UserInfo userInfo);

    public boolean investCdk(String cdkey, UserInfo userInfo);
}
