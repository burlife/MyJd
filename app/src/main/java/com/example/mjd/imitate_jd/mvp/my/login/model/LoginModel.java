package com.example.mjd.imitate_jd.mvp.my.login.model;

import com.example.mjd.imitate_jd.baseUrl.Base;
import com.example.mjd.imitate_jd.mvp.my.login.Api.LoginApi;
import com.example.mjd.imitate_jd.utils.RetrofitManager;

import okhttp3.OkHttpClient;
public class LoginModel implements ILoginModel {
    @Override
    public LoginApi setloginData() {
        OkHttpClient client=new OkHttpClient.Builder().build();
        LoginApi setcreate = RetrofitManager.getinstantce(Base.BASELOGINANDREGURL, client).setcreate(LoginApi.class);

        return setcreate;
    }

    @Override
    public LoginApi setloginTouData() {
        OkHttpClient client=new OkHttpClient.Builder().build();
        LoginApi setcreate = RetrofitManager.getinstantce(Base.BASETOUURL, client).setcreate(LoginApi.class);

        return setcreate;
    }

}
