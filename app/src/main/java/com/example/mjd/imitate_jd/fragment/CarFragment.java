package com.example.mjd.imitate_jd.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mjd.imitate_jd.R;
import com.example.mjd.imitate_jd.activity.BaseFragment;
import com.example.mjd.imitate_jd.mvp.car.MessageEvent;
import com.example.mjd.imitate_jd.mvp.car.PriceAndCountEvent;
import com.example.mjd.imitate_jd.mvp.car.adapter.MyAdapter;
import com.example.mjd.imitate_jd.mvp.car.bean.DatasBean;
import com.example.mjd.imitate_jd.mvp.car.bean.MessageBean;
import com.example.mjd.imitate_jd.mvp.car.bean.SomeId;
import com.example.mjd.imitate_jd.mvp.car.presenter.DelPresenter;
import com.example.mjd.imitate_jd.mvp.car.presenter.NewsPresenter;
import com.example.mjd.imitate_jd.mvp.car.view.Iview;
import com.example.mjd.imitate_jd.presenter.IPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CarFragment extends BaseFragment<IPresenter> implements Iview {
    private String uid = "72";
    private NewsPresenter presenter;
    private CheckBox mCheckbox2;
    private ExpandableListView mElv;

    /**
     * 0
     */
    private TextView mTvPrice;
    /**
     * 结算(0)
     */
    private TextView mTvNum;
    private MyAdapter adapter;
    private List<DatasBean> groupList = new ArrayList<>();
    private List<List<DatasBean.ListBean>> childList = new ArrayList<>();
    private String pid;
    private DelPresenter delPresenter;
   View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.car_fragment, container, false);
       // EventBus.getDefault().register(this);

        presenter = new NewsPresenter();
        presenter.attachView( this);
        delPresenter = new DelPresenter();
        delPresenter.attachView(this);
        initView();
        adapter = new MyAdapter(getActivity(), groupList, childList);
        mElv.setAdapter(adapter);

        mCheckbox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.changeAllListCbState(mCheckbox2.isChecked());
            }
        });
        return view;
    }

    @Override
    public void createPresenter() {

    }


    private void initView() {
        mElv = (ExpandableListView) view.findViewById(R.id.elv);
        mCheckbox2 = (CheckBox) view.findViewById(R.id.checkbox2);
        mTvPrice = (TextView) view.findViewById(R.id.tv_price);
        mTvNum = (TextView) view.findViewById(R.id.tv_num);
    }
    @Override
    public void onSuccess(Object o) {
        if(o!=null){
            List<DatasBean> list = (List<DatasBean> )o;
            if(list!=null){
                groupList.addAll(list);
                for (int i = 0; i < list.size(); i++) {
                    List<DatasBean.ListBean> datas = list.get(i).getList();
                    childList.add(datas);
                }

                adapter.notifyDataSetChanged();
                mCheckbox2.setChecked(true);

                adapter.changeAllListCbState(true);
                mElv.setGroupIndicator(null);
                for (int i=0;i<groupList.size();i++){
                    mElv.expandGroup(i);
                }

            }
        }
    }

    @Override
    public void onFailed(Exception e) {

    }

    @Override
    public void delSuccess(MessageBean listMessageBean) {
        Toast.makeText(getActivity(),listMessageBean.getMsg(),Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.getData(uid,pid);

    }


    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        mCheckbox2.setChecked(event.isChecked());
    }

    @Subscribe
    public void onMessageEvent(PriceAndCountEvent event) {
        mTvNum.setText("结算(" + event.getCount() + ")");
        mTvPrice.setText("￥"+event.getPrice() );
    }
    @Subscribe
    public void onMessageEvent(SomeId event) {
        pid = event.getPid();
        Log.e("查询","pid:"+pid);
        delPresenter.getData(uid,pid);


    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (presenter != null) {
            presenter.detachView();
        }
    }
}



