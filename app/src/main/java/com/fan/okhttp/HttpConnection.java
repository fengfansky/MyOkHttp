package com.fan.okhttp;

import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.net.ssl.SSLSocketFactory;

/**
 * Created by Lenovo on 2018/5/4.
 */

public class HttpConnection {

    private static final String HTTPS = "https";
    private Socket socket;

    //最后使用的时间
    private long lastUseTime;
    private Request request;
    private InputStream inputStream;
    private OutputStream outputStream;

    public void sendRequest(Request request){
        this.request = request;
    }

    public void updateLastUseTime(){
        lastUseTime = System.currentTimeMillis();
    }

    /**
     * 服务器通信
     */
    public InputStream call(MHttpCodec httpCodec) throws IOException {
        try {
            createSocket();
            httpCodec.writeRequest(outputStream, request);
            return inputStream;
        } catch (IOException e) {
           closeSocket();
           throw new IOException();
        }
    }

    private void closeSocket() {
        if (socket != null){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createSocket() throws IOException {
        if (null == socket || socket.isClosed()){
            HttpUrl url = request.httpUrl();
            //是否是sslsocket
            if (url.protocol.equalsIgnoreCase(HTTPS)){
                socket = SSLSocketFactory.getDefault().createSocket();
            }else {
                socket = new Socket();
            }
            socket.connect(new InetSocketAddress(url.host, url.port));
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
        }
    }

    public boolean isSameAddress(String host, int port){
        if (null == socket){
            return false;
        }
        return TextUtils.equals(socket.getInetAddress().getHostName(),host) && port == socket.getPort();
    }

}
