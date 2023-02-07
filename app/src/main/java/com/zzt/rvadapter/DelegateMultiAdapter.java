package com.zzt.rvadapter;


import android.nfc.Tag;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.zzt.rvadapter.base.BaseDelegateMultiAdapter;
import com.zzt.rvadapter.base.BaseMultiTypeDelegate;
import com.zzt.rvadapter.base.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DelegateMultiAdapter extends BaseDelegateMultiAdapter<DelegateMultiEntity, BaseViewHolder> {

    public DelegateMultiAdapter() {
        super();
        // 方式二，实现自己的代理类：
        setMultiTypeDelegate(new MyMultiTypeDelegate());
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, @NotNull DelegateMultiEntity item) {
        switch (helper.getItemViewType()) {
            case DelegateMultiEntity.TEXT:
                helper.setText(R.id.tv, "CymChad " + helper.getAdapterPosition());
                break;
            case DelegateMultiEntity.IMG_TEXT:
                switch (helper.getLayoutPosition() % 2) {
                    case 0:
                        helper.setImageResource(R.id.iv, R.mipmap.animation_img1);
                        break;
                    case 1:
                        helper.setImageResource(R.id.iv, R.mipmap.animation_img2);
                        break;
                    default:
                        break;
                }
                helper.setText(R.id.tv, "ChayChan " + helper.getAdapterPosition());

                EditText testEdit = ((EditText) helper.getView(R.id.edit_test));
                Log.d("Tag", "这里在保存数据  获取：" + item.getEditTest());
                testEdit.setText(item.getEditTest());

                final TextWatcher textWatcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        Log.e("textWatcher", helper.getAdapterPosition() + "");
                        if (testEdit.hasFocus()) {
                            item.setEditTest(s.toString());
                        }
                    }
                };
                testEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            testEdit.addTextChangedListener(textWatcher);
                        } else {
                            testEdit.removeTextChangedListener(textWatcher);
                        }
                    }
                });
                break;
            default:
                break;
        }
    }

    // 方式二：实现自己的代理类
    final static class MyMultiTypeDelegate extends BaseMultiTypeDelegate<DelegateMultiEntity> {

        public MyMultiTypeDelegate() {
            addItemType(DelegateMultiEntity.TEXT, R.layout.item_text_view);
            addItemType(DelegateMultiEntity.IMG, R.layout.item_image_view);
            addItemType(DelegateMultiEntity.IMG_TEXT, R.layout.item_img_text_view);
        }

        @Override
        public int getItemType(@NotNull List<? extends DelegateMultiEntity> data, int position) {
            switch (position % 3) {
                case 0:
                    return DelegateMultiEntity.TEXT;
                case 1:
                    return DelegateMultiEntity.IMG;
                case 2:
                    return DelegateMultiEntity.IMG_TEXT;
                default:
                    break;
            }
            return 0;
        }
    }

    private class MyTextChangedListener implements TextWatcher {
        DelegateMultiEntity item;

        public MyTextChangedListener(DelegateMultiEntity item) {
            this.item = item;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            item.setEditTest(s.toString());
            Log.d("Tag", "这里在保存数据：" + s.toString());
            notifyDataSetChanged();
        }
    }
}
