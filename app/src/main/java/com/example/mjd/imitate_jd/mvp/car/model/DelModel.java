package com.example.mjd.imitate_jd.mvp.car.model;

import com.example.mjd.imitate_jd.mvp.car.RetrofitUtils;
import com.example.mjd.imitate_jd.mvp.car.bean.MessageBean;
import com.example.mjd.imitate_jd.mvp.car.presenter.DelPresenter;

import io.reactivex.Flowable;

public class DelModel implements IModel{
    private DelPresenter presenter;

    public DelModel(DelPresenter presenter){
        this.presenter =  presenter;

    }
    @Override
    public void getData(String uid, String pid) {

        Flowable<MessageBean> delFlowable = RetrofitUtils.getInstance().getApiService().deleteData(uid,pid);
        presenter.delData(delFlowable);
    }
}
