package com.example.dkhs.bean;

import android.util.Log;

import com.example.dkhs.bean.Apple;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by dkhs on 2018/5/10.
 */

public class Producer implements Runnable {

    private ArrayBlockingQueue<Apple> blockingQueue;

    public Producer(ArrayBlockingQueue<Apple> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            produce();
        }
    }

    private void produce() {
        try {
            Apple apple = new Apple();
            blockingQueue.put(apple);
            Log.e("ljj", "生产：" + apple);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
