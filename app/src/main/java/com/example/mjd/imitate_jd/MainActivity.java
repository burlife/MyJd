package com.example.mjd.imitate_jd;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.mjd.imitate_jd.activity.BaseActivity;
import com.example.mjd.imitate_jd.adapter.MyMainVpAdapter;
import com.example.mjd.imitate_jd.presenter.MainVpPrensenter;
import com.example.mjd.imitate_jd.view.IMainVpView;
import com.example.mjd.imitate_jd.view.MyMainVp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainVpPrensenter> implements IMainVpView {


    @BindView(R.id.main_bt1)
    RadioButton mainBt1;
    @BindView(R.id.main_bt2)
    RadioButton mainBt2;
    @BindView(R.id.main_bt3)
    RadioButton mainBt3;
    @BindView(R.id.main_bt4)
    RadioButton mainBt4;
    @BindView(R.id.main_bt5)
    RadioButton mainBt5;
    @BindView(R.id.main_rg)
    RadioGroup mainRg;
    @BindView(R.id.main_vp)
    MyMainVp mainVp;
    MyMainVpAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        ButterKnife.bind(this);
        initData();
    }

    private void initView() {
        mainVp = (MyMainVp) findViewById(R.id.main_vp);
    }

    @Override
    public void createPresenter() {
        presenter = new MainVpPrensenter(this);
    }

    private void initData() {
        presenter.showFragmentVp();
        mainRg.check(R.id.main_bt1);
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.main_bt1:
                        mainVp.setCurrentItem(0);
                        break;
                    case R.id.main_bt2:
                        mainVp.setCurrentItem(1);
                        break;
                    case R.id.main_bt3:
                        mainVp.setCurrentItem(2);
                        break;
                    case R.id.main_bt4:
                        mainVp.setCurrentItem(3);
                        break;
                    case R.id.main_bt5:
                        mainVp.setCurrentItem(4);
                        break;
                }
            }
        });
    }

    @Override
    public void getFragment(List<Fragment> list) {
        adapter = new MyMainVpAdapter(getSupportFragmentManager(), list);
        mainVp.setAdapter(adapter);
    }
}