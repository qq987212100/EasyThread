package com.ioohi.easythread.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class BaseExecutorService {

    public static BaseThreadPoolExecutor newCachedThreadPool() {
        return new BaseThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }


    public static BaseThreadPoolExecutor newCachedThreadPool(ThreadFactory threadFactory) {
        return new BaseThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                threadFactory);
    }

    public static BaseThreadPoolExecutor newFixedThreadPool(int nThreads) {
        return new BaseThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }


    public static BaseThreadPoolExecutor newFixedThreadPool(int nThreads, ThreadFactory threadFactory) {
        return new BaseThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                threadFactory);
    }

    public static BaseScheduledThreadPoolExecutor newScheduledThreadPool(int corePoolSize) {
        return new BaseScheduledThreadPoolExecutor(corePoolSize);
    }

    public static BaseScheduledThreadPoolExecutor newScheduledThreadPool(
            int corePoolSize, ThreadFactory threadFactory) {
        return new BaseScheduledThreadPoolExecutor(corePoolSize, threadFactory);
    }
}
