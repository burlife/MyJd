package com.example.mjd.imitate_jd.net;


import com.example.mjd.imitate_jd.bean.Five_toubean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface FiveAPI {
    @GET("user/getUserInfo?")
    Observable<Five_toubean> getFiveimg(@Query("uid") int uid);
}
