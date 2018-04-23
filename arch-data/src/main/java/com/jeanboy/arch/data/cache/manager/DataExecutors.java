package com.jeanboy.arch.data.cache.manager;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by jeanboy on 2017/9/29.
 */

public class DataExecutors {

    private final Executor singleExecutor;
    private final Executor multipleExecutor;

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
        this(Executors.newSingleThreadExecutor(), Executors.newCachedThreadPool());
    }

    private DataExecutors(Executor singleExecutor, Executor multipleExecutor) {
        this.singleExecutor = singleExecutor;
        this.multipleExecutor = multipleExecutor;
    }

    public void load(Runnable runnable) {
        multipleExecutor.execute(runnable);
    }

    public void put(Runnable runnable) {
        singleExecutor.execute(runnable);
    }
}
