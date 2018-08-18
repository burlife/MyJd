package com.example.mjd.imitate_jd.mvp.my.regir.api;
import com.example.mjd.imitate_jd.mvp.my.regir.bean.Regbean;

import java.util.HashMap;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface RegApi {
    @POST("reg")
    @FormUrlEncoded
    Observable<Regbean> getreg(@FieldMap HashMap<String, Object> map);
}
