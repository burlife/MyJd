package com.example.mjd.imitate_jd.goods.model;

/**
 * Created by lenovo on 2017/12/13.
 */


import com.example.mjd.imitate_jd.bean.ShopBean;

import rx.Observer;

public  interface Imodel {
    //将观察者传（bean）进去
    public void shuju(Observer<ShopBean> observer, String name);
}