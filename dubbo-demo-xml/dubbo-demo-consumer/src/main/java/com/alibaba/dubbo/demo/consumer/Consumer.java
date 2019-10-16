package com.alibaba.dubbo.demo.consumer;

import com.alibaba.dubbo.demo.DemoServer;
import com.alibaba.dubbo.demo.DemoService;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by ken.lj on 2017/7/31.
 */
public class Consumer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-demo-consumer.xml"});
        context.start();
        
        DemoService demoService = (DemoService) context.getBean("demoService"); // 获取远程服务代理
        DemoServer demoServer = (DemoServer) context.getBean("demoServer");
        String hello = demoService.sayHello("hello hhhhworld"); // 执行远程方法
        
        hello += demoServer.sayGoodbye("baibai");

        System.err.println(hello); // 显示调用结果
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
