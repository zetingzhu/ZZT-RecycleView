/**
 * Copyright 2015 bingoogolapple
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zzt.rvadapterhelp.widget;

import android.view.View;
import android.view.ViewGroup;

import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.List;

/**
 * @author: zeting
 * @date: 2023/2/3
 * 带有头和尾部视图的列表
 */
public abstract class BaseRecyclerHADAdapter<D, VH extends RecyclerView.ViewHolder> extends BaseRecyclerABSAdapter<D> {
    private static final int BASE_ITEM_TYPE_HEADER = 2048;
    private static final int BASE_ITEM_TYPE_FOOTER = 4096;

    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();
    private int mCurrentMaxHeaderKey = BASE_ITEM_TYPE_HEADER;
    private int mCurrentMaxFooterKey = BASE_ITEM_TYPE_FOOTER;

    public BaseRecyclerHADAdapter(List<D> data) {
        super(data);
    }

    protected abstract void bindTheDataHAF(VH holder, D data);

    /**
     * 继承这个自定义 ItemViewType
     *
     * @param position
     * @return
     */
    public int getItemViewTypeHAF(int position) {
        return 0;
    }

    @Override
    protected void bindTheDataABS(RecyclerView.ViewHolder holder, D data) {
        bindTheDataHAF((VH) holder, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null) {
            // header 类型
            return new RecyclerView.ViewHolder(mHeaderViews.get(viewType)) {
            };
        } else if (mFootViews.get(viewType) != null) {
            // footer 类型
            return new RecyclerView.ViewHolder(mFootViews.get(viewType)) {
            };
        } else {
            // 真实 item 类型
            return super.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            // 返回 header 的 itemType
            return mHeaderViews.keyAt(position);
        } else if (isFooterView(position)) {
            // 返回 footer 的 itemType
            return mFootViews.keyAt(position - getHeadersCount() - getRealItemCount());
        } else {
            // 返回真实 item 的 itemType
            return getItemViewTypeHAF(position);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // 如果是 header 或 footer 就不绑定数据
        if (isHeaderViewOrFooterView(position)) {
            return;
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getFootersCount() + getRealItemCount();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();

            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (isHeaderViewOrFooterView(position)) {
                        // header 或 footer 时宽度占满父控件
                        return gridLayoutManager.getSpanCount();
                    } else {
                        // 真实的 item
                        if (spanSizeLookup != null) {
                            return spanSizeLookup.getSpanSize(position - getHeadersCount());
                        }
                        return 1;
                    }
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if (isHeaderViewOrFooterView(position)) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
    }

    /**
     * 获取除去 header 和 footer 后真实的 item 总数
     *
     * @return
     */
    public int getRealItemCount() {
        return super.getItemCount();
    }

    /**
     * 获取真实 item 的索引
     *
     * @param position
     * @return
     */
    public int getRealItemPosition(int position) {
        return position - getHeadersCount();
    }

    /**
     * 索引为 position 的 item 是否是 header
     *
     * @param position
     * @return
     */
    public boolean isHeaderView(int position) {
        return position < getHeadersCount();
    }

    /**
     * 索引为 position 的 item 是否是 footer
     *
     * @param position
     * @return
     */
    public boolean isFooterView(int position) {
        return position >= getHeadersCount() + getRealItemCount();
    }

    /**
     * 索引为 position 的 item 是否是 header 或 footer
     *
     * @param position
     * @return
     */
    public boolean isHeaderViewOrFooterView(int position) {
        return isHeaderView(position) || isFooterView(position);
    }

    /**
     * 添加 header
     *
     * @param view
     */
    public void addHeaderView(View view) {
        mHeaderViews.put(++mCurrentMaxHeaderKey, view);
        notifyItemInserted(getHeadersCount() - 1);
    }

    /**
     * 移除 header
     *
     * @param view
     */
    public void removeHeaderView(View view) {
        int index = mHeaderViews.indexOfValue(view);
        if (index != -1) {
            mHeaderViews.removeAt(index);
            notifyItemRemoved(index);
        }
    }

    /**
     * 添加 footer
     *
     * @param view
     */
    public void addFooterView(View view) {
        mFootViews.put(++mCurrentMaxFooterKey, view);
        notifyItemInserted(getHeadersCount() + getRealItemCount() + getFootersCount() - 1);
    }

    /**
     * 移除 footer
     *
     * @param view
     */
    public void removeFooterView(View view) {
        int index = mFootViews.indexOfValue(view);
        if (index != -1) {
            mFootViews.removeAt(index);
            notifyItemRemoved(getHeadersCount() + getRealItemCount() + index);
        }
    }

    /**
     * 获取 header 的个数
     *
     * @return
     */
    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    /**
     * 获取 footer 的个数
     *
     * @return
     */
    public int getFootersCount() {
        return mFootViews.size();
    }
}