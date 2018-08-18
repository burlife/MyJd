package com.example.mjd.imitate_jd.model;

import com.example.mjd.imitate_jd.utils.BNUrl;
import com.example.mjd.imitate_jd.utils.RetrofitManager;

import okhttp3.OkHttpClient;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class BNDataModel implements IBNDataModel {

    @Override
    public void getBNData(String good_id, Observer observer) {
        OkHttpClient client=new OkHttpClient.Builder().build();
        RetrofitManager.getinstantce("http://apiv4.yangkeduo.com/",client)
                .setcreate(BNUrl.class)
                .getPj(good_id).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
