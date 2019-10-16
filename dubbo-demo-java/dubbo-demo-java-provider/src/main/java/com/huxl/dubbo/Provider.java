package com.huxl.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.huxl.dubbo.api.HelloWorldService;
import com.huxl.dubbo.service.impl.HelloWorldServiceImpl;


import java.util.concurrent.CountDownLatch;

/**
 * 服务提供者
 * @author huxl
 */
public class Provider {

    private static String zkHost = System.getProperty("zookeeper.address","127.0.0.1");

    public static void main(String[] args) throws InterruptedException {
        ServiceConfig<HelloWorldService> service = new ServiceConfig<>();
        //等于<dubbo:application name="first-dubbo-provider">
        service.setApplication(new ApplicationConfig("first-dubbo-provider"));
        //等于<dubbo:registry address="zookeeper://127.0.0.1:2181">
        service.setRegistry(new RegistryConfig("zookeeper://" + zkHost+ ":2181"));
        //等于<dubbo:service interface="...HelloWorldService">
        service.setInterface(HelloWorldService.class);
        //等于<dubbo:service ref="">
        service.setRef(new HelloWorldServiceImpl());
        //暴露服务
        service.export();

        System.out.println("provider service started");
        new CountDownLatch(1).await();

    }
}
