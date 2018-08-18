package com.example.mjd.imitate_jd.net;


import com.example.mjd.imitate_jd.bean.CarBean;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;



public interface CarApi {
    @POST("product/addCart")
    @FormUrlEncoded
    Call<Object> getaddCart(@FieldMap HashMap<String, Object> map);
    @GET("product/getCarts?")
    Observable<CarBean> getqueryCarts(@Query("uid") int uid);
}
