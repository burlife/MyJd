package com.example.mjd.imitate_jd.net;

import com.example.mjd.imitate_jd.bean.ShopBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchApi {
    @GET("searchProducts?page=1")
    Call<ShopBean> getCall(@Query("keywords") String name);
}
