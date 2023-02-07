package com.zzt.rvadapterhelp;

import android.widget.TextView;

import com.zzt.rvadapterhelp.widget.BaseRecyclerHADAdapter;
import com.zzt.rvadapterhelp.widget.BaseRecyclerViewHolder;

import java.util.List;

/**
 * @author: zeting
 * @date: 2023/2/6
 */
public class ABAdapter extends BaseRecyclerHADAdapter<AEntity, BaseRecyclerViewHolder> {

    public ABAdapter(List<AEntity> data) {
        super(data);
    }


    protected void bindTheDataHAF(BaseRecyclerViewHolder holder, AEntity data) {
        TextView textView = holder.get(R.id.textView);
        textView.setText(data.getTitle());
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.item_a_test_layout;
    }

}
