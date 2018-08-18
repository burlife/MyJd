package com.example.mjd.imitate_jd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mjd.imitate_jd.R;
import com.example.mjd.imitate_jd.bean.ShopBean2;
import com.example.mjd.imitate_jd.eventbusevent.MessageEvent;
import com.example.mjd.imitate_jd.eventbusevent.PriceAndCountEvent;
import com.example.mjd.imitate_jd.view.MyView;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.List;



public class GouWuAdapter extends BaseExpandableListAdapter{
    private List<ShopBean2.DataBean> groupList;
    private List<List<ShopBean2.DataBean.ListBean>> chlidList;
    private Context context;
    private final LayoutInflater inflater;

    public GouWuAdapter(Context context, List<ShopBean2.DataBean> groupList, List<List<ShopBean2.DataBean.ListBean>> chlidList) {
        this.groupList = groupList;
        this.context = context;
        this.chlidList = chlidList;
        inflater=LayoutInflater.from(context);
    }


    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return chlidList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return chlidList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view;
        final GroupViewHolder holder;
        if(convertView == null){
            holder=new GroupViewHolder();
            view=inflater.inflate(R.layout.father_items_layout,null);
            holder.cbGroup=view.findViewById(R.id.cb_parent);
            holder.tv_number=view.findViewById(R.id.tv_number);
            holder.tv_sign=view.findViewById(R.id.tv_sign);
            view.setTag(holder);
        }else{
            view=convertView;
            holder= (GroupViewHolder) view.getTag();

        }
        final ShopBean2.DataBean dataBean = groupList.get(groupPosition);
        holder.cbGroup.setChecked(dataBean.isChecked());
        holder.tv_number.setText(dataBean.getSellerid());
        holder.tv_sign.setText(dataBean.getSellerName());
        holder.cbGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果点击一级列表的选中框
                dataBean.setChecked(holder.cbGroup.isChecked());
                //changeChildCbState改变二级列表checkbox状态



                changeChildCbState(groupPosition,holder.cbGroup.isChecked());
                EventBus.getDefault().post(compute());
                // changeAllCbState改变全选的状态
                // ,isAllGroupCbSelected()一级列表是否全部选中
                changeAllCbState(isAllGroupCbSelected());
                notifyDataSetChanged();
            }


        });
        return view;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view;
        final ChildViewHolder holder2;
        if(convertView == null){
            holder2=new ChildViewHolder();
            view=inflater.inflate(R.layout.child_items_layout,null);
            holder2.cbChild=view.findViewById(R.id.cb_child);
            holder2.tv_tel=view.findViewById(R.id.tv_tel);
            holder2.tv_time=view.findViewById(R.id.tv_time);
            holder2.tv_price = view.findViewById(R.id.tv_pri);
            holder2.tv_del = view.findViewById(R.id.tv_del);
            holder2.iv=view.findViewById(R.id.iv);
            holder2.myView=view.findViewById(R.id.mv);
            view.setTag(holder2);
        }else {
            view=convertView;
            holder2= (ChildViewHolder) view.getTag();
        }
        final ShopBean2.DataBean.ListBean listBean = chlidList.get(groupPosition).get(childPosition);
        holder2.cbChild.setChecked(listBean.isChecked());
        holder2.tv_tel.setText(listBean.getTitle());
        holder2.tv_time.setText(listBean.getCreatetime());
        holder2.tv_price.setText(listBean.getPrice()+"");
        holder2.myView.setNum(listBean.getNum()+"");
        //拆分图片url
        String[] split=listBean.getImages().split("\\|");
        ImageLoader.getInstance().displayImage(split[0] ,holder2.iv);
        holder2.cbChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置该条目对象里的checked属性值
                listBean.isChecked(holder2.cbChild.isChecked());
                PriceAndCountEvent priceAndCountEvent=compute();
                EventBus.getDefault().post(priceAndCountEvent);
                if(holder2.cbChild.isChecked()){
                    //当前checkbox是选中状态
                    if(isAllChildCbSelected(groupPosition)){
                        changGroupCbState(groupPosition,true);
                        changeAllCbState(isAllGroupCbSelected());
                    }else {
                        changGroupCbState(groupPosition,false);
                        changeAllCbState(isAllGroupCbSelected());;
                    }
                    notifyDataSetChanged();
                }
            }
        });
        //加
        holder2.myView.setAddClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = holder2.myView.getNum();
                num++;
                listBean.setNum(num);
                if (holder2.cbChild.isChecked()) {
                    EventBus.getDefault().post(compute());
                }
                notifyDataSetChanged();
            }
        });
        //减
        holder2.myView.setDelClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = holder2.myView.getNum();
                if(num == 1){
                    return;
                }
                num--;
                listBean.setNum(num);
                if (holder2.cbChild.isChecked()) {
                    EventBus.getDefault().post(compute());
                }
                notifyDataSetChanged();
            }
        });
        holder2.tv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ShopBean2.DataBean.ListBean> myBeans = chlidList.get(groupPosition);
                ShopBean2.DataBean.ListBean remove = myBeans.remove(childPosition);
                if(myBeans.size() ==0){
                    chlidList.remove(groupPosition);
                    groupList.remove(groupPosition);
                }
                EventBus.getDefault().post(compute());
                notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    class GroupViewHolder{
        CheckBox cbGroup;
        TextView tv_number;
        TextView tv_sign;
    }
    class ChildViewHolder {
        CheckBox cbChild;
        ImageView iv;
        TextView tv_tel;
        TextView tv_time;
        TextView tv_price;
        TextView tv_del;
        private MyView myView;
    }
    //计算价格
    private PriceAndCountEvent compute(){
        int price=0;
        int count=0;
        for(int i=0;i<chlidList.size();i++){
            List<ShopBean2.DataBean.ListBean> beanList = chlidList.get(i);
            for(int j=0;j<beanList.size();j++ ){
                ShopBean2.DataBean.ListBean listBean = beanList.get(j);
                if(listBean.isChecked()){
                    price+=listBean.getPrice()*listBean.getNum();
                    count+=listBean.getNum();

                }
            }
        }
        PriceAndCountEvent priceAndCountEvent=new PriceAndCountEvent();
        priceAndCountEvent.setPrice(price);
        priceAndCountEvent.setCount(count);
        return priceAndCountEvent;
    }
    private boolean  isAllChildCbSelected( int groupPosition){
        List<ShopBean2.DataBean.ListBean> been = chlidList.get(groupPosition);
        for(int i=0;i<been.size();i++){
            ShopBean2.DataBean.ListBean bean = been.get(i);
            if(!bean.isChecked()){
                return  false;
            }
        }

        return  true;
    }
    /**
     * 判断一级列表是否全部选中
     *
     * @return
     */
    private boolean isAllGroupCbSelected(){
        for(int i=0;i<groupList.size();i++){
            ShopBean2.DataBean dataBea = groupList.get(i);
            if(! dataBea.isChecked()){
                return false;
            }
        }
        return  true;
    }
    //改变一级列表
    private void changGroupCbState(int groupPosition,boolean flag){
        ShopBean2.DataBean dataBeans = groupList.get(groupPosition);
        dataBeans.setChecked(flag);
    }
    //改变二级列表
    private void changeChildCbState(int groupPosition,boolean flag){
        List<ShopBean2.DataBean.ListBean> lists = chlidList.get(groupPosition);
        for(int i=0;i<lists.size();i++){
            ShopBean2.DataBean.ListBean wolists = lists.get(i);
            wolists.setChecked(flag);

        }
    }
    //改变全选的状态值
    private void changeAllCbState(boolean flag){
        MessageEvent messageEvent=new MessageEvent();
        messageEvent.setChecked(flag);
        EventBus.getDefault().post(messageEvent);
    }
    /**
     * 设置全选、反选
     *
     * @param flag
     */
    public void changeAllListCbState(boolean flag) {
        for (int i = 0; i < groupList.size(); i++) {
            changGroupCbState(i, flag);
            changeChildCbState(i, flag);
        }
        EventBus.getDefault().post(compute());
        notifyDataSetChanged();
    }
}
