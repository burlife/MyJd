package com.example.mjd.imitate_jd.mvp.classify.view;

import com.example.mjd.imitate_jd.mvp.classify.bean.Yean;
import com.example.mjd.imitate_jd.mvp.classify.bean.Zean;

public interface IFenView {
    void onZuo(Zean zean);//左侧请求成功
    void onYou(Yean yean);//右侧请求成功
}
