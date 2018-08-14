package com.example.dkhs.com.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by dkhs on 2018/8/14.
 */

public class OkHttpManager {

    private static OkHttpManager instance;

    public static final int CONNECT_TIME_OUT=2000;
    public static final int RED_TIME_OUT=2000;
    public static final int WRITE_TIME_OUT=2000;

    private OkHttpClient okHttpClient;

    private OkHttpManager(){
        okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(CONNECT_TIME_OUT,TimeUnit.MILLISECONDS)
                .readTimeout(RED_TIME_OUT,TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIME_OUT,TimeUnit.MILLISECONDS)
                .build();
    }

    public static OkHttpManager getInstance(){
        if(instance==null){
            synchronized (OkHttpManager.class){
                if(instance==null){
                    instance=new OkHttpManager();
                }
            }
        }
        return instance;
    }

    public OkHttpClient getOkHttpClient(){
        return  okHttpClient;
    }

    public OkHttpClient.Builder newOKClientBuilder() {
        return okHttpClient.newBuilder();
    }


}
