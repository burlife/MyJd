package com.example.mjd.imitate_jd.mvp.car;

import com.example.mjd.imitate_jd.mvp.car.bean.DatasBean;
import com.example.mjd.imitate_jd.mvp.car.bean.MessageBean;

import java.util.List;


import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("product/getCarts")
    Flowable<MessageBean<List<DatasBean>>> getDatas(@Query("uid") String uid);
    @GET("product/deleteCart")
    Flowable<MessageBean> deleteData(@Query("uid") String uid, @Query("pid") String pid);
}
