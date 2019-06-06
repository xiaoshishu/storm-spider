package com.example.annotation.annotation1;

import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @MyLog("test")
    @Override
    public void test() {

    }




}
