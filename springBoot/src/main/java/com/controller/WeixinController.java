package com.controller;

import com.google.common.io.CharStreams;
import com.utils.VerifyTokenUtil;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Controller
@RequestMapping(value = "/weixin")
public class WeixinController {

    private Logger logger = LoggerFactory.getLogger(WeixinController.class);
    @ResponseBody
    @RequestMapping("/test")
    public Integer test(HttpServletResponse response) throws IOException {

        logger.debug("debug");
        logger.info("info");
        logger.error("error");
        return 123;
    }


    @ResponseBody
    @RequestMapping(value = "/process")
    public void hello(HttpServletRequest request, HttpServletResponse response, @RequestParam("signature")String signature,
                      @RequestParam("timestamp")String timestamp, @RequestParam("nonce")String nonce, RedirectAttributes attributes){
        String echostr = request.getParameter("echostr");
        if (!StringUtils.isEmpty(echostr)) {
            VerifyTokenUtil verifyTokenUtil = new VerifyTokenUtil();
            if (verifyTokenUtil.validate(signature, timestamp, nonce)) {
//                return echostr;
                returnVerify(request,response,echostr);
                    return;
            }
        }else{
        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            String fromXML = CharStreams.toString(new InputStreamReader(inputStream, "utf-8"));
            /**对xml进行解密*/
//            WXBizMsgCrypt pc = new WXBizMsgCrypt(WeiXinConfig.token, WeiXinConfig.AppSecret, WeiXinConfig.AppID);
//            //解密后的铭文
//            String result2 = pc.decryptMsg(signature, timestamp, nonce, fromXML);
            XMLSerializer xmlSerializer = new XMLSerializer();
            JSONObject read = (JSONObject) xmlSerializer.read(fromXML);
            String content = read.getString("Content");
            String toUserName = read.getString("ToUserName");
            String fromUserName = read.getString("FromUserName");
            String CreateTime = read.getString("CreateTime");
            attributes.addAttribute("content", content);
            attributes.addAttribute("toUserName", toUserName);
            attributes.addAttribute("fromUserName", fromUserName);
            attributes.addAttribute("CreateTime", CreateTime);
            callMsg(request,response,content,toUserName,fromUserName,CreateTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
        return ;
    }

    @RequestMapping(value = "/callMsg",method = { RequestMethod.POST, RequestMethod.GET })
    public String  callMsg(HttpServletRequest request, HttpServletResponse response,@RequestParam("content")String content,
                      @RequestParam("toUserName")String toUserName, @RequestParam("fromUserName")String fromUserName, @RequestParam("CreateTime")String CreateTime){
        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            String fromXML = CharStreams.toString(new InputStreamReader(inputStream, "utf-8"));
            /**对xml进行解密*/
            String resXmlStr="<xml><ToUserName><![CDATA["+fromUserName+"]]></ToUserName>" +//此处要填写 发送方帐号（一个OpenID）
                    "<FromUserName><![CDATA["+toUserName+"]]></FromUserName>" +//此处填写开发者微信号
                    "<CreateTime>"+CreateTime+"</CreateTime>" +
                    "<MsgType><![CDATA["+"text"+"]]></MsgType>"+
                    "<Content><![CDATA["+"嗯嗯！！"+"]]></Content></xml>";
            System.out.println(content);
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(resXmlStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  "success";
    }
    @ResponseBody
    @RequestMapping(value = "/returnVerify",method = { RequestMethod.POST, RequestMethod.GET },produces = {"application/xml; charset=UTF-8"})
    public String returnVerify(HttpServletRequest request, HttpServletResponse response,String echostr){
        try {
            response.getWriter().println(echostr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return echostr;
    }
}
