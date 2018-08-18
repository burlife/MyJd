package com.example.mjd.imitate_jd.model;

import rx.Observer;



public interface IBNDataModel {
    //详情页
    public void getBNData(String good_id, Observer observer);

}
