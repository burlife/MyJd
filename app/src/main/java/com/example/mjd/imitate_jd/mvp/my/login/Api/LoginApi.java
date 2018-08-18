package com.example.mjd.imitate_jd.mvp.my.login.Api;

import com.example.mjd.imitate_jd.mvp.my.login.bean.Loginbean;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.adapter.rxjava.Result;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;


public interface LoginApi {
    @POST("login")
    @FormUrlEncoded
    Observable<Loginbean> getlogin(@FieldMap HashMap<String, Object> map);
    @Multipart
    @POST("file/upload")
    Call<Result<String>> loadTou(@Part List<MultipartBody.Part> partList);
}
