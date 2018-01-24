package com.alibaba.dubbo.demo.provider;

import com.alibaba.dubbo.demo.DemoServer;
import com.alibaba.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DemoServerImpl implements DemoServer {
    @Override
    public String sayGoodbye(String name) {
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return "Dubbo " + name + ", response form provider: " + RpcContext.getContext().getLocalAddress();

    }
}
