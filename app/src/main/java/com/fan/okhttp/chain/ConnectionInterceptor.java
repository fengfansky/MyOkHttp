package com.fan.okhttp.chain;

import com.fan.okhttp.HttpClient;
import com.fan.okhttp.HttpUrl;
import com.fan.okhttp.Request;
import com.fan.okhttp.Response;

/**
 * Created by Lenovo on 2018/5/4.
 */

public class ConnectionInterceptor implements Interceptor {
    @Override
    public Response intercept(InterceptorChain interceptorChain) {

        Request request = interceptorChain.call.getRequest();
        HttpClient client = interceptorChain.call.getClient();

        HttpUrl httpUrl = request.httpUrl();

        String host = httpUrl.getHost();

        client.connectionPool()

        return null;
    }
}
