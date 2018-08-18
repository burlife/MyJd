package com.example.mjd.imitate_jd.view;

import com.example.mjd.imitate_jd.bean.ShopBean2;

import java.util.List;


/**
 * Created by 杨群 on 2017/12/19.
 */

public interface ShopActivity {
    public void showList(List<ShopBean2.DataBean> groupList, List<List<ShopBean2.DataBean.ListBean>> childList);

}
