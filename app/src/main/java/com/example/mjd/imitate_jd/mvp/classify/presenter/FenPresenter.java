package com.example.mjd.imitate_jd.mvp.classify.presenter;


import com.example.mjd.imitate_jd.mvp.classify.bean.Yean;
import com.example.mjd.imitate_jd.mvp.classify.bean.Zean;
import com.example.mjd.imitate_jd.mvp.classify.model.FenModel;
import com.example.mjd.imitate_jd.mvp.classify.view.IFenView;

public class FenPresenter implements FenModel.ScuMod{

    IFenView view;
    FenModel model;

    public FenPresenter(IFenView view) {
        this.view = view;
        model = new FenModel();
        model.setScuMod(this);
    }

    @Override
    public void Zc(Zean bean) {
        view.onZuo(bean);
    }

    @Override
    public void Yc(Yean yean) {
        view.onYou(yean);
    }

    //左侧调用
    public void  ShowPer(){
        model.ZuoChen();
    }
    // 右侧调用
    public void FlShowYou(int cont){
        model.YuoChen(cont);
    }
}
