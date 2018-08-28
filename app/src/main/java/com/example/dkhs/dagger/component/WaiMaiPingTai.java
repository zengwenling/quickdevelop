package com.example.dkhs.dagger.component;

import com.example.dkhs.dagger.ZhaiNan;
import com.example.dkhs.dagger.module.ShangjiaAModule;

import dagger.Component;

/**
 * Created by dkhs on 2018/8/28.
 */
@Component(modules = ShangjiaAModule.class)
public interface WaiMaiPingTai {
    ZhaiNan waimai();
}
