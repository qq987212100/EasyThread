package com.ioohi.easythread.listener;

import java.util.concurrent.ExecutorService;

public interface OnThreadPoolStateChangeListener {

    /**
     * 当线程开始执行
     * @param id 线程ID
     */
    void onThreadStart(int id);

    /**
     * 当线程执行完毕
     * @param id 线程ID
     */
    void onThreadEnd(int id);

    /**
     * 当线程出现错误
     * @param id 线程ID
     * @param e 所出现异常
     */
    void onThreadError(int id, Exception e);

    /**
     * 线程池执行完毕回调
     * @param executorService 执行完毕的线程池
     */
    void onThreadPoolEnd(ExecutorService executorService);

}
