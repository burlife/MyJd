package com.example.mjd.imitate_jd.mvp.car.model;

import com.example.mjd.imitate_jd.mvp.car.RetrofitUtils;
import com.example.mjd.imitate_jd.mvp.car.bean.DatasBean;
import com.example.mjd.imitate_jd.mvp.car.bean.MessageBean;
import com.example.mjd.imitate_jd.mvp.car.presenter.NewsPresenter;

import java.util.List;

import io.reactivex.Flowable;

public class NewsModel implements IModel{
    private NewsPresenter presenter;

    @Override
    public void getData(String uid, String pid) {
        Flowable<MessageBean<List<DatasBean>>> flowable = RetrofitUtils.getInstance().getApiService().getDatas(uid);
        presenter.getNews(flowable);
    }

    public NewsModel(NewsPresenter presenter){
        this.presenter = (NewsPresenter) presenter;

    }

}
