package com.zyportal2.system.entity;

import java.io.Serializable;

/**
 * Created by Thinkpad on 2019/6/8.
 */
public class TelB implements Serializable {
    public String telNum;
    public String password;
    public String lastDeviceId;
    public String clientVersion;
    public String lastDeviceType;
    public String lastLoginSubChannel;
    public String token;
    public String IDFA;
    public int loginType;

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastDeviceId() {
        return lastDeviceId;
    }

    public void setLastDeviceId(String lastDeviceId) {
        this.lastDeviceId = lastDeviceId;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getLastDeviceType() {
        return lastDeviceType;
    }

    public void setLastDeviceType(String lastDeviceType) {
        this.lastDeviceType = lastDeviceType;
    }

    public String getLastLoginSubChannel() {
        return lastLoginSubChannel;
    }

    public void setLastLoginSubChannel(String lastLoginSubChannel) {
        this.lastLoginSubChannel = lastLoginSubChannel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIDFA() {
        return IDFA;
    }

    public void setIDFA(String IDFA) {
        this.IDFA = IDFA;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }
}
