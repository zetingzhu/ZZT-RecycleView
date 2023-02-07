package com.zzt.rvadapterhelp.widget;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: zeting
 * @date: 2022/2/14
 * 普通的列表适配器
 */
public abstract class BaseRecyclerABSAdapter<D> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = BaseRecyclerABSAdapter.class.getSimpleName();
    protected Context mContext;

    RecyclerView mRecyclerView;

    /**
     * 数据源
     */
    private List<D> mData;
    /**
     * 点击事件
     */
    private List<BaseRecyclerItemClickListener<RecyclerView.ViewHolder>> mListenerList;

    /**
     * 是否有延迟时间
     */
    private long delayMillis = 0;
    /**
     * 延迟监听
     */
    private BaseRecyclerDelayMillisListener mDelayListener;
    /**
     * 空视图
     */
    protected View emptyView;

    public BaseRecyclerABSAdapter(List<D> data) {
        this.mData = data == null ? new ArrayList<D>() : data;

        registerAdapterDataObserver(new GroupDataObserver());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        checkIfEmpty();
    }

    /**
     * 设置延时监听
     *
     * @param delayMillis
     * @param mDelayListener
     */
    public void setDelayMillis(long delayMillis, BaseRecyclerDelayMillisListener mDelayListener) {
        this.delayMillis = delayMillis;
        this.mDelayListener = mDelayListener;
    }

    /**
     * 更新列表数据
     */
    public void setListData(List<D> data, RecyclerView recyclerView) {
        this.mData = data == null ? new ArrayList<D>() : data;
        notifyChanged(recyclerView);
    }

    /**
     * 只设置数据
     *
     * @param data
     */
    public void setDataOnly(List<D> data) {
        this.mData = data == null ? new ArrayList<D>() : data;
    }


    /**
     * 添加数据到列表中
     *
     * @param appendedData
     */
    public void appendData(List<D> appendedData, RecyclerView recyclerView) {
        if (appendedData != null) {
            if (mData == null) {
                mData = new ArrayList<>();
            }
            mData.addAll(appendedData);
            notifyChanged(recyclerView);
        }
    }

    /**
     * 顶部插入数据
     *
     * @param appendedData
     * @param recyclerView
     */
    public void appendTopData(List<D> appendedData, RecyclerView recyclerView) {
        if (appendedData != null) {
            if (mData == null) {
                mData = new ArrayList<>();
            }
            mData.addAll(0, appendedData);
            notifyChanged(recyclerView);
        }
    }

    /**
     * 顶部添加数据只往上滚动一个
     *
     * @param appendedData
     * @param recyclerView
     */
    public void appendTopDataScrollPre(List<D> appendedData, RecyclerView recyclerView) {
        if (appendedData != null) {
            if (mData == null) {
                mData = new ArrayList<>();
            }
            mData.addAll(0, appendedData);
            int size = appendedData.size();
            notifyItemRangeInserted(0, size);
        }
    }


    /**
     * 添加单个对象到列表中
     */
    public void appendItemData(D appendedItemData, RecyclerView recyclerView) {
        if (appendedItemData != null) {
            if (mData == null) {
                mData = new ArrayList<>();
            }
            mData.add(appendedItemData);
            notifyChanged(recyclerView);
        }
    }


    /**
     * 设置适配器
     *
     * @param recyclerView
     */
//    public void setAdapter(RecyclerView recyclerView) {
//        if (mRecyclerView != null) {
//            this.mRecyclerView = recyclerView;
//            mRecyclerView.setAdapter(this);
//        }
//    }


    /**
     * 刷新数据
     */
    public void notifyChanged(RecyclerView recyclerView) {
        if (recyclerView != null) {
            if (recyclerView.isComputingLayout()) {
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        notifyDataSetChanged();
                    }
                });
            } else {
                notifyDataSetChanged();
            }
        }
    }

    /**
     * 设置空内容提示
     */
    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        checkIfEmpty();
    }

    protected void checkIfEmpty() {
        if (emptyView != null) {
            boolean emptyViewVisible = getItemCount() == 0;
            emptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
        }
    }

    /**
     * 获取列表分类
     *
     * @param viewType
     * @return
     */
    public abstract int getItemLayoutId(int viewType);

    /**
     * 实例化 ViewHolder
     */
    public RecyclerView.ViewHolder createViewHolder(View view, int viewType) {
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(getItemLayoutId(viewType), parent, false);
        final RecyclerView.ViewHolder holder = createViewHolder(view, viewType);
        holder.itemView.setOnClickListener(new OnSignleClickListener() {
            @Override
            public void onSignleClick(View v) {
                if (!isListEmpty(mListenerList)) {
                    if (delayMillis > 0) {
                        holder.itemView.postDelayed(() -> {
                            int position = holder.getBindingAdapterPosition();
                            if (Utils.isNotEmptyDataForList(mData, position)) {
                                for (BaseRecyclerItemClickListener<RecyclerView.ViewHolder> ClickListener : mListenerList) {
                                    ClickListener.onItemClick(holder, position, mData.get(position));
                                }
                            }
                        }, 200);
                        int position = holder.getBindingAdapterPosition();
                        if (mDelayListener != null && Utils.isNotEmptyDataForList(mData, position)) {
                            mDelayListener.onItemDelayMillis(position, mData.get(position));
                        }
                    } else {
                        int position = holder.getBindingAdapterPosition();
                        if (Utils.isNotEmptyDataForList(mData, position)) {
                            for (BaseRecyclerItemClickListener<RecyclerView.ViewHolder> ClickListener : mListenerList) {
                                ClickListener.onItemClick(holder, position, mData.get(position));
                            }
                            if (mDelayListener != null) {
                                mDelayListener.onItemDelayMillis(position, mData.get(position));
                            }
                        }
                    }
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindTheDataABS(holder, mData.get(holder.getBindingAdapterPosition()));
    }

    /**
     * 添加当前列表点击事件
     *
     * @param lis
     */
    public void addItemClickListener(BaseRecyclerItemClickListener<RecyclerView.ViewHolder> lis) {
        if (isListEmpty(mListenerList)) {
            mListenerList = new ArrayList<>();
        }
        this.mListenerList.add(lis);
    }

    /**
     * 添加单独一个事件
     *
     * @param lis
     */
    public void setItemClickListener(BaseRecyclerItemClickListener<RecyclerView.ViewHolder> lis) {
        if (isListEmpty(mListenerList)) {
            mListenerList = new ArrayList<>();
        }
        mListenerList.clear();
        this.mListenerList.add(lis);
    }


    @Override
    public int getItemCount() {
//        checkIfEmpty();
        return mData.size();
    }

    /**
     * 获取单条数据
     *
     * @param pos
     * @return
     */
    public D getItem(int pos) {
        return mData.get(pos);
    }

    /**
     * 获取所有数据
     *
     * @return
     */
    public List<D> getDataList() {
        return mData;
    }


    /**
     * 绑定数据
     *
     * @param holder 视图管理者
     * @param data   数据源
     */
    protected abstract void bindTheDataABS(RecyclerView.ViewHolder holder, D data);

    /**
     * 判断list是否为空
     */
    public static boolean isListEmpty(Collection<?> list) {
        return list == null || list.size() == 0;
    }

    /**
     * 数据变动监听
     */
    class GroupDataObserver extends RecyclerView.AdapterDataObserver {

        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        public void onItemRangeChanged(int positionStart, int itemCount) {
            checkIfEmpty();
        }

        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            onItemRangeChanged(positionStart, itemCount);
        }

        public void onItemRangeInserted(int positionStart, int itemCount) {
            checkIfEmpty();
        }

        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    }
}