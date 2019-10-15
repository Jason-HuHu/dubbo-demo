package com.huxl.dubbo.api;

/**
 * 接口
 * @author huxl
 */
public interface HelloWorldService {

    /**
     * 通知方法
     * @param msg 消息
     * @return 处理后的消息
     */
    String sayHello(String msg);
}
