package com.example.mjd.imitate_jd.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mjd.imitate_jd.R;
import com.example.mjd.imitate_jd.activity.BaseFragment;
import com.example.mjd.imitate_jd.mvp.my.login.activity.LoginActivity;
import com.example.mjd.imitate_jd.bean.Five_toubean;
import com.example.mjd.imitate_jd.presenter.FivePresenter;
import com.example.mjd.imitate_jd.view.IFiveView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;


public class MyFragment extends BaseFragment<FivePresenter> implements IFiveView {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @BindView(R.id.five_imageView)
    SimpleDraweeView imageView;
    Unbinder unbinder;
    TextView fiveBtnDl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = View.inflate(getActivity(), R.layout.my_fragment, null);
        unbinder = ButterKnife.bind(this, view);
         fiveBtnDl=view.findViewById(R.id.five_btn_dl);
        initData();
        return view;
    }


    @Override
    public void createPresenter() {
        presenter=new FivePresenter(this);
    }

    private void initData() {
        sharedPreferences=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        final boolean islogin = sharedPreferences.getBoolean("is_login", false);
        fiveBtnDl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(islogin){
                    presenter.showTouimg();
                    Toast.makeText(getActivity(), "已经登录过了", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent=new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                    return;
                }

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getImageData(Five_toubean five_toubean) {
        fiveBtnDl.setText("用户名："+five_toubean.getData().getUsername());
       Uri uri=Uri.parse((String) five_toubean.getData().getIcon());
       DraweeController controller= Fresco.newDraweeControllerBuilder()
               .setUri(uri)
                .build();
       imageView.setController(controller);
    }
}
