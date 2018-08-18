package com.example.mjd.imitate_jd.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mjd.imitate_jd.R;
import com.example.mjd.imitate_jd.bean.Xiangbean;
import com.example.mjd.imitate_jd.fragment.CarFragment;
import com.example.mjd.imitate_jd.mvp.classify.bean.Zean;
import com.example.mjd.imitate_jd.mvp.home.api.HomeApi;
import com.example.mjd.imitate_jd.mvp.home.bean.HomeBean;
import com.example.mjd.imitate_jd.mvp.home.presenter.HomePresenter;
import com.example.mjd.imitate_jd.mvp.home.view.IHomeView;
import com.example.mjd.imitate_jd.utils.MyPop;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XiangqingActivity extends BaseActivity<HomePresenter> implements IHomeView {
    int id;
    @BindView(R.id.one_xiang_sdv)
    SimpleDraweeView oneXiangSdv;
    @BindView(R.id.one_xiang_tv)
    TextView oneXiangTv;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.one_xiang_tv_subhead)
    TextView oneXiangTvSubhead;
    @BindView(R.id.one_xiang_tv_time)
    TextView oneXiangTvTime;
    @BindView(R.id.one_xiang_tv_price)
    TextView oneXiangTvPrice;
    @BindView(R.id.one_xiang_ivkf)
    TextView one_xiang_ivkf;
    @BindView(R.id.one_xiang_ivguan)
   TextView oneXiangIvguan;
    @BindView(R.id.one_xiang_btnli)
    Button oneXiangBtnli;
    @BindView(R.id.one_xiang_btnadd)
    Button oneXiangBtnadd;
    Xiangbean ba;
    MyPop pop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        Log.i("---id-------", "onCreate: " + id);
        initData();
        initClick();
    }

    private void initClick() {
        oneXiangBtnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop=new MyPop(XiangqingActivity.this,ba);
                pop.showpop();
            }
        });
        oneXiangBtnli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop=new MyPop(XiangqingActivity.this,ba);
                pop.showpop();
            }
        });
    }

    private void initData() {
        presenter.showoneXiang(id);
    }

    @Override
    public void createPresenter() {

        presenter = new HomePresenter(this, this);
    }

    @Override
    public void setonebean(List<HomeBean.DataBean> list) {

    }

    @Override
    public void setonemsbean(List<HomeBean.MiaoshaBean.ListBeanX> list) {

    }

    @Override
    public void setonetjbean(List<HomeBean.TuijianBean.ListBean> list) {

    }

    @Override
    public void getVpData(List<Zean.DataBean> bean) {

    }

    @Override
    public void getxiangData(Xiangbean bean) {
        ba=bean;
        String images = bean.getData().getImages();
        String[] split = images.split("\\|");
        DraweeController con = Fresco.newDraweeControllerBuilder()
                .setUri(split[0])
                .build();
        oneXiangSdv.setController(con);
        tvName.setText(bean.getData().getTitle());
        oneXiangTvSubhead.setText(bean.getData().getSubhead());
        oneXiangTvTime.setText(bean.getData().getCreatetime());
        oneXiangTvPrice.setText("￥" + bean.getData().getPrice());
        one_xiang_ivkf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Intent.ACTION_VIEW);
                in.setData(Uri.parse("tel:15083406429"));
                startActivity(in);
            }
        });
        oneXiangIvguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XiangqingActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
