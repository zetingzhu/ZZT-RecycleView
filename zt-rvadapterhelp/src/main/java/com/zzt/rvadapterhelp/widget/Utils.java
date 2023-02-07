package com.zzt.rvadapterhelp.widget;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class Utils {
    /**
     * 判断list是否为空
     */
    public static boolean isListEmpty(Collection<?> list) {
        return list == null || list.size() == 0;
    }

    /**
     * 判断list不为空
     */
    public static boolean isNotListEmpty(Collection<?> coll) {
        return !isListEmpty(coll);
    }

    /**
     * 判断map是否为空
     */
    public static boolean isMapEmpty(Map<?, ?> map) {
        return map == null || map.size() == 0;
    }

    /**
     * 列表某一条数据不为空
     */
    public static boolean isNotEmptyDataForList(Collection<?> coll, int index) {
        if (isListEmpty(coll)) {
            return false;
        } else {
            int size = coll.size();
            if (index < 0 || index >= size) {
                return false;
            }
        }
        return true;
    }

}
