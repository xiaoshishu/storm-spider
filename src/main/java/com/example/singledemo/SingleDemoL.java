package com.example.singledemo;

/**
 * 懒汉式
 * 多线程不安全调用效率不高 但是会延时加载
 */
public class SingleDemoL {

    private static SingleDemoL singleDemoL;

    private SingleDemoL(){}

    public static SingleDemoL getInstance(){
        if (singleDemoL == null) {
            singleDemoL = new SingleDemoL();
        }
        return  singleDemoL;
    }


}
