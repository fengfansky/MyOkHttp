package com.fan.okhttp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 2018/5/4.
 */

class RequestBody {

    /**
     * (1) <form>  表单提交 使用urlencode编码
     * POST http://www.example.com HTTP/1.1
     * Content-Type: application/x-www-form-urlencoded;charset=utf-8
     * <p>
     * （2）multipart/form-data 这种方式一般用来上传文件
     * POST http://www.example.com HTTP/1.1
     * Content-Type:multipart/form-data; boundary=----WebKitFormBoundaryrGKCBY7qhFd3TrwA
     * <p>
     * （3）application/json 告诉服务端消息主体是序列化后的 JSON 字符串
     * POST http://www.example.com HTTP/1.1
     * Content-Type: application/json;charset=utf-8
     * <p>
     * （4）text/xml
     * POST http://www.example.com HTTP/1.1
     * Content-Type: text/xml
     */

    private static final String CONTENT_TYPE = "application/x-www-form-urlencode";

    private static final String CHARSET = "utf-8";

    Map<String, String> encodeBodys = new HashMap<>();

    public String contentType() {
        return CONTENT_TYPE;
    }

    public long getContentLength() {
        return body().getBytes().length;
    }

    public String body() {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : encodeBodys.entrySet()) {
            sb.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    public RequestBody add(String name, String value) {
        try {
            encodeBodys.put(URLEncoder.encode(name, CHARSET), URLEncoder.encode(value, CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return this;
    }

}
