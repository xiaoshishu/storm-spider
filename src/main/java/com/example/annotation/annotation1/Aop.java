package com.example.annotation.annotation1;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aop {

    public static void main(String[] args) {

    }

    @MyLog("test")
    public void test() {

    }

}
