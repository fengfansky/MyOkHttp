package com.fan.okhttp;

/**
 * Created by Lenovo on 2018/5/4.
 */

public interface Callback {
    void onFailure(Call call, Throwable throwable);
    void onSuccess(Call call, Response response);
}
