package com.example.demo;

import com.example.annotation.annotation1.TestServiceImpl;
import com.example.demo.model.Student;
import com.example.demo.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {

    @Autowired
    TestServiceImpl testService;

    @Test
    public void testInterrupt() throws Exception{
        Student user = new Student();
        Thread t1 = new Thread(){
            @Override
            public void run() {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("我被挂起了r");
                }
                while(true){
                    Thread.yield();
                }
            }
        };
        t1.start();
        Thread.sleep(100);
        t1.interrupt();



    }


    @Test
    public void testAnnation(){
        testService.test();
    }

    @Test
    public void testArray(){
        int[] array = new int[10];

        array[5] = 1;
        array[0] = 2;
        array[1] = 3;
        array[2] = 4;


        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }


    @Test
    public void testCompe(){
        // newCachedThreadPool 特点
        // 1.核心线程数为0 , 线程最大数量是Integer的最大值。
        // 2.线程的存活时间为60s
        // 3.用的阻塞队列是SynchronousQueue(同步队列)
        // 4.由于有一个任务就创建一个线程，

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i =0 ; i < 10 ; i++) {
            executorService.execute(() -> {
                System.out.println("可缓存线程池" + Thread.currentThread().getName() + "正在执行");
            });
        }
        // SingleThreadExecutor 特点
        //1.核心线程池和最大线程数都是1
        //2.线程存活时间是0 keepAliveTime
        //3.阻塞队列LinkedBlockingQueue
        //4.只有一个线程执行任务，如果当前线程被占用，则保存到阻塞队列中。
        ExecutorService executorServiceForSingle = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i ++) {
            executorServiceForSingle.execute(() -> {
                System.out.println("单例线程池" + Thread.currentThread().getName() + "正在执行");
            });
        }

        // FixedThreadPool 特点
        // 1.核心线程数和最大线程数一样大
        // 2.没有所谓的空闲时间 keepAliveTime为0
        // 3.阻塞队列用的LinkedBlockingQueue
        // 4.使用不当会抛出OOM异常，适用于CPU密集型。线程执行长期任务。
        ExecutorService executorServiceForFixed = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i ++) {
            executorServiceForFixed.execute(() -> {
                System.out.println("固定数目线程池" + Thread.currentThread().getName() + "正在执行");
            });
        }





    }


}
