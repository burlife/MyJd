package com.example.mjd.imitate_jd.adapter;

import com.example.mjd.imitate_jd.baseUrl.Base;
import com.example.mjd.imitate_jd.net.CarApi;
import com.example.mjd.imitate_jd.utils.MyInterceptor;
import com.example.mjd.imitate_jd.utils.RetrofitManager;
import okhttp3.OkHttpClient;

public class CarModel implements ICar {

    @Override
    public CarApi setquerycartsdata() {
        OkHttpClient client=new OkHttpClient.Builder()
                .addNetworkInterceptor(new MyInterceptor())
                .build();
        CarApi setcreate = RetrofitManager.getinstantce(Base.BASETOUURL, client).setcreate(CarApi.class);
        return setcreate;
    }
}
