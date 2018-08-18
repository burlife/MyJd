package com.example.mjd.imitate_jd.presenter;

import android.util.Log;

import com.example.mjd.imitate_jd.bean.Pj_Bean;
import com.example.mjd.imitate_jd.model.PJDataModel;
import com.example.mjd.imitate_jd.view.IPJDataView;

import java.util.ArrayList;
import java.util.List;
import rx.Observer;

//评价页面
public class PJPresenter {
    PJDataModel model;
    IPJDataView view;
    List<Pj_Bean.DataBean> pjlist=new ArrayList<>();

    public PJPresenter(IPJDataView view) {
        this.view = view;
        model=new PJDataModel();
    }
    public void ppData(){
        String id=view.getId();
        model.getPJData(id,new Observer<Pj_Bean>(){

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("111111p","onError");
            }

            @Override
            public void onNext(Pj_Bean pj_bean) {
                pjlist.addAll(pj_bean.getData());
                view.pjShowData(pjlist);
            }
        });
    }
}
