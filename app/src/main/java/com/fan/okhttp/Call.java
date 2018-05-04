package com.fan.okhttp;

import java.io.IOException;

/**
 * Created by Lenovo on 2018/5/4.
 */

public class Call {

    Request request;
    HttpClient client;

    boolean isCanceled;

    public Call(Request request, HttpClient client, boolean isCanceled) {
        this.request = request;
        this.client = client;
        this.isCanceled = isCanceled;
    }

    public Request getRequest() {
        return request;
    }

    public HttpClient getClient() {
        return client;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    final class AsyncCall implements Runnable{

        private final Callback callback;

        public AsyncCall(Callback callback) {
            this.callback = callback;
        }

        @Override
        public void run() {
            Response response = null;
            try {
                response = getResponse();

                if (isCanceled){
                    callback.onFailure(Call.this, new IOException("Canceled"));
                }else {
                    callback.onSuccess(Call.this, response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                client.dispatcher()
            }

        }

        public String host() {

            return null;
        }
    }

    private Response getResponse() throws IOException{

        return null;
    }
}
