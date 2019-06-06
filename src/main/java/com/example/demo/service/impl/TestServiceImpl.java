package com.example.demo.service.impl;

import com.example.annotation.annotation1.MyLog;
import com.example.annotation.annotation1.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @MyLog("test")
    @Override
    public void test() {
        this.getClass().getAnnotations();
    }




}
