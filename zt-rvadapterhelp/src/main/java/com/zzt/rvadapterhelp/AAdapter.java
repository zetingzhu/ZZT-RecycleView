package com.zzt.rvadapterhelp;

import android.widget.TextView;

import com.zzt.rvadapterhelp.widget.BaseRecyclerAdapter;
import com.zzt.rvadapterhelp.widget.BaseRecyclerViewHolder;

import java.util.List;

/**
 * @author: zeting
 * @date: 2023/2/3
 */
public class AAdapter extends BaseRecyclerAdapter<AEntity, BaseRecyclerViewHolder> {
    public AAdapter(List<AEntity> data) {
        super(data);
    }


    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.item_a_test_layout;
    }


    protected void bindTheData(BaseRecyclerViewHolder holder, AEntity data) {
        TextView textView = holder.get(R.id.textView);
        textView.setText(data.getTitle());
    }
}
