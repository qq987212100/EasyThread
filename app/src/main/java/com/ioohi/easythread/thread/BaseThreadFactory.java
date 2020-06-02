package com.ioohi.easythread.thread;


import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class BaseThreadFactory implements ThreadFactory {

    private static final AtomicInteger THREAD_NUMBER = new AtomicInteger(1);
    private AtomicInteger mThreadNum = new AtomicInteger(1);
    private String prefix;
    private boolean daemoThread;

    public BaseThreadFactory() {
        this("base-threadpool-" + THREAD_NUMBER.getAndIncrement(), false);
    }

    public BaseThreadFactory(String prefix) {
        this(prefix, false);
    }

    public BaseThreadFactory(String prefix, boolean daemo) {
        this.prefix = prefix + "-thread-";
        daemoThread = daemo;
    }

    @Override
    public BaseThread newThread(Runnable runnable) {
        String name = prefix + mThreadNum.getAndIncrement();
        BaseThread baseThread = new BaseThread();
        baseThread.setName(name);
        baseThread.setRunnable(runnable);
        baseThread.setDaemon(daemoThread);
        return baseThread;
    }

    public BaseThread newThread(Runnable runnable, int id) {
        String name = prefix + mThreadNum.getAndIncrement();
        BaseThread baseThread = new BaseThread();
        baseThread.setName(name);
        baseThread.setRunnable(runnable);
        baseThread.setDaemon(daemoThread);
        baseThread.setid(id);
        return baseThread;
    }

    public BaseThread newThread(Runnable runnable, int id, String name) {
        BaseThread baseThread = new BaseThread();
        baseThread.setName(name);
        baseThread.setRunnable(runnable);
        baseThread.setDaemon(daemoThread);
        baseThread.setid(id);
        return baseThread;
    }
}
