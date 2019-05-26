package com.gupao.vip.mic.dubbo.user;

import com.gupao.vip.mic.dubbo.order.DoOrderRequest;
import com.gupao.vip.mic.dubbo.order.DoOrderResponse;
import com.gupao.vip.mic.dubbo.order.IOrderServices;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("order-consumer.xml");

        //用户下单过程
        IOrderServices services = (IOrderServices) context.getBean("orderServices");

       /* IOrderQueryService orderQueryService=(IOrderQueryService)context.getBean("orderQueryServices");
        orderQueryService.doQuery("mic");
        Future<String> future = RpcContext.getContext().getFuture();
        System.out.println("方法已经调用完成！！");
        //异步调用这个地方才会阻塞
        String string = future.get();
        System.out.println(string);*/
        DoOrderRequest request = new DoOrderRequest();
        request.setName("mic");
        services.doOrder(request);
        for (int i=0;i<10;i++) {
            DoOrderResponse response = services.doOrder(request);
            System.out.println(response);
            TimeUnit.SECONDS.sleep(1);
        }
}
}
