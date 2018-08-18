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

public class HomMsAdapter extends RecyclerView.Adapter<HomMsAdapter.ViewHolder> {
    OnMsItemClickListener listener;
    List<HomeBean.MiaoshaBean.ListBeanX> list;
    Context context;


    public HomMsAdapter(List<HomeBean.MiaoshaBean.ListBeanX> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public void setOnMsItemClickListener(OnMsItemClickListener listener){
        this.listener=listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.fragment_one_msrecy_item, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnMsItemClick((Integer) v.getTag());
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.oneMsTitle.setText("￥"+list.get(position).getBargainPrice());
        holder.oneMsPrice.setText("￥"+list.get(position).getPrice());
        String images=list.get(position).getImages();
        String[] split=images.split("\\|");
        DraweeController controller= Fresco.newDraweeControllerBuilder()
                .setUri(split[0])
                .build();
        holder.oneMsSim.setController(controller);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.one_ms_sim)
        SimpleDraweeView oneMsSim;
        @BindView(R.id.one_ms_title)
        TextView oneMsTitle;
        @BindView(R.id.one_ms_price)
        TextView oneMsPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public interface OnMsItemClickListener{
        public void OnMsItemClick(int position);
    }
}
