package com.example.mjd.imitate_jd.presenter;

import android.util.Log;

import com.example.mjd.imitate_jd.bean.Sy_bean;
import com.example.mjd.imitate_jd.model.SYDataModel;
import com.example.mjd.imitate_jd.view.ISYDataView;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;

//首页
public class SYPresenter {
    SYDataModel model;
    ISYDataView view;
    List<Sy_bean.GoodsListBean> sylist=new ArrayList<>();

    public SYPresenter(ISYDataView view) {
        this.view = view;
        model=new SYDataModel();
    }
    public void spData(){
        model.getSYData(new Observer<Sy_bean>(){

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                Log.i("111111p","onError");
            }

            @Override
            public void onNext(Sy_bean sy_bean) {
                Log.d("TAG3", "onNext: "+sy_bean.getGoods_list());
                sylist.addAll(sy_bean.getGoods_list());
                view.showSYData(sylist);
            }
        });
    }
}
