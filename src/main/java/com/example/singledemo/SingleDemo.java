package com.example.singledemo;

import lombok.Synchronized;

/**
 * 双重锁的单例模式
 *
 */
public class SingleDemo {
    // 注意式volatile 关键字修饰的。
    private volatile static SingleDemo singleDemo;

    private SingleDemo(){}

    public static SingleDemo getInstance(){
        if (singleDemo == null) {
            synchronized (SingleDemo.class){
                if (singleDemo == null) {
                    singleDemo = new SingleDemo();
                }
            }
        }
        return  singleDemo;
    }


}
