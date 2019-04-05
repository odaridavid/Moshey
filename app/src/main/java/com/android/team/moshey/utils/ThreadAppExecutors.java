package com.android.team.moshey.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created By blackcoder
 * On 03/04/19
 **/
public final class ThreadAppExecutors {

    private static final Object LOCK = new Object();
    private static ThreadAppExecutors sInstance;
    private final Executor diskIO;
    private final Executor networkIO;

    private ThreadAppExecutors(Executor diskIO, Executor networkIO) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
    }

    public static ThreadAppExecutors getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new ThreadAppExecutors(
                        Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3));
            }
        }
        return sInstance;
    }

    public Executor diskIO() {
        return diskIO;
    }

    public Executor networkIO() {
        return networkIO;
    }

}
