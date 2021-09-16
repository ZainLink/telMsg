package com.zyportal2.system.entity;

import java.io.Serializable;

/**
 * Created by Thinkpad on 2019/4/23.
 */
public class BindCdkeyInfo implements Serializable {
    String binder;

    public String getBinder() {
        return binder;
    }

    public void setBinder(String binder) {
        this.binder = binder;
    }
}
