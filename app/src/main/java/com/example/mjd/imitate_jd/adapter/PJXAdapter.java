package com.example.mjd.imitate_jd.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mjd.imitate_jd.R;
import com.example.mjd.imitate_jd.bean.Pj_Bean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

//评价页面的适配器
public class PJXAdapter extends BaseAdapter {
    Context context;
    List<Pj_Bean.DataBean> pjlist;

    public PJXAdapter(Context context, List<Pj_Bean.DataBean> pjlist) {
        this.context = context;
        this.pjlist = pjlist;
    }

    @Override
    public int getCount() {
        return pjlist.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PjxHolder holder;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.pjx_item,null);
            holder=new PjxHolder();

            holder.p_name=convertView.findViewById(R.id.p_name);
            holder.p_nr=convertView.findViewById(R.id.p_nr);
            holder.p_sdv=convertView.findViewById(R.id.p_sdv);
            convertView.setTag(holder);
        }else{
            holder= (PjxHolder) convertView.getTag();
        }
        holder.p_name.setText(pjlist.get(position).getName());
        holder.p_nr.setText(pjlist.get(position).getComment());
        holder.p_sdv.setImageURI(pjlist.get(position).getAvatar());
        return convertView;
    }
    class PjxHolder{
        TextView p_name,p_nr;
        SimpleDraweeView p_sdv;
    }
}
