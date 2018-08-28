package com.example.dkhs.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.dkhs.commonlib.ui.BaseDKHSActivity;
import com.example.dkhs.R;
import com.example.dkhs.bean.AdsEntity;
import com.example.dkhs.bean.User;
import com.example.dkhs.com.net.RetrofitManager;
import com.example.dkhs.com.net.apiservice.DkhsApiService;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


/**
 * Created by dkhs on 2018/8/10.
 */

public class RxjavaActivity extends BaseDKHSActivity {

    public static final String TAG=RxjavaActivity.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
//        testThread();
//        testZip();
        testRetrofit();
    }

    public void testRetrofit() {
        DkhsApiService service = RetrofitManager.getInstance().getRetrofit().create(DkhsApiService.class);
        Observable<List<AdsEntity>> observable = service.getAdsModel("splash");
        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<AdsEntity>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e("ljj","onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull List<AdsEntity> adsEntity) {
                        Log.e("ljj","onNext");
//                        Toast.makeText(RxjavaActivity.this, adsEntity.getDescription(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("ljj","onError"+e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("ljj","onComplete");
                    }
                });

    }

    private void testThread(){
        Observer<String> observer=new Observer<String>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e("ljj","onSubscribe threadname:"+Thread.currentThread().getName());
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.e("ljj","onNext:"+s+">>"+Thread.currentThread().getName());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("ljj","onError"+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e("ljj","onComplete");
            }
        };


        Observable<String> observable=Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                Log.e("ljj","current thread:"+Thread.currentThread().getName());
                emitter.onNext("first");
                emitter.onNext("second");
            }
        });
        observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(observer);

    }

    private void testZip(){
        Observable.zip(Observable.intervalRange(1, 5, 1, 1, TimeUnit.SECONDS)
                        .map(new Function<Long, String>() {
                            @Override
                            public String apply(Long aLong) throws Exception {
                                String s1 = "A" + aLong;
                                Log.d(TAG, "===================A 发送的事件 " + s1);
                                return s1;
                            }}),
                Observable.intervalRange(1, 6, 1, 1, TimeUnit.SECONDS)
                        .map(new Function<Long, String>() {
                            @Override
                            public String apply(Long aLong) throws Exception {
                                String s2 = "B" + aLong;
                                Log.d(TAG, "===================B 发送的事件 " + s2);
                                return s2;
                            }
                        }),
                new BiFunction<String, String, String>() {
                    @Override
                    public String apply(String s, String s2) throws Exception {
                        String res = s + s2;
                        return res;
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "===================onSubscribe ");
                    }
                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "===================onNext " + s);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "===================onError ");
                    }
                    @Override
                    public void onComplete() {
                        Log.d(TAG, "===================onComplete ");
                    }
                });
    }
}
