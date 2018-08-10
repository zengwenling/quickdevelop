package com.example.dkhs.bean;

import android.util.Log;

import com.example.dkhs.bean.Apple;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by dkhs on 2018/5/10.
 */

public class Consumer implements Runnable {

    private ArrayBlockingQueue<Apple> blockingQueue;

    public Consumer(ArrayBlockingQueue<Apple> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            comsume();
        }
    }

    private void comsume() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
            Apple apple = blockingQueue.take();
            Log.e("ljj", "消费：" + apple);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
