package com.gupao.vip.mic.dubbo.order;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
@Service(value = "orderQueryService")
public class OrderQueryServiceImpl implements IOrderQueryService{

    @Override
    public String doQuery(String params) throws InterruptedException {
        System.out.println(params);
        TimeUnit.SECONDS.sleep(2);
        return "hello,菲菲";
    }
}
