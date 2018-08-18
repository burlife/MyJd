package com.example.mjd.imitate_jd.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.mjd.imitate_jd.presenter.IPresenter;


public abstract class BaseActivity<T extends IPresenter> extends AppCompatActivity {
   public T presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter();
    }
    public abstract void createPresenter();
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dettch();
    }
}
