package com.example.mjd.imitate_jd.mvp.home.view;


import com.example.mjd.imitate_jd.mvp.classify.bean.Zean;
import com.example.mjd.imitate_jd.mvp.home.bean.HomeBean;
import com.example.mjd.imitate_jd.bean.Xiangbean;

import java.util.List;

public interface IHomeView {
    public void setonebean(List<HomeBean.DataBean> list);
    public void setonemsbean(List<HomeBean.MiaoshaBean.ListBeanX> list);
    public void setonetjbean(List<HomeBean.TuijianBean.ListBean> list);
    public void getVpData(List<Zean.DataBean> bean);
    public void getxiangData(Xiangbean bean);
}
