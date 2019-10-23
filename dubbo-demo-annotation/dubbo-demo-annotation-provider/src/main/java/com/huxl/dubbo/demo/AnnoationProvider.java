package com.huxl.dubbo.demo;

import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.concurrent.CountDownLatch;

/**
 * @author huxl
 */
public class AnnoationProvider {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.start();
        new CountDownLatch(1).await();
    }


    @Configuration
    @EnableDubbo(scanBasePackages = "com.huxl.dubbo.demo.service.impl",multipleConfig = true)
    @PropertySource("classpath:dubbo-provider.properties")
    static public class ProviderConfiguration {

        @Bean
        public ProviderConfig providerConfig(){
            ProviderConfig providerConfig = new ProviderConfig();
            providerConfig.setTimeout(1000);
            return providerConfig;
        }
    }

}
