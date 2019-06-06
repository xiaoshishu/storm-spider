package com.example.threadpool;

public class Job implements Runnable {
    @Override
    public void run() {
        try{
            Thread.sleep(2500);
        } catch (Exception  e){
            e.printStackTrace();
        }
        System.out.println("当前线程名称" + Thread.currentThread().getName() + "job 被执行了");
    }
}
