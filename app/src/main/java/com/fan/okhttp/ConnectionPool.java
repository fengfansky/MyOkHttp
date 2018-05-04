package com.fan.okhttp;

import android.support.annotation.NonNull;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lenovo on 2018/5/4.
 */

class ConnectionPool {

    private long keepAlive;

    private boolean cleanupRunning;

    private Deque<HttpConnection> connections = new ArrayDeque<>();

    private Runnable cleanupRunnable = new Runnable() {
        @Override
        public void run() {

        }
    };

    private static final Executor executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(), new ThreadFactory() {
        @Override
        public Thread newThread(@NonNull Runnable r) {
            Thread thread = new Thread(r, "Connection Pool");
            thread.setDaemon(true);
            return thread;
        }
    });




}
