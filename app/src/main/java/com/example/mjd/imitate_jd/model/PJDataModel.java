package com.example.mjd.imitate_jd.model;

import com.example.mjd.imitate_jd.utils.PJUrl;
import com.example.mjd.imitate_jd.utils.RetrofitManager;

import okhttp3.OkHttpClient;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 杨群 on 2017/12/18.
 */

public class PJDataModel implements IPJDataModel {
    @Override
    public void getPJData(String id, Observer observer) {
        OkHttpClient client=new OkHttpClient.Builder().build();
        RetrofitManager.getinstantce("http://apiv4.yangkeduo.com/",client)
                .setcreate(PJUrl.class)
                .getPj(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);

    }
}
