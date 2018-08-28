package com.example.dkhs.dagger.module;

import com.example.dkhs.dagger.BaoZi;
import com.example.dkhs.dagger.Noodle;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dkhs on 2018/8/28.
 */
@Module
public class ShangjiaAModule {

    @Provides
    public BaoZi provideBaoZi() {
        return new BaoZi("豆沙包");
    }

    @Provides
    public Noodle provideNoodle() {
        return new Noodle();
    }
}
