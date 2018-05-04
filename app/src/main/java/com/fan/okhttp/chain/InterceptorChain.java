package com.fan.okhttp.chain;

import com.fan.okhttp.Call;
import com.fan.okhttp.HttpConnection;
import com.fan.okhttp.Response;

import java.io.IOException;
import java.util.List;

/**
 * Created by Lenovo on 2018/5/4.
 */

public class InterceptorChain {

    final List<Interceptor> interceptors;
    final int index;
    final Call call;
    final HttpConnection httpConnection;

    public InterceptorChain(List<Interceptor> interceptors, int index, Call call, HttpConnection httpConnection) {
        this.interceptors = interceptors;
        this.index = index;
        this.call = call;
        this.httpConnection = httpConnection;
    }

    public Response proceed() throws IOException {
        Interceptor interceptor = interceptors.get(index);
        InterceptorChain nextInterceptorChain = new InterceptorChain(interceptors, index + 1, call, httpConnection);
        return interceptor.intercept(nextInterceptorChain);
    }

}
