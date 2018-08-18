package com.example.mjd.imitate_jd.mvp.my.regir.model;

import com.example.mjd.imitate_jd.baseUrl.Base;
import com.example.mjd.imitate_jd.mvp.my.regir.api.RegApi;
import com.example.mjd.imitate_jd.utils.RetrofitManager;

import okhttp3.OkHttpClient;


public class RegModel implements IRegModel {
    @Override
    public RegApi setRegData() {
        OkHttpClient client=new OkHttpClient.Builder().build();
        RegApi setcreate = RetrofitManager.getinstantce(Base.BASELOGINANDREGURL, client).setcreate(RegApi.class);
        return setcreate;
    }
}
