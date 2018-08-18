package com.example.mjd.imitate_jd.goods.model;




import com.example.mjd.imitate_jd.bean.ShopBean;
import com.example.mjd.imitate_jd.goods.model.Imodel;
import com.example.mjd.imitate_jd.net.SearchApi;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/12/13.
 */

public class Model implements Imodel {

    private ShopBean bean;
    @Override
    public void shuju(Observer<ShopBean> observer, final String name) {
        //创建被观察者
        Observable.create(new Observable.OnSubscribe<ShopBean>() {
            @Override
            public void call(final Subscriber<? super ShopBean> subscriber) {

                //拦截器的使用
                OkHttpClient oc=new OkHttpClient.Builder().build();

                //Retrofit的请求数据
                Retrofit retrofit=new Retrofit.Builder()
                        //添加拦截器
                        .client(oc)
                        //设置网络请求的url
                        .baseUrl("https://www.zhaoapi.cn/product/")
                        //设置Gson
                        .addConverterFactory(GsonConverterFactory.create())
                        //设置Rxjava
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();

                //创建网络请求接口的实例
                SearchApi Request=retrofit.create(SearchApi.class);
                //对发的请求进行封装
                Call<ShopBean> call=Request.getCall(name);
                //使用异步发送网络请求
                call.enqueue(new Callback<ShopBean>(){
                    //请求成功时回调
                    @Override
                    public void onResponse(Call<ShopBean> call, Response<ShopBean> response) {
                        bean=response.body();
                        //调用OnNext方法把值传给观察者
                        subscriber.onNext(bean);
                        subscriber.onCompleted();
                    }

                    //请求失败时的回调
                    @Override
                    public void onFailure(Call<ShopBean> call, Throwable t) {

                    }
                });

            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }
}
