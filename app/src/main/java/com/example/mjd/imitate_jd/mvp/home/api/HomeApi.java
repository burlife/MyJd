package com.example.mjd.imitate_jd.mvp.home.api;

import com.example.mjd.imitate_jd.mvp.classify.bean.Zean;
import com.example.mjd.imitate_jd.mvp.home.bean.HomeBean;
import com.example.mjd.imitate_jd.bean.Xiangbean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface HomeApi {
    @GET("getAd")
    Observable<HomeBean> getoneapi();
    @GET("getCatagory")
    Observable<Zean> getone_vp();
    @GET("getProductDetail?")
    Observable<Xiangbean> getone_xiang(@Query("pid") int pid);
}
