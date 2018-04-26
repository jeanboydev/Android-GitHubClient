package com.jeanboy.arch.data.cache.manager;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by jeanboy on 2017/9/29.
 */

public class DataExecutors {

    private final Executor writeThread;
    private final Executor readThread;
    private final Executor mainThread;

    private static DataExecutors instance;

    public static DataExecutors getInstance() {
        if (instance == null) {
            synchronized (DataExecutors.class) {
                if (instance == null) {
                    instance = new DataExecutors();
                }
            }
        }
        return instance;
    }

    private DataExecutors() {
        this(Executors.newSingleThreadExecutor(), Executors.newCachedThreadPool(), new MainThreadExecutor());
    }

    public DataExecutors(Executor writeThread, Executor readThread, Executor mainThread) {
        this.writeThread = writeThread;
        this.readThread = readThread;
        this.mainThread = mainThread;
    }

    private static class MainThreadExecutor implements Executor {

        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }

    public void load(Runnable runnable) {
        readThread.execute(runnable);
    }

    public void put(Runnable runnable) {
        writeThread.execute(runnable);
    }

    public void post(Runnable runnable) {
        mainThread.execute(runnable);
    }
}
