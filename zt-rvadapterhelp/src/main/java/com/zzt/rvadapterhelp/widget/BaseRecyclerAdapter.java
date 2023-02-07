package com.zzt.rvadapterhelp.widget;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @author: zeting
 * @date: 2022/2/14
 * 普通的列表适配器
 */
public abstract class BaseRecyclerAdapter<D, VH extends RecyclerView.ViewHolder> extends BaseRecyclerABSAdapter<D> {
    public BaseRecyclerAdapter(List<D> data) {
        super(data);
    }

    protected abstract void bindTheData(VH holder, D data);

    @Override
    public RecyclerView.ViewHolder createViewHolder(View view, int viewType) {
        return (VH) new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindTheData((VH) holder, getDataList().get(holder.getBindingAdapterPosition()));
    }

    @Override
    protected void bindTheDataABS(RecyclerView.ViewHolder holder, D data) {

    }


}