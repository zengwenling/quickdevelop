package com.example.dkhs.com.factory;

import com.example.dkhs.bean.Apple;
import com.example.dkhs.bean.Banana;
import com.example.dkhs.bean.iface.Fruits;

/**
 * Created by dkhs on 2018/1/30.
 */

public class MyFactory {

    public static Fruits creatFruits(String name) {
        Fruits fruits;
        if (name.equals("apple")) {
            fruits = new Apple();
        } else {
            fruits = new Banana();
        }
        return fruits;
    }

    public static Fruits creatFruits(Class clazz) {
        Fruits result = null;
        try {
            result = (Fruits) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
