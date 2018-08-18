package com.example.mjd.imitate_jd.goods.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mjd.imitate_jd.R;
import com.example.mjd.imitate_jd.bean.ShopBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;



public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {

    OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }


    Context context;
    ShopBean bean;


    public Myadapter(Context context, ShopBean bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.shuopitem, null);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final ShopBean.DataBean listBean=bean.getData().get(position);
        holder.tv1.setText(bean.getData().get(position).getTitle());
        holder.tv2.setText(""+bean.getData().get(position).getPrice());

        String[] split = listBean.getImages().split("\\|");
        DraweeController dc = Fresco.newDraweeControllerBuilder()
                .setUri(split[0])
                .setAutoPlayAnimations(true)
                .build();
        holder.img.setController(dc);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.onItemClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return bean.getData().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView img;
        private final TextView tv1,tv2;
        public MyViewHolder(View itemView) {
            super(itemView);
            img =  itemView.findViewById(R.id.image_shop);
            tv1 = itemView.findViewById(R.id.name_shop);
            tv2 = itemView.findViewById(R.id.price_shop);
        }
    }
    public interface OnItemClickListener{
        public void onItemClick(int position);
    }

}

