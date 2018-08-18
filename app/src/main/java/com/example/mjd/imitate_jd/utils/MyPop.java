package com.example.mjd.imitate_jd.utils;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mjd.imitate_jd.R;
import com.example.mjd.imitate_jd.bean.Xiangbean;
import com.example.mjd.imitate_jd.baseUrl.Base;
import com.example.mjd.imitate_jd.net.CarApi;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPop {
    Context context;
    @BindView(R.id.pop_img)
    SimpleDraweeView popImg;
    @BindView(R.id.pop_title)
    TextView popTitle;
    @BindView(R.id.pop_textView2)
    TextView popTextView2;
    @BindView(R.id.pop_send)
    Button popSend;
    @BindView(R.id.pop_jian)
    Button popJian;
    @BindView(R.id.pop_count)
    TextView popCount;
    @BindView(R.id.pop_jia)
    Button popJia;
    Xiangbean bean;
    public MyPop(Context context,Xiangbean ba) {
        this.bean=ba;
        this.context = context;
    }

    public void showpop() {
        View view = View.inflate(context, R.layout.activity_xiangqing_popwindows, null);
        ButterKnife.bind(this,view);
        PopupWindow pp=new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,500,true);
        pp.setOutsideTouchable(true);
        pp.setBackgroundDrawable(new BitmapDrawable());
        pp.setAnimationStyle(R.style.mypopwindow_anim_style);
        pp.showAtLocation(view, Gravity.BOTTOM,0,0);
        String images = bean.getData().getImages();
        String[] split = images.split("\\|");
        DraweeController con = Fresco.newDraweeControllerBuilder()
                .setUri(split[0])
                .build();
        popImg.setController(con);
        popTitle.setText(bean.getData().getTitle());
        popSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client=new OkHttpClient.Builder().build();
                CarApi setcreate = RetrofitManager.getinstantce(Base.BASETOUURL, client).setcreate(CarApi.class);
                HashMap<String,Object> map=new HashMap<String, Object>();
                map.put("uid","672");
                map.put("pid",bean.getData().getPid());
                map.put("token","4E2388C1DB8C507D9320BCE7D54E62EE");
                setcreate.getaddCart(map).enqueue(new Callback<Object>() {

                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        Toast.makeText(context, "添加购物车成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        Log.i("--addcart---", "onFailure: "+t.getMessage());
                    }
                });
            }
        });
    }
}
