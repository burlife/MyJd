package com.example.mjd.imitate_jd.utils;


import com.example.mjd.imitate_jd.bean.Sy_bean;

import retrofit2.http.GET;
import rx.Observable;

public interface SYUrl {
    //首页
    @GET("v5/newlist?page=1&size=20&ver=1512745500001&pdduid=3470667255")
    Observable<Sy_bean> getSy();
}
