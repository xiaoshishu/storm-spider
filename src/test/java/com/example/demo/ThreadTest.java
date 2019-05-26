package com.example.demo;

import org.junit.Test;

public class ThreadTest {

    @Test
    public void testInterrupt() throws Exception{
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


}
