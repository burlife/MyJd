package com.example.mjd.imitate_jd.view;

import com.example.mjd.imitate_jd.bean.Pj_Bean;

import java.util.List;


//评价View层接口
public interface IPJDataView {
    public void pjShowData(List<Pj_Bean.DataBean> pjlist);
    public String getId();
}
