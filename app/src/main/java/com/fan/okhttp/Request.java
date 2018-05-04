package com.fan.okhttp;

import android.text.TextUtils;

import java.net.MalformedURLException;
import java.util.Map;

/**
 * Created by Lenovo on 2018/5/4.
 */

public class Request {

    private HttpUrl httpUrl;
    private Map<String, String> headers;
    private String method;
    private RequestBody requestBody;

    public Request(Builder builder) {
        this.httpUrl = builder.url;
        this.headers = builder.headers;
        this.method = builder.method;
        this.requestBody = builder.requestBody;
    }

    public String method() {

        return method;
    }

    public HttpUrl httpUrl() {
        return httpUrl;
    }

    public Map<String, String> headers() {
        return headers;
    }

    public RequestBody requestBody() {
        return requestBody;
    }

    /**
     * 构造者模式
     */
    public final static class Builder {

        private HttpUrl url;
        private Map<String, String> headers;
        private String method;
        private RequestBody requestBody;

        public Builder url(String url){
            try {
                this.url = new HttpUrl(url);
            } catch (MalformedURLException e) {
               throw new IllegalArgumentException("http url 格式错误");
            }
            return this;
        }

        public Builder addHeader(String name, String value){
            headers.put(name, value);
            return this;
        }

        public Builder removeHeader(String name){
            headers.remove(name);
            return this;
        }

        public Builder get(){
            method = "GET";
            return this;
        }

        public Builder post(RequestBody requestBody){
            method = "POST";
            this.requestBody = requestBody;
            return this;
        }

        public Request build(){
            if (url == null){
                throw new IllegalStateException("url == null");
            }
            if (TextUtils.isEmpty(method)){
                method = "GET";
            }
            return new Request(this);
        }
    }
}
