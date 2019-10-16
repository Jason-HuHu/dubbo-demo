package com.huxl.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.huxl.dubbo.api.HelloWorldService;


/**
 * 消费者
 * @author huxl
 */
public class Consumer {


    private static String zkHost = System.getProperty("zookeeper.address","127.0.0.1");

    public static void main(String[] args) {
        ReferenceConfig<HelloWorldService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("first-demo-consumer"));
        reference.setRegistry(new RegistryConfig("zookeeper://" + zkHost + ":2181"));
        reference.setInterface(HelloWorldService.class);
        HelloWorldService service = reference.get();
        String msg  = service.sayHello("dubbo");

        System.out.println(msg);

    }
}
