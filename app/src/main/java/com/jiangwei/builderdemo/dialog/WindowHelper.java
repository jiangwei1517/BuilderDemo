/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.jiangwei.builderdemo.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by lidongjun02 on 13-10-25.
 */
public class WindowHelper {
    /**
     * 获取屏幕高度
     *
     * @param
     */
    public static int getScreenHeight(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    /**
     * 获取屏幕宽度
     *
     * @param
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static void openAppInMarket(final String packageName, final Context context) {
        if (context == null || packageName == null || packageName.length() == 0) {
            return;
        }

        boolean success = true;
        final String marketAppUrl = "market://details?id=" + packageName;
        final String marketHttpUrl = "http://playVideo.google.com/store/apps/details?id=" + packageName;
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(marketAppUrl));
            context.startActivity(intent);
        } catch (Exception e) {
            success = false;
        }

        if (!success) {
            try {
                // 本机上没有电子市场，则打开浏览器
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(marketHttpUrl));
                context.startActivity(intent);
            } catch (Exception e) {
                // do nothing
            }
        }
    }


    public static void showInputMethod(final Activity activity) {
        if (activity == null)
            return;
        InputMethodManager inputMethodManager = ((InputMethodManager) activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE));
        if (activity.getCurrentFocus() != null) {
            inputMethodManager.showSoftInput(activity.getCurrentFocus(), 0);
        }
    }

    public static void hideInputMethod(final Activity activity) {
        if (activity == null)
            return;
        InputMethodManager inputMethodManager = ((InputMethodManager) activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE));

        inputMethodManager.isActive();
        if (activity.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                    0);
        }
    }

    /**
     * 安装软件
     *
     * @param context
     * @param apkPath
     */
    public static void installApk(Context context, Uri apkPath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(apkPath, "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}

