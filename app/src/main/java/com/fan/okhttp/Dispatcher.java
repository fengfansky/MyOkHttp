package com.fan.okhttp;

import android.util.Log;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lenovo on 2018/5/4.
 */

class Dispatcher {

    //最多同时请求
    private int maxRequests;
    //同一个host同时最多请求
    private int maxRequestsPerHost;

    private ExecutorService executorService;

    /**
     * 等待执行的队列
     */
    private final Deque<Call.AsyncCall> readyAsyncCalls = new ArrayDeque<>();

    /**
     * 正在执行的队列
     */
    private final Deque<Call.AsyncCall> runingAsyncCalls = new ArrayDeque<>();


    public Dispatcher() {

    }

    public Dispatcher(int maxRequests, int maxRequestsPerHost) {
        this.maxRequests = maxRequests;
        this.maxRequestsPerHost = maxRequestsPerHost;
    }

    /**
     * 线程池
     */
    public synchronized ExecutorService createExecutor() {
        if (executorService == null) {
            ThreadFactory threadFactory = new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r, "Http Client");
                    return thread;
                }
            };
            executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60,
                    TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), threadFactory);
        }
        return executorService;
    }

    /**
     * 加入执行队列
     * @param call
     */
    public void enqueue(Call.AsyncCall call) {
        Log.e("Dispatcher", "同时有:" + runingAsyncCalls.size());
        Log.e("Dispatcher", "host 同时有:" + runningCallForHost(call));

        if (runingAsyncCalls.size() < maxRequests && runningCallForHost(call) < maxRequestsPerHost) {
            Log.e("Dispatcher", "提交执行");
            runingAsyncCalls.add(call);
            createExecutor().execute(call);
        } else {
            Log.e("Dispatcher", "等待执行");
            readyAsyncCalls.add(call);
        }
    }

    private int runningCallForHost(Call.AsyncCall call) {
        int count = 0;
        //如果执行这个请求，则相同的host数量就是result
        for (Call.AsyncCall runingCall : runingAsyncCalls) {
            if (runingCall.host().equals(call.host())) {
                count++;
            }
        }
        return count;
    }

    /**
     * 请求结束 移出正在运行的队列并判断是否执行等待队列的请求
     *
     */
    public void fi



}
