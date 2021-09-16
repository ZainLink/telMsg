package com.zyportal2.system.service;

/**
 * Created by Thinkpad-W530 on 2020/9/22.
 */
public interface SkypeService {
    public String getSkype();

    public Boolean takeTel(String tel) throws Exception;
}
