package com.example.dkhs.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.dkhs.commonlib.ui.BaseDKHSActivity;
import com.example.dkhs.R;
import com.example.dkhs.bean.User;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by dkhs on 2018/8/10.
 */

public class RxjavaActivity extends BaseDKHSActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

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

//
//       Observable<String> observableJust= Observable.just("a","b","c");
//        observableJust.observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//       .subscribe(observer);


    }
}
