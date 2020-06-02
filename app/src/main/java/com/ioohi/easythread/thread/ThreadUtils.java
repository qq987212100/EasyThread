package com.ioohi.easythread.thread;


public class ThreadUtils {

    private static ThreadUtils threadUtils;
    private static BaseThreadFactory threadFactory;

    private ThreadUtils() {

    }

    public static synchronized ThreadUtils getInstance() {

        if (threadUtils == null) {
            threadUtils = new ThreadUtils();
        }

        if (threadFactory == null) {
            threadFactory = new BaseThreadFactory();
        }

        return threadUtils;
    }

    public BaseThreadFactory getThreadFactory() {
        return threadFactory;
    }
}
