package com.huxl.dubbo;

import com.huxl.dubbo.api.HelloWorldService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

/**
 * 服务提供者
 * @author huxl
 */
public class Provider {

    private static String zkHost = System.getProperty("zookeeper.address","127.0.0.1");

    public static void main(String[] args) {
        ServiceConfig<HelloWorldService> service = new ServiceConfig<>();
        service.setApplication(new ApplicationConfig("first-dubbo-provider"));
        service.setRegistry(new RegistryConfig("zookeeper://" + zkHost+ "2181"));
        service.setInterface(HelloWorldService.class);
        //暴露服务
        service.export();
    }
}
