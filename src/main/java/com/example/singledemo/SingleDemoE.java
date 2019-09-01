package com.example.singledemo;

/**
 * 单例饿汉式
 * 线程安全的，调用效率高，但是不能延时加载
 * 一上来就创建实例，如果不用的话，会浪费资源
 */
public class SingleDemoE {

    private static SingleDemoE singleDemoE = new SingleDemoE();

    private SingleDemoE(){}

    public static SingleDemoE getInstance(){
        return  singleDemoE;
    }


}
