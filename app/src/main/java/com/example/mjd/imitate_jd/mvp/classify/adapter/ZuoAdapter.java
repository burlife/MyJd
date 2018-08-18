package com.example.mjd.imitate_jd.mvp.classify.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mjd.imitate_jd.R;
import com.example.mjd.imitate_jd.mvp.classify.bean.Zean;

import java.util.List;

public class ZuoAdapter extends RecyclerView.Adapter<ZuoAdapter.MyHolder>{
    private Context context;
    private Zean zean;
    private OnItemClickListener mOnItemClickListener;

    public ZuoAdapter(Context context, Zean list) {
        this.context = context;
        this.zean = list;
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder holder = new MyHolder(LayoutInflater.from(context).inflate(R.layout.zuo_litem, parent, false));
        return holder;
    }
    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        holder.zuo_mz.setText(zean.getData().get(position).getName());
        if (mOnItemClickListener != null){
            holder.zuo_mz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView,position); // 2
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return zean.getData().size();
        //list == null ? 0 : list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView zuo_mz;
        public MyHolder(View itemView) {
            super(itemView);
            zuo_mz = itemView.findViewById(R.id.zuo_mz);
        }
    }
}
