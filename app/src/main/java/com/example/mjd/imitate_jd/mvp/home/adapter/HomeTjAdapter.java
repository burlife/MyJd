package com.example.mjd.imitate_jd.mvp.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.mjd.imitate_jd.R;
import com.example.mjd.imitate_jd.mvp.home.bean.HomeBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeTjAdapter extends RecyclerView.Adapter<HomeTjAdapter.ViewHolder> {
    OnTjItemClickListener listener;
    List<HomeBean.TuijianBean.ListBean> list;
    Context context;



    public HomeTjAdapter(List<HomeBean.TuijianBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public void setOnTjItemClickListener(OnTjItemClickListener listener){
        this.listener=listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.fragment_one_tjrecy_item, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnTJItemClick((Integer) v.getTag());
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.oneTjTitle.setText(list.get(position).getTitle());
        holder.oneTjPrice.setText("￥" + list.get(position).getBargainPrice());
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(split[0])
                .build();
        holder.oneTjSim.setController(controller);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.one_tj_sim)
        SimpleDraweeView oneTjSim;
        @BindView(R.id.one_tj_title)
        TextView oneTjTitle;
        @BindView(R.id.one_tj_price)
        TextView oneTjPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface OnTjItemClickListener{
        public void OnTJItemClick(int position);
    }
}
