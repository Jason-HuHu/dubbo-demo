package com.huxl.dubbo.demo;

import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.*;

/**
 * @author huxl
 */
public class AnnoationProvider {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        context.start();
        System.in.read();
    }


    @Configuration
    @EnableDubbo(scanBasePackages = "com.huxl.dubbo.demo.service.impl",multipleConfig = true)
    static public class ProviderConfiguration {

        @Bean
        public ProviderConfig providerConfig(){
            ProviderConfig providerConfig = new ProviderConfig();
            providerConfig.setTimeout(1000);
            return providerConfig;
        }
    }

}
