package com.utils;

import com.utils.aes.WeiXinConfig;
import net.sf.json.JSONObject;

import java.security.MessageDigest;
import java.util.Arrays;

public class VerifyTokenUtil {


    private static class SingletonHolder{
        static final VerifyTokenUtil INSTANCE = new VerifyTokenUtil();
    }

    /**
     * 获取单例
     * @return
     */
    public static VerifyTokenUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private MessageDigest digest;

    public VerifyTokenUtil() {
        try {
            digest = MessageDigest.getInstance("SHA-1");
        } catch(Exception e) {
            throw new InternalError("init MessageDigest error:" + e.getMessage());
        }
    }


    public static String sendServiceMsg(String touser, String content, String aceessToken) {

//		String aceessToken = getAceessToken(WeiXinConfig.testMiniAppId, WeiXinConfig.testMiniAppsecret);
        JSONObject text = new JSONObject();
        text.put("content", content);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser", touser);
        jsonObject.put("msgtype", "text");
        jsonObject.put("text", text);
        String jsonStr = jsonObject.toString();
        String string = HttpClientUtil.doPostJson("" + aceessToken, jsonStr);
        try {
            return string;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 将字节数组转换成16进制字符串
     * @param b
     * @return
     */
    private static String byte2hex(byte[] b) {
        StringBuilder sbDes = new StringBuilder();
        String tmp = null;
        for (int i = 0; i < b.length; i++) {
            tmp = (Integer.toHexString(b[i] & 0xFF));
            if (tmp.length() == 1) {
                sbDes.append("0");
            }
            sbDes.append(tmp);
        }
        return sbDes.toString();
    }

    private String encrypt(String strSrc) {
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        digest.update(bt);
        strDes = byte2hex(digest.digest());
        return strDes;
    }

    /**
     * 校验请求的签名是否合法
     *
     * 加密/校验流程：
     * 1. 将token、timestamp、nonce三个参数进行字典序排序
     * 2. 将三个参数字符串拼接成一个字符串进行sha1加密
     * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public  boolean validate(String signature, String timestamp, String nonce){
        //1. 将token、timestamp、nonce三个参数进行字典序排序
        String[] arrTmp = {WeiXinConfig.token, timestamp, nonce };
        Arrays.sort(arrTmp);
        StringBuffer sb = new StringBuffer();
        //2.将三个参数字符串拼接成一个字符串进行sha1加密
        for (int i = 0; i < arrTmp.length; i++) {
            sb.append(arrTmp[i]);
        }
        String expectedSignature = encrypt(sb.toString());
        //3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        if(expectedSignature.equals(signature)){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }

}
