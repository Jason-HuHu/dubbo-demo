package com.huxl.dubbo.demo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.huxl.dubbo.demo.IAnnotationService;

@Service
public class AnnotationServiceImpl implements IAnnotationService {
    @Override
    public String hello(String str) {

        System.out.println("async provider received: " + str);

        return "Annotation Service:" + str;
    }
}
