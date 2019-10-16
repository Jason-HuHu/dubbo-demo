package com.bill.service;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * 使用Service注解暴露服务
 * @author huxl
 */
@Service(timeout = 5000)
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String name) {
        return name;
    }
}
