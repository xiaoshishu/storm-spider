package com.example.threadpool;

public class WorkerTest {

    public static void main(String[] args){
        DefaultThreadPool defaultThreadPool = new DefaultThreadPool(10);

        for (int i = 0; i< 10000; i++) {
            if (i == 30) {
                defaultThreadPool.addWorkers(30);
            }
            if (i == 200) {
                defaultThreadPool.removeWorkers(200);
            }

            Job job = new Job();
            defaultThreadPool.execute(job);
            System.out.println(defaultThreadPool.getJobSize());
        }

    }

}
