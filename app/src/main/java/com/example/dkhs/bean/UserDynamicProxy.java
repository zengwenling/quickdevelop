package com.example.dkhs.bean;

import com.example.dkhs.bean.iface.Buy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by dkhs on 2018/1/23.
 */

public class UserDynamicProxy implements InvocationHandler {

    private Buy user;

    public UserDynamicProxy(Buy user) {
        this.user = user;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();
        if (name.equals("buyHouse")) {
            int monery = (int) args[0];
            args[0] = (int) (monery * 0.8);
        }
        return method.invoke(user, args);
    }
}
