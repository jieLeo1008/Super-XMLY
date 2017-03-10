package com.jieleo.xmly_plus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jieleo.xmly_plus.R;
import com.jieleo.xmly_plus.model.bean.DisCoverBean;
import com.jieleo.xmly_plus.tools.MyViewHolder;

/**
 * Created by dllo on 17/3/10.
 */

public class DisCoverAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private DisCoverBean disCoverBean;
    private Context context;

    public void setDisCoverBean(DisCoverBean disCoverBean) {
        this.disCoverBean = disCoverBean;
    }

    public DisCoverAdapter(Context context) {

        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MyViewHolder.onCreatMyViewHolder(context, parent, R.layout.discover_recyclerview_item);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DisCoverBean.ListBeanX listBeanX=disCoverBean.getList().get(position);
        //判断每层有几行
        for (int i = 0; i < listBeanX.getList().size(); i++) {
            holder.setText(R.id.discover_recycleView_leftText,listBeanX.getList().get(i).getTitle());
            holder.setText(R.id.discover_recycleView_rightText,listBeanX.getList().get(i).getSubtitle());
            holder.setOnLineImage(R.id.discover_recycleView_leftImage,listBeanX.getList().get(i).getCoverPath() );
            holder.setOnLineImage(R.id.discover_recycleView_rightImage,listBeanX.getList().get(i).getSubCoverPath());
        }
     }

    @Override
    public int getItemCount() {
        return disCoverBean!=null?disCoverBean.getList().size():0;
    }


}
