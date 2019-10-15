package com.huxl.dubbo.service.impl;

import com.huxl.dubbo.api.HelloWorldService;

public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public String sayHello(String msg) {
        System.out.println("Hello:" + msg);

        return "Hello,I'm helloWorld : " + msg;
    }
}
