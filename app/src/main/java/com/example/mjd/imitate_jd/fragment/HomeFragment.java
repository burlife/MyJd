package com.example.mjd.imitate_jd.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.mjd.imitate_jd.R;
import com.example.mjd.imitate_jd.activity.BaseFragment;
import com.example.mjd.imitate_jd.activity.SaoActivity;
import com.example.mjd.imitate_jd.activity.SousuoActivity;
import com.example.mjd.imitate_jd.activity.XiangqingActivity;
import com.example.mjd.imitate_jd.mvp.classify.bean.Zean;
import com.example.mjd.imitate_jd.mvp.home.adapter.HomMsAdapter;
import com.example.mjd.imitate_jd.mvp.home.adapter.HomeTjAdapter;
import com.example.mjd.imitate_jd.mvp.home.adapter.MyHomeVpAdapter;
import com.example.mjd.imitate_jd.mvp.home.adapter.MyOneGvAdapter1;
import com.example.mjd.imitate_jd.mvp.home.adapter.MyOneGvAdapter2;
import com.example.mjd.imitate_jd.mvp.home.bean.HomeBean;
import com.example.mjd.imitate_jd.bean.Xiangbean;
import com.example.mjd.imitate_jd.mvp.home.presenter.HomePresenter;
import com.example.mjd.imitate_jd.utils.Imagebanner;
import com.example.mjd.imitate_jd.mvp.home.view.IHomeView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.youth.banner.Banner;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView {
    @BindView(R.id.one_bn)
    Banner oneBn;
    @BindView(R.id.one_sdv)
    SimpleDraweeView oneSdv;
    @BindView(R.id.one_vp)
    ViewPager onevp;
    TextView oneTvSecond;
    @BindView(R.id.one_xsqg)
    LinearLayout oneXsqg;
    @BindView(R.id.one_ms_recy)
    RecyclerView oneMsRecy;
    @BindView(R.id.one_tj_recy)
    RecyclerView oneTjRecy;
    Unbinder unbinder;
    List<GridView> listgv;
    GridView gv1;
    GridView gv2;
    MyHomeVpAdapter vpadapter;
    HomMsAdapter msAdapter;
    HomeTjAdapter tjAdapter;
    @BindView(R.id.one_vf)
    ViewFlipper oneVf;
    List<HomeBean.MiaoshaBean.ListBeanX> listms;
    List<HomeBean.TuijianBean.ListBean> listtj;
  EditText editText;
    private TextView miaosha_time;
    private TextView miaosha_shi;
    private TextView miaosha_minter;
    private TextView miaosha_second;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setTime();
            sendEmptyMessageDelayed(0, 1000);
        }
    };

    private void setTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String format = df.format(curDate);
        StringBuffer buffer = new StringBuffer();
        String substring = format.substring(0, 11);
        buffer.append(substring);
        Log.d("ccc", substring);
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour % 2 == 0) {
            miaosha_time.setText(hour + "点场");
            buffer.append((hour + 2));
            buffer.append(":00:00");
        } else {
            miaosha_time.setText((hour - 1) + "点场");
            buffer.append((hour + 1));
            buffer.append(":00:00");
        }
        String totime = buffer.toString();
        try {
            java.util.Date date = df.parse(totime);
            java.util.Date date1 = df.parse(format);
            long defferenttime = date.getTime() - date1.getTime();
            long days = defferenttime / (1000 * 60 * 60 * 24);
            long hours = (defferenttime - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minute = (defferenttime - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            long seconds = defferenttime % 60000;
            long second = Math.round((float) seconds / 1000);
            miaosha_shi.setText("0" + hours + "");
            if (minute >= 10) {
                miaosha_minter.setText(minute + "");
            } else {
                miaosha_minter.setText("0" + minute + "");
            }
            if (second >= 10) {
                miaosha_second.setText(second + "");
            } else {
                miaosha_second.setText("0" + second + "");
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = View.inflate(getActivity(), R.layout.home_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        ImageView sao=view.findViewById(R.id.sy_sao);
        sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IntentIntegrator(getActivity())
                        .setCaptureActivity(SaoActivity.class)
                        .initiateScan();//初始化扫描
            }
        });
        initData();
        initgif();
        initVP();
        //实现跑马灯
        initVF();

        initClick();
        miaosha_time = (TextView) view.findViewById(R.id.tv_miaosha_time);
        miaosha_shi = (TextView) view.findViewById(R.id.tv_miaosha_shi);
        miaosha_minter = (TextView) view.findViewById(R.id.tv_miaosha_minter);
        miaosha_second = (TextView) view.findViewById(R.id.tv_miaosha_second);
        handler.sendEmptyMessage(0);
        return view;
    }

    private void initClick() {

    }
     //实现跑马灯
    private void initVF() {
        oneVf.addView(View.inflate(getActivity(), R.layout.fragment_one_vf_item, null));
        oneVf.addView(View.inflate(getActivity(), R.layout.fragment_one_vf_item1, null));
        oneVf.addView(View.inflate(getActivity(), R.layout.fragment_one_vf_item2, null));

    }
   //显示京东超市的viewpager的页面
    private void initVP() {
        listgv = new ArrayList<>();
        gv1 = new GridView(getActivity());
        gv1.setNumColumns(5);
        gv2 = new GridView(getActivity());
        gv2.setNumColumns(5);
        listgv.add(gv1);
        listgv.add(gv2);
        vpadapter = new MyHomeVpAdapter(listgv, getActivity());
        onevp.setAdapter(vpadapter);
        onevp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        presenter.showoneGV();
    }

    private void initgif() {

        DraweeController con = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse("res://" + getActivity().getPackageName() + "/" + R.mipmap.gif))
                .setAutoPlayAnimations(true)
                .build();
        oneSdv.setController(con);
    }

    private void initData() {
        listms = new ArrayList<>();
        listtj = new ArrayList<>();
        presenter.showoneBanner();
        presenter.showoneGV();

    }

    @Override
    public void createPresenter() {


        presenter = new HomePresenter(this, getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setonebean(List<HomeBean.DataBean> list) {
        List<String> array = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            array.add(list.get(i).getIcon());
        }
        oneBn.setImageLoader(new Imagebanner());
        oneBn.setImages(array);
        oneBn.start();
    }

    @Override
    public void setonemsbean(List<HomeBean.MiaoshaBean.ListBeanX> list) {
        listms = list;
        LinearLayoutManager mgr = new LinearLayoutManager(getActivity());
        mgr.setOrientation(LinearLayoutManager.HORIZONTAL);
        oneMsRecy.setLayoutManager(mgr);
        msAdapter = new HomMsAdapter(list, getActivity());
        oneMsRecy.setAdapter(msAdapter);
        msAdapter.setOnMsItemClickListener(new HomMsAdapter.OnMsItemClickListener() {
            @Override
            public void OnMsItemClick(int position) {
                Intent intent = new Intent(getActivity(), XiangqingActivity.class);
                intent.putExtra("id", listms.get(position).getPid());
                startActivity(intent);
            }
        });

    }

    @Override
    public void setonetjbean(List<HomeBean.TuijianBean.ListBean> list) {
        listtj = list;
        GridLayoutManager mgr = new GridLayoutManager(getActivity(), 2);
        oneTjRecy.setLayoutManager(mgr);
        tjAdapter = new HomeTjAdapter(list, getActivity());
        oneTjRecy.setAdapter(tjAdapter);
        tjAdapter.setOnTjItemClickListener(new HomeTjAdapter.OnTjItemClickListener() {
            @Override
            public void OnTJItemClick(int position) {
                Intent intent = new Intent(getActivity(), XiangqingActivity.class);
                intent.putExtra("id", listtj.get(position).getPid());
                startActivity(intent);
            }
        });
    }
   //获取首页
    @Override
    public void getVpData(List<Zean.DataBean> bean) {
        MyOneGvAdapter1 adapter1 = new MyOneGvAdapter1(bean, getActivity());
        gv1.setAdapter(adapter1);
        MyOneGvAdapter2 adapter2 = new MyOneGvAdapter2(bean, getActivity());
        gv2.setAdapter(adapter2);
    }


    @Override
    public void getxiangData(Xiangbean bean) {

    }

    @OnClick(R.id.sy_edname)
    public void onClick() {
        Intent intent = new Intent(getActivity(), SousuoActivity.class);
        startActivity(intent);
    }
}
