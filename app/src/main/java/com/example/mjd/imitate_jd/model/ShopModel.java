package com.example.mjd.imitate_jd.model;

import android.os.Handler;
import android.os.Looper;

import com.example.mjd.imitate_jd.bean.ShopBean2;
import com.example.mjd.imitate_jd.utils.Api;
import com.example.mjd.imitate_jd.utils.HttpUtils;
import com.example.mjd.imitate_jd.utils.OnNetListenter;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 杨群 on 2017/12/19.
 */

public class ShopModel implements IShopModel {
    private Handler handler=new Handler(Looper.getMainLooper());
    @Override
    public void getShop(final OnNetListenter<ShopBean2> onNetListenter) {
        HttpUtils.getHttpUtils().doGet(Api.url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onNetListenter.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string=response.body().string();
                final ShopBean2 shopBeans=new Gson().fromJson(string,ShopBean2.class);
            handler.post(new Runnable() {
                @Override
                public void run() {
                  onNetListenter.onSueecss(shopBeans);
                }
            });
            }
        });
    }
}
