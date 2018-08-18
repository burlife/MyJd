package com.example.mjd.imitate_jd.view;

import com.example.mjd.imitate_jd.bean.Bn_Bean;

import java.util.List;


//详情View层接口
public interface IBNDataView {
    public void bnShowData(List<Bn_Bean.SkuBean> bnlist);
    public String getBnId();
}
