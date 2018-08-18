package com.example.mjd.imitate_jd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.mjd.imitate_jd.R;
import com.example.mjd.imitate_jd.adapter.PJXAdapter;
import com.example.mjd.imitate_jd.bean.Pj_Bean;
import com.example.mjd.imitate_jd.presenter.PJPresenter;
import com.example.mjd.imitate_jd.view.IPJDataView;

import java.util.ArrayList;
import java.util.List;

public class PingJiaActivity extends AppCompatActivity implements IPJDataView {
    PJXAdapter pjxAdapter;
    PJPresenter pjPresenter;
    String id;
    List<Pj_Bean.DataBean> pjlist = new ArrayList<>();
    private ListView mPjLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping_jia);
        initView();
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        pjPresenter=new PJPresenter(this);
        pjPresenter.ppData();
    }

    private void initView() {
        mPjLv = (ListView) findViewById(R.id.pj_lv);
    }

    @Override
    public void pjShowData(List<Pj_Bean.DataBean> pjlist) {
        pjxAdapter=new PJXAdapter(this,pjlist);
        mPjLv.setAdapter(pjxAdapter);
    }

    @Override
    public String getId() {
        return id;
    }
}
