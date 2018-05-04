package com.fan.okhttp;

/**
 * Created by Lenovo on 2018/5/4.
 */

public class HttpClient {

    private final Dispatcher dispatcher;
    private final ConnectionPool connectionPool;
    private final int retrys;


    public HttpClient(Builder builder){
        this.dispatcher = builder.dispatcher;
        this.connectionPool = builder.connectionPool;
        this.retrys = builder.retrys;
    }

    public Dispatcher dispatcher() {
        return dispatcher;
    }

    public ConnectionPool connectionPool() {
        return connectionPool;
    }

    public int retrys() {
        return retrys;
    }

    public static class Builder{
        Dispatcher dispatcher;
        ConnectionPool connectionPool;
        int retrys;


        public Builder dispatcher(Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
            return this;
        }

        public Builder connectionPool(ConnectionPool connectionPool) {
            this.connectionPool = connectionPool;
            return this;
        }

        public Builder retrys(int retrys) {
            this.retrys = retrys;
            return this;
        }

        public HttpClient build(){
            if (null == dispatcher){
                dispatcher = new Dispatcher();
            }
            if (null == connectionPool){
                connectionPool = new ConnectionPool();
            }
            return new HttpClient(this);
        }

    }


}
