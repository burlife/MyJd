package com.example.mjd.imitate_jd.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mjd.imitate_jd.R;
import com.example.mjd.imitate_jd.bean.Pj_Bean;

import java.util.List;



//详情页的适配器
public class PJAdapter extends BaseAdapter {
    Context context;
    List<Pj_Bean.DataBean> pjlist;

    public PJAdapter(Context context, List<Pj_Bean.DataBean> pjlist) {
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
        PJHolder holder;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.pj_item,null);
            holder=new PJHolder();
            holder.pj_tv=convertView.findViewById(R.id.pj_tv);
            holder.name_tv=convertView.findViewById(R.id.name_tv);
            convertView.setTag(holder);
        }else{
            holder= (PJHolder) convertView.getTag();
        }
        holder.name_tv.setText(pjlist.get(position).getName());
        holder.pj_tv.setText(pjlist.get(position).getComment());
        return convertView;
    }
    class PJHolder{
        TextView name_tv;
        TextView pj_tv;
    }
}

