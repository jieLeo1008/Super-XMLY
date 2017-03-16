package com.jieleo.xmly_plus.adapter.hot_adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.jieleo.xmly_plus.R;
import com.jieleo.xmly_plus.model.bean.model_hot_page.HotCenterBean;
import com.jieleo.xmly_plus.tools.BaseViewHolder;
import com.jieleo.xmly_plus.tools.DemoRecyclerView;

/**
 * Created by dllo on 17/3/15.
 */

public class HotBelowAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = "HotBelowAdapter";
    private HotCenterBean hotCenterBean;
    private Context context;

    public void setHotCenterBean(HotCenterBean hotCenterBean) {
        this.hotCenterBean = hotCenterBean;
        notifyDataSetChanged();
    }

    public HotBelowAdapter(Context context) {

        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.onCreatMyViewHolder(context, parent, R.layout.fragment_hot_below_item);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        HotBelowItemAdapter adapter = new HotBelowItemAdapter(context);
        RecyclerView itemRv = holder.getView(R.id.fragment_hot_below_item_recycleView);
        adapter.setHotCenterBean(hotCenterBean);
        itemRv.setLayoutManager(new LinearLayoutManager(context));
        itemRv.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "getItemCount: "+ hotCenterBean.getHotRecommends().getList().get(0).getList().size());
        return hotCenterBean != null ? hotCenterBean.getHotRecommends().getList().size() : 0;
    }


}



