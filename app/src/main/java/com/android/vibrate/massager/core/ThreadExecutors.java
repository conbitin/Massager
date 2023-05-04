/*
 * Created by  on 9/11/21 10:07 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 8/19/21 9:05 AM
 */

package com.android.vibrate.massager.core;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadExecutors {

    // Number of cores to decide the number of threads
    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private static final int THREAD_POOL_SIZE_NETWORK = 3;

    private static final ThreadExecutors ourInstance = new ThreadExecutors();

    public static ThreadExecutors getInstance() {
        return ThreadExecutors.ourInstance;
    }

    private final Executor diskIO;
    private final Executor networkIO;
    private final Executor mainThread;
    private final Executor workerThread;

    private ThreadExecutors() {
        this.diskIO = Executors.newSingleThreadExecutor();
        this.networkIO = Executors.newFixedThreadPool(ThreadExecutors.THREAD_POOL_SIZE_NETWORK);
        this.mainThread = new MainThreadExecutor();
        this.workerThread = new ThreadPoolExecutor(
                ThreadExecutors.NUMBER_OF_CORES * 2,
                ThreadExecutors.NUMBER_OF_CORES * 2,
                60L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    public Executor getDiskIO() {
        return this.diskIO;
    }

    public Executor getNetworkIO() {
        return this.networkIO;
    }

    public Executor getMainThread() {
        return this.mainThread;
    }

    public Executor getWorkerThread() {
        return this.workerThread;
    }

    public static void execute(final Where where, final Runnable command) {
        switch (where) {
            case DISK:
                ThreadExecutors.getInstance().getDiskIO().execute(command);
                break;
            case NETWORK:
                ThreadExecutors.getInstance().getNetworkIO().execute(command);
                break;
            case WORKER:
                ThreadExecutors.getInstance().getWorkerThread().execute(command);
                break;
            case MAIN:
                ThreadExecutors.getInstance().getMainThread().execute(command);
                break;
        }
    }

    public enum Where {
        DISK, NETWORK, WORKER, MAIN
    }

    private class MainThreadExecutor implements Executor {
        private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(final Runnable command) {
            this.mainThreadHandler.post(command);
        }
    }
}
