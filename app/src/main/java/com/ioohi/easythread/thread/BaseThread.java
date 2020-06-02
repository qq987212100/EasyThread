package com.ioohi.easythread.thread;


import com.ioohi.easythread.listener.OnThreadStateChangeListener;

public class BaseThread extends Thread {

    private Runnable runnable;
    private int id;

    OnThreadStateChangeListener changeListener;

    public void setOnThreadStateChangeListener(OnThreadStateChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    public OnThreadStateChangeListener getOnThreadStateChangeListener(){
        return changeListener;
    }

    public BaseThread() {

    }

    public BaseThread(int id, Runnable runnable) {
        this.id = id;
        this.runnable = runnable;
    }

    public BaseThread(int id, Runnable runnable, String name) {
        this.id = id;
        this.runnable = runnable;
        setName(name);
    }

    public void setid(int id) {
        this.id = id;
    }

    public int getid(int id) {
        return id;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {

        try {
            if (changeListener != null) {
                changeListener.onThreadStart(id);
            }
            if (runnable != null) {
                runnable.run();
            }
        } catch (Exception e) {
            if (changeListener != null) {
                changeListener.onThreadError(id, e);
            }
        } finally {
            if (changeListener != null) {
                changeListener.onThreadEnd(id);
            }
        }
        //最后调用run()；
        super.run();
    }
}
