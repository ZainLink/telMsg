package com.zyportal2.system.util;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thinkpad-W530 on 2020/5/26.
 */
public class IpUtils {
    public static Map<String, String> getIpInfo(String ip) {
        String url = "http://api.map.baidu.com/highacciploc/v1?qcip=" + ip + "&qterm=pc&ak=jM04WbMCuASzfLvQvkObLP0X2n7Ye5ZR&coor=bd09ll";
        StringBuffer sbf = null;
        BufferedReader br = null;
        try {
            URL oracle = new URL(url);
            URLConnection conn = oracle.openConnection();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine = null;
            sbf = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                sbf.append(inputLine);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        JSONObject json = JSONObject.parseObject(sbf.toString());
        Map<String, String> map = new HashMap<>();
        String status = json.getString("status");
        if ("0".equals(json.getString("status"))) {
            JSONObject contentJ = json.getJSONObject("content");
            map.put("city", contentJ.getJSONObject("address_detail").getString("city"));
            JSONObject pointJ = contentJ.getJSONObject("point");
            map.put("lng", pointJ.getString("x"));
            map.put("lat", pointJ.getString("y"));
            return map;
        } else {
            return null;
        }
    }

    public static Map<String, String> getLocationInfo(String lat, String lng) {
        String url = "http://api.map.baidu.com/geocoder/v2/?location=" + lat + ","
                + lng + "&output=json&ak=jM04WbMCuASzfLvQvkObLP0X2n7Ye5ZR&coor=bd09ll&pois=1";
        StringBuffer sbf = null;
        BufferedReader br = null;
        try {
            URL oracle = new URL(url);
            URLConnection conn = oracle.openConnection();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine = null;
            sbf = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                sbf.append(inputLine);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        JSONObject json = JSONObject.parseObject(sbf.toString());
        Map<String, String> map = new HashMap<>();
        String status = json.getString("status");
        if ("0".equals(json.getString("status"))) {
            JSONObject contentJ = json.getJSONObject("content");
            map.put("city", contentJ.getJSONObject("address_detail").getString("city"));
            JSONObject pointJ = contentJ.getJSONObject("point");
            map.put("lng", pointJ.getString("x"));
            map.put("lat", pointJ.getString("y"));
            return map;
        } else {
            return null;
        }
    }

    public static void main(String args[]) {
        IpUtils.getLocationInfo("31.57003745","120.30545590");
    }

}
