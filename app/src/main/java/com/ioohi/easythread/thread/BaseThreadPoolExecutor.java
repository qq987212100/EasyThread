package com.ioohi.easythread.thread;


import androidx.annotation.NonNull;

import com.ioohi.easythread.listener.OnThreadPoolStateChangeListener;
import com.ioohi.easythread.listener.OnThreadStateChangeListener;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BaseThreadPoolExecutor extends ThreadPoolExecutor {

    private OnThreadPoolStateChangeListener changeListener;

    public void setOnThreadPoolStateChangeListener(OnThreadPoolStateChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    public BaseThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public BaseThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public BaseThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public BaseThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    public void execute(Runnable command) {
        if (command instanceof BaseThread) {
            ((BaseThread) command).setOnThreadStateChangeListener(new OnThreadStateChangeListener() {
                @Override
                public void onThreadStart(int id) {
                    if (changeListener != null) {
                        changeListener.onThreadStart(id);
                    }
                }

                @Override
                public void onThreadEnd(int id) {
                    if (changeListener != null) {
                        changeListener.onThreadEnd(id);
                    }

                    if (getTaskCount() == getCompletedTaskCount() + 1) {
                        if (changeListener != null) {
                            changeListener.onThreadPoolEnd(BaseThreadPoolExecutor.this);
                        }
                    }
                }

                @Override
                public void onThreadError(int id, Exception e) {
                    if (changeListener != null) {
                        changeListener.onThreadError(id, e);
                    }
                }
            });
        }
        //最后调用super
        super.execute(command);
    }

    @Override
    @NonNull
    public Future<?> submit(Runnable task) {
        if (task instanceof BaseThread) {
            ((BaseThread) task).setOnThreadStateChangeListener(new OnThreadStateChangeListener() {
                @Override
                public void onThreadStart(int id) {
                    if (changeListener != null) {
                        changeListener.onThreadStart(id);
                    }
                }

                @Override
                public void onThreadEnd(int id) {
                    if (changeListener != null) {
                        changeListener.onThreadEnd(id);
                    }

                    if (getTaskCount() == getCompletedTaskCount()) {
                        if (changeListener != null) {
                            changeListener.onThreadPoolEnd(BaseThreadPoolExecutor.this);
                        }
                    }
                }

                @Override
                public void onThreadError(int id, Exception e) {
                    if (changeListener != null) {
                        changeListener.onThreadError(id, e);
                    }
                }
            });
        }
        return super.submit(task);
    }

    @Override
    @NonNull
    public <T> Future<T> submit(Runnable task, T result) {
        if (task instanceof BaseThread) {
            ((BaseThread) task).setOnThreadStateChangeListener(new OnThreadStateChangeListener() {
                @Override
                public void onThreadStart(int id) {
                    if (changeListener != null) {
                        changeListener.onThreadStart(id);
                    }
                }

                @Override
                public void onThreadEnd(int id) {
                    if (changeListener != null) {
                        changeListener.onThreadEnd(id);
                    }

                    if (getTaskCount() == getCompletedTaskCount()) {
                        if (changeListener != null) {
                            changeListener.onThreadPoolEnd(BaseThreadPoolExecutor.this);
                        }
                    }
                }

                @Override
                public void onThreadError(int id, Exception e) {
                    if (changeListener != null) {
                        changeListener.onThreadError(id, e);
                    }
                }
            });
        }
        return super.submit(task, result);
    }

}
