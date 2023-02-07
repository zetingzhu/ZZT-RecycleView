package com.zzt.rvadapterhelp.widget;

import android.view.View;

/**
 * Created by dufangzhu on 2017/7/31.
 * 处理多次重复点击问题  这里只允许1秒钟内单击一次
 */

public abstract class OnSignleClickListener implements View.OnClickListener {
    /*到达下一次点击的时间间隔*/
    public static final int MIN_CLICK_DELAY_TIME = 1000;
    /*记录上一次的时间*/
    private long lastClickTime = 0;

    @Override
    public void onClick(View v) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onSignleClick(v);
        }
    }
    /*点击逻辑*/
    public abstract void onSignleClick(View v);
}
