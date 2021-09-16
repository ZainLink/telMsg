package com.zyportal2.system.entity;

import java.io.Serializable;

/**
 * Created by Thinkpad on 2019/4/4.
 */
public class UserInfo implements Serializable {


    String username;

    String password;

    String bincdk;

    String status;

    String createtime;

    String auth;

    String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBincdk() {
        return bincdk;
    }

    public void setBincdk(String bincdk) {
        this.bincdk = bincdk;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
