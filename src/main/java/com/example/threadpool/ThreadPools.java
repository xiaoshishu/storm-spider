package com.example.threadpool;

public interface ThreadPools<Job extends Runnable> {

    /**
     * 执行一个job
     */
    public  void execute(Job job);

    /**
     * 关闭线程池
     */
    public void shutdown();

    /**
     * 添加工作线程
     * @param num
     */
    public void addWorkers(int num);

    /**
     * 减少工作线程
     * @param num
     */
    public void removeWorkers(int num);

    /**
     * 获取正在等待的任务数
     * @return
     */

    public int getJobSize();

}
