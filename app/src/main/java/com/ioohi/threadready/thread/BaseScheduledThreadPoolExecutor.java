package com.ioohi.threadready.thread;


import com.ioohi.threadready.listener.OnThreadPoolStateChangeListener;
import com.ioohi.threadready.listener.OnThreadStateChangeListener;

import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;


public class BaseScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {

    private OnThreadPoolStateChangeListener changeListener;

    public void setOnThreadPoolStateChangeListener(OnThreadPoolStateChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    public BaseScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize);
    }

    public BaseScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory) {
        super(corePoolSize, threadFactory);
    }

    public BaseScheduledThreadPoolExecutor(int corePoolSize, RejectedExecutionHandler handler) {
        super(corePoolSize, handler);
    }

    public BaseScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, threadFactory, handler);
    }

    @Override
    public void execute(Runnable command) {

        if (command instanceof BaseThread) {
            ((BaseThread) command).setOnThreadStateChangeListener(new OnThreadStateChangeListener() {
                @Override
                public void onThreadStart(int id) {
                    changeListener.onThreadStart(id);
                }

                @Override
                public void onThreadEnd(int id) {
                    changeListener.onThreadEnd(id);
                }

                @Override
                public void onThreadError(int id, Exception e) {
                    changeListener.onThreadError(id, e);
                }
            });
        }
        //最后调用super
        super.execute(command);
    }

    @Override
    public Future<?> submit(Runnable task) {
        if (task instanceof BaseThread) {
            ((BaseThread) task).setOnThreadStateChangeListener(new OnThreadStateChangeListener() {
                @Override
                public void onThreadStart(int id) {
                    changeListener.onThreadStart(id);
                }

                @Override
                public void onThreadEnd(int id) {
                    changeListener.onThreadEnd(id);
                }

                @Override
                public void onThreadError(int id, Exception e) {
                    changeListener.onThreadError(id, e);
                }
            });
        }
        return super.submit(task);
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        if (task instanceof BaseThread) {
            ((BaseThread) task).setOnThreadStateChangeListener(new OnThreadStateChangeListener() {
                @Override
                public void onThreadStart(int id) {
                    changeListener.onThreadStart(id);
                }

                @Override
                public void onThreadEnd(int id) {
                    changeListener.onThreadEnd(id);
                }

                @Override
                public void onThreadError(int id, Exception e) {
                    changeListener.onThreadError(id, e);
                }
            });
        }
        return super.submit(task, result);
    }
}
