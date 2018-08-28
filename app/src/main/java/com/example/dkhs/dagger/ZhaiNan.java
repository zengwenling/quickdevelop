package com.example.dkhs.dagger;

import javax.inject.Inject;

/**
 * Created by dkhs on 2018/8/28.
 */

public class ZhaiNan {
    @Inject
    BaoZi baozi;

    @Inject
    Noodle noodle;

    @Inject
    public ZhaiNan() {

    }

    public String eat() {
        StringBuilder sb = new StringBuilder();
        sb.append("我吃的是 ");
        if (baozi != null) {
            sb.append(baozi.toString());
        }

        if (noodle != null) {
            sb.append("  ");
            sb.append(noodle.toString());
        }
        return sb.toString();
    }
}
