package com.zyportal2.system.serviceImpl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.zyportal2.system.entity.TelInfo;
import com.zyportal2.system.service.SkypeService;
import com.zyportal2.system.util.SkypeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.awt.*;
import java.util.*;
import java.util.List;


/**
 * Created by Thinkpad-W530 on 2020/9/22.
 */
@Service("skypeService")
public class SkypeServiceImpl implements SkypeService {

    private User32 user32 = User32.INSTANCE;
    private User32.INPUT input = new User32.INPUT();

    public static final int WM_LBUTTONUP = 514;
    public static final int WM_LBUTTONDOWN = 513;
    public static final int WM_LBUTTONDBLCLK = 0x203;
    static int WM_CLOSE = 0x10;
    final static String winTitle = "Untitled - Notepad";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private int num = 0;

    private static final String tellist = "tel";

//    public static  final String tel="17623628803";

    @Override
    public String getSkype() {
        return null;
    }

    @Override
    public Boolean takeTel(String tel) throws Exception {
        WinDef.HWND hWnd = user32.FindWindow(null, "Skype");
        if (hWnd == null) {
            Process p = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Microsoft\\Skype for Desktop\\Skype.exe");
            hWnd = user32.FindWindow(null, "Skype");
        }

        if (StringUtils.isEmpty(tel)) {
            if (this.selectSendList().size() > num) {
                TelInfo telInfo = this.selectSendList().get(num);
                tel = telInfo.getMobile();
                List<TelInfo> list = this.selectSendList();
                for (TelInfo a : list) {
                    if (a.getMobile().equals(tel)) {
                        int size = a.getSize() + 1;
                        a.setSize(size);
                    }
                }
                String str = JSON.toJSON(list).toString();
                redisTemplate.opsForValue().set(tellist, str);
                if (this.selectSendList().size() > num) {
                    num++;
                }
            } else if(this.selectSendList().size()==num&&num!=0) {
                num = 0;
                TelInfo telInfo = this.selectSendList().get(num);
                tel = telInfo.getMobile();
                List<TelInfo> list = this.selectSendList();
                for (TelInfo a : list) {
                    if (a.getMobile().equals(tel)) {
                        int size = a.getSize() + 1;
                        a.setSize(size);
                    }
                }
                String str = JSON.toJSON(list).toString();
                redisTemplate.opsForValue().set(tellist, str);
                if (this.selectSendList().size() > num) {
                    num++;
                }
            }
        }
        if (StringUtils.isNoneEmpty(tel)) {
            Thread.sleep(1500);
            //移动窗体并固定大小
            User32.INSTANCE.SetForegroundWindow(hWnd);
            user32.MoveWindow(hWnd, 0, 0, 1074, 787, false);
            Thread.sleep(1000);
            //关闭号码盘
            this.dbclick(hWnd, 330, 129);
            Thread.sleep(1000);
            //重新打开号码盘
            this.click(hWnd, 301, 76);
            Thread.sleep(2000);
            for (Character c : tel.toCharArray()) {
                sendChar(hWnd, c);
            }
            Thread.sleep(500);
            //开始拨打电话
            this.dbclick(hWnd, 538, 648);
            Thread.sleep(13500);
            //13.5秒后挂断
            this.dbclick(hWnd, 592, 716);
            Thread.sleep(1000);
            //关闭未接通
            this.dbclick(hWnd, 371, 332);
        }
        return true;
    }


    //模拟鼠标左键单击
    public void click(WinDef.HWND hwnd, int x, int y) throws Exception {
        int v = x + (y << 16);
        WinDef.LPARAM l = new WinDef.LPARAM(v);
        WinDef.WPARAM w = new WinDef.WPARAM(0);
        user32.PostMessage(hwnd, 513, w, l); // 按下
        user32.PostMessage(hwnd, 514, w, l); // 松开
    }

    public void dbclick(WinDef.HWND hwnd, int x, int y) throws Exception {
        this.click(hwnd, x, y);
        this.click(hwnd, x, y);
    }


    private void sendChar(WinDef.HWND hwnd, char ch) throws Exception {

        User32.INSTANCE.SetForegroundWindow(hwnd);
        input.type = new WinDef.DWORD(User32.INPUT.INPUT_KEYBOARD);
        input.input.setType("ki");
        input.input.ki.wScan = new WinDef.WORD(0);
        input.input.ki.time = new WinDef.DWORD(0);
        input.input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR(0);
        // Press
        input.input.ki.wVk = new WinDef.WORD(Character.toUpperCase(ch)); // 0x41
        input.input.ki.dwFlags = new WinDef.DWORD(0); // keydown
        user32.SendInput(new WinDef.DWORD(1), (User32.INPUT[]) input.toArray(1), input.size());
        // Release
        input.input.ki.wVk = new WinDef.WORD(Character.toUpperCase(ch)); // 0x41
        input.input.ki.dwFlags = new WinDef.DWORD(2); // keyup
        user32.SendInput(new WinDef.DWORD(1), (User32.INPUT[]) input.toArray(1), input.size());
    }


    public java.util.List<TelInfo> selectSendList() {
        String infos = redisTemplate.opsForValue().get(tellist);
        java.util.List<TelInfo> list = null;
        if (infos != null) {
            list = JSONObject.parseArray(infos, TelInfo.class);
            Iterator<TelInfo> iterator = list.iterator();
            while (iterator.hasNext()) {
                TelInfo a = iterator.next();
                if (!a.getUser().equals("admin")) {
                    if (a.getUser() != null) {
                        String mcdk = redisTemplate.opsForValue().get(a.getUser());
                        if (mcdk == null) {
                            iterator.remove();
                        }
                    }
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        SkypeUtil skypeUtil = new SkypeUtil("18552026779");
        skypeUtil.start();
    }
}
