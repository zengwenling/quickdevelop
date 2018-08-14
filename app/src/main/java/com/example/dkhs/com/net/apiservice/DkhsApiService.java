package com.example.dkhs.com.net.apiservice;

import com.example.dkhs.bean.AdsEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by dkhs on 2018/8/14.
 */

public interface DkhsApiService {

    @GET(DkhsUrl.GET_ADS_MODAL)
    Observable<AdsEntity> getAdsModel(@Path("code") String code);
}
