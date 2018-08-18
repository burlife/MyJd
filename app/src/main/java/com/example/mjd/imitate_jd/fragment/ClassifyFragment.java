package com.example.mjd.imitate_jd.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.mjd.imitate_jd.GlideImagerLoder;
import com.example.mjd.imitate_jd.R;
import com.example.mjd.imitate_jd.mvp.classify.adapter.ErAdapter;
import com.example.mjd.imitate_jd.mvp.classify.adapter.ZuoAdapter;
import com.example.mjd.imitate_jd.mvp.classify.bean.Yean;
import com.example.mjd.imitate_jd.mvp.classify.bean.Zean;
import com.example.mjd.imitate_jd.mvp.classify.presenter.FenPresenter;
import com.example.mjd.imitate_jd.mvp.classify.view.IFenView;
import com.youth.banner.Banner;

import java.util.Arrays;


public class ClassifyFragment extends Fragment implements IFenView {
    public ExpandableListView yev;
    public RecyclerView zrv;
    public FenPresenter presenter;
    private View view;
    String[] image={"https://www.zhaoapi.cn/images/quarter/ad1.png",
                        "https://www.zhaoapi.cn/images/quarter/ad2.png",
            "https://www.zhaoapi.cn/images/quarter/ad3.png",
            "https://www.zhaoapi.cn/images/quarter/ad4.png"
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.classify_fragment,container,false);
        //拿到P层
        presenter = new FenPresenter(this);
        presenter.ShowPer();
        //默认展示第一条
        presenter.FlShowYou(1);
        //找控件
        zrv = view.findViewById(R.id.zrv);
        yev = view.findViewById(R.id.yrv);
        /*Banner banner=view.findViewById(R.id.c_banner);
        banner.setImageLoader(new GlideImagerLoder());
        banner.setImages(Arrays.asList(image));
        banner.start();
        */
        return view;
    }

    @Override
    public void onZuo(final Zean zean) {
        zrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //拿到适配器
        ZuoAdapter adapter= new ZuoAdapter(getActivity(),zean);
        zrv.setAdapter(adapter);
        //调用点击事件
        adapter.setOnItemClickListener(new ZuoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int id = zean.getData().get(position).getCid();
                presenter.FlShowYou(id);
            }
        });
    }

    @Override
    public void onYou(Yean yean) {
        ErAdapter adapter = new ErAdapter(getActivity(),yean);
        yev.setAdapter(adapter);
        //父级列表默认全部展开
        int groupCount = yev.getCount();
        for (int i=0; i<groupCount; i++)
        {
            yev.expandGroup(i);
        }


    }
}
