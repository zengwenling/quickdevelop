package com.example.dkhs.bean;

import android.util.Log;

import com.example.dkhs.bean.iface.Buy;

/**
 * Created by dkhs on 2018/1/23.
 */

public class User implements Buy {
    @Override
    public void buyHouse(int money) {
        Log.e("ljj", "买房子用了" + money + "元");
    }
}
