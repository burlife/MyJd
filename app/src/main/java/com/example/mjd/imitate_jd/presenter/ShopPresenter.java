package com.example.mjd.imitate_jd.presenter;

import com.example.mjd.imitate_jd.bean.ShopBean2;
import com.example.mjd.imitate_jd.model.IShopModel;
import com.example.mjd.imitate_jd.model.ShopModel;
import com.example.mjd.imitate_jd.utils.OnNetListenter;
import com.example.mjd.imitate_jd.view.ShopActivity;

import java.util.ArrayList;
import java.util.List;



public class ShopPresenter {
    private IShopModel iShopModel;
    private ShopActivity shopActivity;
    public ShopPresenter(ShopActivity shopActivity){
        iShopModel=new ShopModel();
        this.shopActivity=shopActivity;
    }
    public void getShop(){
        iShopModel.getShop(new OnNetListenter<ShopBean2>() {
            @Override
            public void onSueecss(ShopBean2 shopBeans) {
                List<ShopBean2.DataBean> dataBean = shopBeans.getData();
                List<List<ShopBean2.DataBean.ListBean>> childList=
                        new ArrayList<List<ShopBean2.DataBean.ListBean>>();
                for(int i=0;i<dataBean.size();i++){
                    List<ShopBean2.DataBean.ListBean> listBeen= dataBean.get(i).getList();
                    childList.add(listBeen);
                }
                shopActivity.showList(dataBean,childList);
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });
    }
}
