package com.example.mylibrary;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by dkhs on 2017/10/30.
 */

public class TestPrint {
    public void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public void sout() {
        System.out.println("测试信息，已经调用了sout()方法");
    }
}