package com.example.mjd.imitate_jd.mvp.home.model;
import com.example.mjd.imitate_jd.baseUrl.Base;
import com.example.mjd.imitate_jd.mvp.home.api.HomeApi;
import com.example.mjd.imitate_jd.utils.RetrofitManager;

import okhttp3.OkHttpClient;


public class HomeModel implements IHomeView {

    @Override
    public HomeApi setOneData() {
        OkHttpClient client=new OkHttpClient.Builder().build();
        HomeApi setcreate = RetrofitManager.getinstantce(Base.BASETWO_ONERL, client).setcreate(HomeApi.class);
        return setcreate;
    }

    @Override
    public HomeApi setOnevpData() {
        OkHttpClient client=new OkHttpClient.Builder()
//                .addNetworkInterceptor(new MyInterceptor())
                .build();
        HomeApi setcreate = RetrofitManager.getinstantce(Base.BASETWO_LV_LEFTURL, client).setcreate(HomeApi.class);
        return setcreate;
    }



}
