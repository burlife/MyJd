package com.example.mjd.imitate_jd.mvp.home.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.GridView;

import com.example.mjd.imitate_jd.mvp.classify.bean.Zean;
import com.example.mjd.imitate_jd.mvp.home.bean.HomeBean;
import com.example.mjd.imitate_jd.bean.Xiangbean;
import com.example.mjd.imitate_jd.mvp.home.model.HomeModel;
import com.example.mjd.imitate_jd.mvp.home.view.IHomeView;
import com.example.mjd.imitate_jd.presenter.IPresenter;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class HomePresenter implements IPresenter<IHomeView> {
    SoftReference<IHomeView> reference;
    com.example.mjd.imitate_jd.mvp.home.model.IHomeView model;
    Context context;
    List<HomeBean.DataBean> list=new ArrayList<>();
    List<HomeBean.MiaoshaBean.ListBeanX> listms=new ArrayList<>();
    List<HomeBean.TuijianBean.ListBean> listtj=new ArrayList<>();
    List<Zean.DataBean> listvp=new ArrayList<>();
    public HomePresenter(IHomeView view, Context context) {
        this.context=context;
        model=new HomeModel();
        attch(view);
    }
    //轮播图
    public void showoneBanner(){
       model.setOneData()
               .getoneapi()
               .observeOn(AndroidSchedulers.mainThread())
               .subscribeOn(Schedulers.io()).subscribe(new Observer<HomeBean>() {
           @Override
           public void onCompleted() {
           }
           @Override
           public void onError(Throwable throwable) {
           }

           @Override
           public void onNext(HomeBean oneBean) {
               for (int i = 0; i < oneBean.getData().size(); i++) {
                   list.add(oneBean.getData().get(i));
               }
               for (int i = 0; i < oneBean.getMiaosha().getList().size(); i++) {
                   listms.add(oneBean.getMiaosha().getList().get(i));
               }
               for (int i = 0; i < oneBean.getTuijian().getList().size(); i++) {
                   listtj.add(oneBean.getTuijian().getList().get(i));
               }

                reference.get().setonebean(list);
                reference.get().setonemsbean(listms);
                reference.get().setonetjbean(listtj);
           }
       });
       
    }
    //首页的京东超市的viewpager的滑动
    public void showoneGV(){
        model.setOnevpData()
                .getone_vp().
                observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<Zean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                Log.i("---one-onError-------", "onNext: "+throwable.getMessage());
            }

            @Override
            public void onNext(Zean two_lv_leftBean) {
                for (int i = 0; i < two_lv_leftBean.getData().size(); i++) {
                    listvp.add(two_lv_leftBean.getData().get(i));
                }
                Log.i("---one-list-------", "onNext: "+two_lv_leftBean);
                reference.get().getVpData(listvp);
            }
        });
    }
    public void showoneXiang(int id){
        model.setOnevpData().getone_xiang(id).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<Xiangbean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                Log.i("----Xiangbean-------", "onError: "+throwable.getMessage());
            }

            @Override
            public void onNext(Xiangbean xiangbean) {
                Log.i("----Xiangbean-------", "onNext: "+xiangbean);
                reference.get().getxiangData(xiangbean);
            }
        });
    }
    @Override
    public void attch(IHomeView view) {
        reference=new SoftReference<IHomeView>(view);
    }

    @Override
    public void dettch() {
        reference.clear();
    }
}
