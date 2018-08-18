package com.example.mjd.imitate_jd.mvp.my.regir.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mjd.imitate_jd.R;
import com.example.mjd.imitate_jd.activity.BaseActivity;
import com.example.mjd.imitate_jd.mvp.my.login.activity.LoginActivity;
import com.example.mjd.imitate_jd.mvp.my.regir.bean.Regbean;
import com.example.mjd.imitate_jd.mvp.my.regir.presenter.RegPresenter;
import com.example.mjd.imitate_jd.mvp.my.regir.view.IRegView;


import butterknife.BindView;
import butterknife.ButterKnife;

public class RegActivity extends BaseActivity<RegPresenter> implements IRegView,View.OnClickListener{


    @BindView(R.id.reg_username)
    EditText regUsername;
    @BindView(R.id.reg_password)
    EditText regPassword;
    @BindView(R.id.reg_btn)
    Button regBtn;
     ImageView  reg_eye;
    private boolean isHideFirst=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regir_layout);
        ButterKnife.bind(this);
        reg_eye=findViewById(R.id.pass_eye);
        reg_eye.setOnClickListener(this);
        initData();

    }

    private void initData() {
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.regData();
            }
        });

    }

    @Override
    public void createPresenter() {

        presenter=new RegPresenter(this);
    }

    @Override
    public String getname() {

        return regUsername.getText().toString();
    }

    @Override
    public String getpass() {
        return regPassword.getText().toString();
    }

    @Override
    public void getData(Regbean bean) {
        if(bean.getCode().equals("0")){
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(RegActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.hide_eye:
    if (isHideFirst == true) {
        reg_eye.setImageResource(R.drawable.open);
        //密文
        HideReturnsTransformationMethod method1 = HideReturnsTransformationMethod.getInstance();
        regPassword.setTransformationMethod(method1);
        isHideFirst = false;
    } else {
        reg_eye.setImageResource(R.drawable.eye);
        //密文
        TransformationMethod method = PasswordTransformationMethod.getInstance();
        regPassword.setTransformationMethod(method);
        isHideFirst = true;

    }
    int index =  regPassword.getText().toString().length();
    regPassword.setSelection(index);
    break;
}



    }
}
