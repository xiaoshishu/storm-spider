package com.example.demo;

import com.example.annotation.annotation1.TestServiceImpl;
import com.example.demo.model.Student;
import com.example.demo.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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
        List<String> list = new ArrayList<>();

        //list.sort(Comparator.comparing());
        String string = "警惕";
        int a = string.compareTo("电信");
        System.out.println(a);

        for (int i = 0; i < 10; i ++) {
            for (int j = 0; j < 10 ;j++ ) {
                break;
            }
            System.out.println(i);
        }
    }


}
