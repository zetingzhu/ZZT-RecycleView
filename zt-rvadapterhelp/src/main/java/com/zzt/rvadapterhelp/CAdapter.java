package com.zzt.rvadapterhelp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zzt.rvadapterhelp.widget.BaseRecyclerAdapter;
import com.zzt.rvadapterhelp.widget.BaseRecyclerViewHolder;

import java.util.List;

/**
 * @author: zeting
 * @date: 2023/2/6
 */
public class CAdapter extends BaseRecyclerAdapter<AEntity, CAdapter.CVH> {
    public CAdapter(List<AEntity> data) {
        super(data);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.item_a_test_layout;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(View view, int viewType) {
        return new CVH(view);
    }

    @Override
    protected void bindTheData(CVH holder, AEntity data) {
        TextView textView = holder.get(R.id.textView);
        textView.setText(data.getTitle());
    }

    public class CVH extends BaseRecyclerViewHolder {

        public CVH(View itemView) {
            super(itemView);
        }
    }
}
