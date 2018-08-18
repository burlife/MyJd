package com.example.mjd.imitate_jd.utils;


import com.example.mjd.imitate_jd.bean.Bn_Bean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;



public interface BNUrl {
    //详情页
    @GET("v5/goods/{goods_id}?pdduid=3470667255")
    Observable<Bn_Bean> getPj(@Path("goods_id") String goods_id);
}
