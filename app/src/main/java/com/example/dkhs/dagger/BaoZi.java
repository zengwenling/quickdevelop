package com.example.dkhs.dagger;


import javax.inject.Inject;

/**
 * Created by dkhs on 2018/8/28.
 */

public class BaoZi {

    private String name;

    @Inject
    public BaoZi() {
        name = "小笼包";
    }

    public BaoZi(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
