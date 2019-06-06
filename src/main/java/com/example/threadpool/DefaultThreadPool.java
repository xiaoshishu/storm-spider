package com.example.threadpool;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultThreadPool<Job extends Runnable> implements ThreadPools<Job> {

    /**
     * 线程池维护工作者最大数量
     * @param runnable
     */
    private static final int MAX_WORKER_NUMBERS = 30;

    /**
     * 线程池的默认工作数量
     * @param runnable
     */
    private static final int DEFAULT_WORKER_NUMBERS = 5;

    /**
     * 线程池最小工作数量
     * @param runnable
     */
    private static final int MIN_WORKER_NUMBERS = 1;

    /**
     * 维护一个工作列表 , 里面加入客户端发起的工作
     * @param runnable
     */
    private final LinkedList<Job> jobs = new LinkedList<>();

    /**
     * 工作者线程的列表
     * @param runnable
     */
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<>());

    /**
     * 工作者线程数量
     * @param runnable
     */
    private int workernum;

    /**
     * 每个工作者编号生成
     * @param runnable
     */
    private AtomicLong atomicLong = new AtomicLong();

    /**
     * 第一步  构造函数，先初始化线程池
     *
     */
    public DefaultThreadPool(int workernum){
        if (workernum > MAX_WORKER_NUMBERS) {
            this.workernum = DEFAULT_WORKER_NUMBERS;
        } else {
            this.workernum = workernum;
        }
        initWorkers(workernum);
    }

    private void initWorkers(int num){
        for (int i = 0; i < num; i++) {
             Worker worker = new Worker();
             workers.add(worker);
             Thread thread = new Thread(worker);
             thread.start();
        }
    }


    @Override
    public void execute(Job job) {
        if (job == null) {
            throw new NullPointerException();
        } else {
            synchronized (jobs){
                jobs.add(job);
                jobs.notify();
            }
        }
    }

    @Override
    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs){
            if (num + this.workernum > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - this.workernum;
            }
            initWorkers(num);
            this.workernum +=num;
        }
    }

    @Override
    public void removeWorkers(int num) {
        synchronized (jobs){
            if (num >= this.workernum) {
                throw new IllegalArgumentException("超过了已有的线程数量");
            }
            for (int i = 0; i < num; i++) {
                Worker worker = workers.get(i);
                if (worker != null) {
                    worker.shutdown();
                    workers.remove(i);
                }
            }
            this.workernum = this.workernum - num;
        }
    }

    @Override
    public int getJobSize() {
        return workers.size();
    }

    class Worker implements Runnable{
        // 表示该worker 是否运行
        private volatile  boolean running = true;

        @Override
        public void run() {
            while(running){
                Job job = null;
                synchronized (jobs){
                    if (jobs.isEmpty()) {
                        try{
                            jobs.wait();
                        } catch (Exception e){
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobs.removeFirst();
                }
                if (job != null) {
                    job.run();
                }
            }
        }


        /**
         * 终止该线程
         */
        public void shutdown() {
            running = false;
        }

    }


}
