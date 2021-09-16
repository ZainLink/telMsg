package com.zyportal2.system.entity;

import java.io.Serializable;

/**
 * Created by Thinkpad on 2019/4/23.
 */
public class CdkeyInfo implements Serializable {
    private String creater;
    private int hours;
    private boolean invest;

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public boolean isInvest() {
        return invest;
    }

    public void setInvest(boolean invest) {
        this.invest = invest;
    }
}
