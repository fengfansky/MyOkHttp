package com.fan.okhttp.chain;

import com.fan.okhttp.Response;

import java.io.IOException;

/**
 * Created by Lenovo on 2018/5/4.
 */

public interface Interceptor {
    public Response intercept(InterceptorChain interceptorChain) throws IOException;
}
