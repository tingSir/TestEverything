package com.enjoy.structure.decorator;

import com.enjoy.create.fatory.abstractFactory.AbstractFactory;
import com.enjoy.create.fatory.abstractFactory.AppleFactory;
import com.enjoy.entity.Bag;
import com.enjoy.entity.Fruit;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

/**
 * 装饰器模式测试
 */
public class DecoratorClient {

    @Test
    public void sendFruit(){
        AbstractFactory factory = new AppleFactory();

        //得到水果
        Fruit fruit = factory.getFruit();
        fruit.draw();
        //得到包装
        Bag bag = factory.getBag();

        //现需要增加防伪标识
        bag = new CheckedBagDecorator(bag);//防伪功能
        bag = new ReinforceBagDecorator(bag);//加固功能
        bag = new SpeedDecorator(bag);//加急功能

        bag.pack();
        //以下物流运输业务。。。。
    }
    @Test
    public void inputStreamTest(){
        byte[] buffer = new byte[1024];
        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\tmp\\text.txt");
            //装饰器模式增加buffered功能
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            //装饰器模式增加将字母大写的功能
            LowerCaseInputstream lowerCaseInputstream = new LowerCaseInputstream(bufferedInputStream);

            int bytesRead = 0;
            //从文件中按字节读取内容，到文件尾部时read方法将返回-1
            while ((bytesRead = lowerCaseInputstream.read(buffer)) != -1) {
                //将读取的字节转为字符串对象
                String chunk = new String(buffer, 0, bytesRead,"utf-8");
                System.out.println(chunk+"-----"+bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
