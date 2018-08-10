package com.example.dkhs.bean;

import com.example.dkhs.bean.iface.Fruits;

/**
 * Created by dkhs on 2018/1/30.
 */

public class Apple implements Fruits {
    @Override
    public void growing() {
        System.out.println("apple is growing");
    }
}
