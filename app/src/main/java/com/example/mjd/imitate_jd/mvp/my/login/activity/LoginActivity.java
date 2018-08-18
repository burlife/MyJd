package com.example.mjd.imitate_jd.mvp.my.login.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.mjd.imitate_jd.MainActivity;
import com.example.mjd.imitate_jd.R;
import com.example.mjd.imitate_jd.activity.BaseActivity;
import com.example.mjd.imitate_jd.mvp.my.regir.activity.RegActivity;
import com.example.mjd.imitate_jd.mvp.my.login.bean.Loginbean;
import com.example.mjd.imitate_jd.mvp.my.login.presenter.LoginPresenter;
import com.example.mjd.imitate_jd.mvp.my.login.view.ILoginView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView,View.OnClickListener {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @BindView(R.id.login_username)
    EditText loginUsername;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.login_reg)
    TextView loginReg;

     ImageView eye;
     private boolean isHideFirst=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        eye=findViewById(R.id.pass_eye);
        eye.setOnClickListener(this);
        sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        boolean islogin = sharedPreferences.getBoolean("is_login", false);
       if(islogin){
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }

    }

    @Override
    public void createPresenter() {
        presenter=new LoginPresenter(this,this);
    }

    private void initData() {

        loginReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegActivity.class);
                startActivity(intent);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.showlogin();
            }
        });
    }

    @Override
    public String getname() {
        return loginUsername.getText().toString();
    }

    @Override
    public String getpass() {
        return loginPassword.getText().toString();
    }

    @Override
    public void getData(Loginbean bean) {
        if(bean.getCode().equals("0")){
            presenter.showTouXinag();
            editor.putBoolean("is_login",true);
            editor.commit();
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
          switch (v.getId()){
              case R.id.pass_eye:
                  if (isHideFirst == true) {
                      eye.setImageResource(R.drawable.open);
                      //密文
                      HideReturnsTransformationMethod method1 = HideReturnsTransformationMethod.getInstance();
                      loginPassword.setTransformationMethod(method1);
                      isHideFirst = false;
                  } else {
                      eye.setImageResource(R.drawable.eye);
                      //密文
                      TransformationMethod method = PasswordTransformationMethod.getInstance();
                      loginPassword.setTransformationMethod(method);
                      isHideFirst = true;

                  }
                  int index =  loginPassword.getText().toString().length();
                  loginPassword.setSelection(index);
                  break;
          }



    }
}
