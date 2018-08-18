package com.example.mjd.imitate_jd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mjd.imitate_jd.R;
import com.example.mjd.imitate_jd.bean.Sy_bean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

//首页的适配器
public class SYAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<Sy_bean.GoodsListBean> sylist;
    OnItemClickListener listener;
    public SYAdapter(Context context, List<Sy_bean.GoodsListBean> sylist) {
        this.context = context;
        this.sylist = sylist;
    }



    public interface OnItemClickListener{
        public void OnItemClick(View view, int position, String name, String pic, String price);
    }

    public void setOnItemClick(OnItemClickListener listener){
        this.listener=listener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.sy_item,null);
        return new SYHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((SYHolder)holder).sdv.setImageURI(sylist.get(position).getImage_url());
        ((SYHolder)holder).sy_tv.setText(sylist.get(position).getGoods_name());
        ((SYHolder)holder).sy_price.setText("￥"+sylist.get(position).getGroup().getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.OnItemClick(view,position,((SYHolder)holder).sy_tv.getText().toString(),sylist.get(position).getImage_url(),((SYHolder)holder).sy_price.getText().toString());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return sylist!=null?sylist.size():0;
    }
    class SYHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView sdv;
        TextView sy_tv;
        TextView sy_price;

        public SYHolder(View itemView) {
            super(itemView);
            sdv=itemView.findViewById(R.id.sdv);
            sy_tv=itemView.findViewById(R.id.sy_tv);
            sy_price=itemView.findViewById(R.id.sy_price);
        }
    }

}
