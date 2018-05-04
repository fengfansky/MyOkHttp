package com.fan.okhttp.chain;

import com.fan.okhttp.Call;
import com.fan.okhttp.Response;

import java.io.IOException;

/**
 * Created by Lenovo on 2018/5/4.
 */

public class RetryInterceptor implements Interceptor {
    @Override
    public Response intercept(InterceptorChain interceptorChain) throws IOException {
        Call call = interceptorChain.call;
        IOException exception = null;
        for (int i=0; i < interceptorChain.call.getClient().retrys(); i ++){
            if (call.isCanceled()){
                throw new IOException("Canceled!");
            }
            Response response = null;
            try {
                response = interceptorChain.proceed();
            } catch (IOException e) {
                exception = e;
            }
            return response;
        }
        throw exception;
    }
}
