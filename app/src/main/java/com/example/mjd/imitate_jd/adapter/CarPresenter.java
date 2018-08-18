package com.example.mjd.imitate_jd.adapter;

import android.support.v4.app.FragmentActivity;
import android.util.Log;


import com.example.mjd.imitate_jd.bean.CarBean;
import com.example.mjd.imitate_jd.presenter.IPresenter;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class CarPresenter implements IPresenter<IcarView> {
    List<CarBean.DataBean.ListBean> list=new ArrayList<>();
    SoftReference<IcarView> reference;
    ICar model;
    public CarPresenter(IcarView view, FragmentActivity activity){
        attch(view);
        model=new CarModel();
    }

    public void showQuerycarts(){
        int uid=672;
        model.setquerycartsdata()
                .getqueryCarts(uid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CarBean>() {
              @Override
              public void onCompleted() {

              }

              @Override
              public void onError(Throwable throwable) {
                  Log.i("-----query-----", "onError: "+throwable.getMessage());
              }

              @Override
              public void onNext(CarBean carBean) {
                  for (int i = 0; i < carBean.getData().size(); i++) {
                      for (int j = 0; j <carBean.getData().get(i).getList().size() ; j++) {
                          list.add(carBean.getData().get(i).getList().get(j));
                      }

                  }
                  Log.i("-----query-----", "onError: "+list.size());

                  reference.get().getquerycartsData(list);
              }
          });
    }

    @Override
    public void attch(IcarView view) {
        reference=new SoftReference<IcarView>(view);
    }

    @Override
    public void dettch() {

        reference.clear();
    }
}
