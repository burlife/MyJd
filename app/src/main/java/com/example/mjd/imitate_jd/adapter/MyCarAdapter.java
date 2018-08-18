package com.example.mjd.imitate_jd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;


import com.example.mjd.imitate_jd.R;
import com.example.mjd.imitate_jd.bean.CarBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCarAdapter extends RecyclerView.Adapter<MyCarAdapter.ViewHolder> {
    OnItemClickListener listener;
    Context context;
     List<CarBean.DataBean.ListBean>list;

    public MyCarAdapter(List<CarBean.DataBean.ListBean> carbean, Context context) {
        this.context = context;
    }

    public MyCarAdapter(List<CarBean.DataBean.ListBean> list) {
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.car_cartsitem, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String split=list.get(position).getImages();
        String[] images=split.split("\\|");
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(images[0])
                .build();
        holder.sdv.setController(controller);
        holder.tvs.setText(list.get(position).getTitle());
        holder.tvs2.setText("￥"+list.get(position).getPrice());
    }

    @Override
    public int getItemCount() {

        return list == null ? 0 : list.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.eckboxnum)
        CheckBox eckboxnum;
        @BindView(R.id.sdv)
        SimpleDraweeView sdv;
        @BindView(R.id.tvs)
        TextView tvs;
        @BindView(R.id.tvs2)
        TextView tvs2;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        public void OnItemClick(int position);
    }
}
