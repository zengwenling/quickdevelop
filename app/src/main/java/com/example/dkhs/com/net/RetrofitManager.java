package com.example.dkhs.com.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dkhs on 2018/8/14.
 */

public class RetrofitManager {

    public static final String BASE_DEV_URL = "http://dev.dev.dkhs.com";
    private static RetrofitManager instance;

    private Retrofit retrofit;

    private RetrofitManager(){
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_DEV_URL)
                .client(OkHttpManager.getInstance().getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    public static RetrofitManager getInstance(){
        if(instance==null){
            synchronized (RetrofitManager.class){
                if(instance==null){
                    instance=new RetrofitManager();
                }
            }
        }
        return instance;
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }
}
