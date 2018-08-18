package com.example.mjd.imitate_jd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.mjd.imitate_jd.R;
import com.example.mjd.imitate_jd.adapter.SYAdapter;
import com.example.mjd.imitate_jd.bean.Sy_bean;
import com.example.mjd.imitate_jd.presenter.SYPresenter;
import com.example.mjd.imitate_jd.view.ISYDataView;

import java.util.ArrayList;
import java.util.List;

public class FenLeiChildActivity extends AppCompatActivity implements ISYDataView {
    SYAdapter syAdapter;
    private RecyclerView mRv;
    List<Sy_bean.GoodsListBean> sylist=new ArrayList<>();

    SYPresenter syPresenter;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fen_lei_child);
        initView();
        syPresenter=new SYPresenter(this);
        showSYData(sylist);
        syPresenter.spData();
    }

    @Override
    public void showSYData(final List<Sy_bean.GoodsListBean> sylist) {
        Log.d("TAG1", "showSYData: "+sylist);
        GridLayoutManager manager=new GridLayoutManager(this,2);
        mRv.setLayoutManager(manager);
        syAdapter=new SYAdapter(this,sylist);
        mRv.setAdapter(syAdapter);
        syAdapter.setOnItemClick(new SYAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, String name, String pic, String price) {
                id=sylist.get(position).getGoods_id()+"";
                Intent intent = new Intent(FenLeiChildActivity.this,DetailsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                intent.putExtra("pic",pic);
                intent.putExtra("price",price);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv);
    }
}
