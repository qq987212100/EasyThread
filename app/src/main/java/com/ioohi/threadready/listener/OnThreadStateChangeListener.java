package com.ioohi.threadready.listener;

public interface OnThreadStateChangeListener {

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

}
