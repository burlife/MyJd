package com.example.mjd.imitate_jd.model;


import com.example.mjd.imitate_jd.baseUrl.Base;
import com.example.mjd.imitate_jd.net.FiveAPI;
import com.example.mjd.imitate_jd.utils.RetrofitManager;

import okhttp3.OkHttpClient;



public class FiveModel implements IFiveModel {
    @Override
    public FiveAPI setFivetoudata() {
        OkHttpClient client=new OkHttpClient.Builder()
//                .addNetworkInterceptor(new MyInterceptor())
                .build();
        FiveAPI setcreate = RetrofitManager.getinstantce(Base.BASETOUURL, client).setcreate(FiveAPI.class);
        return setcreate;
    }
}
