package com.example.mjd.imitate_jd.mvp.car.view;


import com.example.mjd.imitate_jd.mvp.car.bean.MessageBean;

public interface Iview {
    void onSuccess(Object o);
    void onFailed(Exception e);
    void delSuccess(MessageBean listMessageBean);
}
