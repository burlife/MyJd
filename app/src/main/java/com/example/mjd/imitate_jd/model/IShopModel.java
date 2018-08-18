package com.example.mjd.imitate_jd.model;

import com.example.mjd.imitate_jd.bean.ShopBean2;
import com.example.mjd.imitate_jd.utils.OnNetListenter;


/**
 * Created by 杨群 on 2017/12/19.
 */

public interface IShopModel {
public void getShop(OnNetListenter<ShopBean2> onNetListenter);
}
