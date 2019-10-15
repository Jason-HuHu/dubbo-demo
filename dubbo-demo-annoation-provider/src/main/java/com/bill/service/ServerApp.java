package com.bill.service;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huxl
 */
@SpringBootApplication
@DubboComponentScan(basePackages = "com.bill.service")
public class ServerApp {

    public static void main(String[] args) {
        SpringApplication.run(ServerApp.class,args);
    }
}
