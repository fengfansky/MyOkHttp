package com.fan.okhttp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.security.cert.CRL;
import java.util.Map;

/**
 * Created by Lenovo on 2018/5/4.
 */

class MHttpCodec {

    private static final String CRLE = "\r\n";

    private static final int CR = 13;

    private static final int LF = 10;

    private static final String SPACE = " ";

    private static final String VERSION = "HTTP/1.1";

    private static final String COLON = ":";


    public static final String HEAD_HOST = "Host";
    public static final String HEAD_CONNECTION = "Connection";
    public static final String HEAD_CONTENT_TYPE = "Content-type";
    public static final String HEAD_CONTENT_LENGTH = "Content-Length";
    public static final String HEAD_TRANSFER_ENCODING = "Transfer-Encoding";

    public static final String HEAD_VALUE_KEEP_ALIVE = "Keep-Alive";
    public static final String HEAD_VALUE_CHUNKED = "chunked";

    ByteBuffer byteBuffer;

    public MHttpCodec() {
        byteBuffer = ByteBuffer.allocate(10*1024);
    }

    public void writeRequest(OutputStream os, Request request) throws IOException {

        StringBuffer protocol = new StringBuffer();

        //请求行
        protocol.append(request.method());
        protocol.append(SPACE);
        protocol.append(request.httpUrl().file);
        protocol.append(SPACE);
        protocol.append(VERSION);
        protocol.append(CRLE);

        //http 请求头
        Map<String,String> headers = request.headers();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            protocol.append(entry.getKey());
            protocol.append(COLON);
            protocol.append(SPACE);
            protocol.append(entry.getValue());
            protocol.append(CRLE);
        }
        protocol.append(CRLE);

        //http请求体，如果存在
        RequestBody requestBody = request.requestBody();
        if (null != null){
            protocol.append(requestBody.body());
        }

        //write
        os.write(protocol.toString().getBytes());
        os.flush();
    }

    public Map<String ,String> readHeaders(InputStream is){
        while (true){

        }
    }

}
