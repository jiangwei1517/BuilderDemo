package com.jiangwei.builderdemo.dialog;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.jiangwei.builderdemo.R;


/**
 * author:  jiangwei18 on 16/7/11 19:10
 * email:  jiangwei18@baidu.com
 * Hi:   jwill金牛
 */
public class AlertDialogHelper {

    public static void show(Context context, @StringRes int msgResId,
                            @StringRes int positiveTextId, DialogInterface.OnClickListener positiveListener,
                            @StringRes int negativeTextId, DialogInterface.OnClickListener negativeListener) {
        new AlertDialogBuilder(context)
                .setMessage(msgResId)
                .setLeftButton(positiveTextId, positiveListener)
                .setRightButton(negativeTextId, negativeListener)
                .show();
    }

    public static void show(Context context, CharSequence msg,
                            @StringRes int positiveTextId, DialogInterface.OnClickListener positiveListener,
                            @StringRes int negativeTextId, DialogInterface.OnClickListener negativeListener) {
        new AlertDialogBuilder(context)
                .setMessage(msg)
                .setLeftButton(positiveTextId, positiveListener)
                .setRightButton(negativeTextId, negativeListener)
                .show();
    }


    public static void show(Context context, @StringRes int msgResId,
                            @StringRes int textId, DialogInterface.OnClickListener listener) {
        new AlertDialogBuilder(context)
                .setMessage(msgResId)
                .setMiddleButton(textId, listener)
                .show();
    }

    public static void showNoCancelable(Context context, @StringRes int msgResId, @StringRes int textId,
                                        DialogInterface.OnClickListener listener) {
        new AlertDialogBuilder(context).setMessage(msgResId).setMiddleButton(textId, listener).setCancelable(false)
                .show();
    }

    public static void showNoCancelable(Context context, CharSequence msg,
                                        @StringRes int textId, DialogInterface.OnClickListener listener) {
        new AlertDialogBuilder(context)
                .setMessage(msg)
                .setMiddleButton(textId, listener)
                .setCancelable(false)
                .show();
    }

    public static void show(Context context, CharSequence msg,
                            @StringRes int textId, DialogInterface.OnClickListener listener) {
        new AlertDialogBuilder(context)
                .setMessage(msg)
                .setMiddleButton(textId, listener)
                .show();
    }

    public static void show(Context context, @StringRes int titleResId, CharSequence msg,
                            @StringRes int textId, DialogInterface.OnClickListener listener) {
        new AlertDialogBuilder(context)
                .setTitle(titleResId)
                .setMessage(msg)
                .setMiddleButton(textId, listener)
                .show();
    }

    public static void build(Context context, CharSequence msg,
                             @StringRes int textId, DialogInterface.OnClickListener listener) {
        new AlertDialogBuilder(context)
                .setMessage(msg)
                .setMiddleButton(textId, listener)
                .show();
    }

    public static Dialog setCustomStyleView(Context context, View view) {
        AlertDialogBuilder alertDialogBuilder = new AlertDialogBuilder(context);
        alertDialogBuilder.setView(view).show();
        return alertDialogBuilder.build();
    }

    public static class AlertDialogBuilder {
        public Context mContext;
        public View mView;
        public Dialog mDialog;

        public AlertDialogBuilder(Context context) {
            mContext = context;
            mView = View.inflate(context, R.layout.dialog_three_btn, null);
            mDialog = new Dialog(mContext, R.style.DialogStyle);
            mDialog.setContentView(mView);
            setWidth((int) (WindowHelper.getScreenWidth(context) * 0.8));
        }

        public AlertDialogBuilder setView(View view) {
            mDialog.setContentView(view);
            return this;
        }

        public AlertDialogBuilder setTitle(@StringRes int textId) {
            TextView textView = (TextView) mView.findViewById(R.id.title);
            textView.setVisibility(View.VISIBLE);
            textView.setText(textId);
            return this;
        }

        public AlertDialogBuilder setTitle(CharSequence msg) {
            TextView textView = (TextView) mView.findViewById(R.id.title);
            textView.setVisibility(View.VISIBLE);
            textView.setText(msg);
            return this;
        }

        public AlertDialogBuilder setMessage(@StringRes int textId) {
            ((TextView) mView.findViewById(R.id.message)).setText(textId);
            return this;
        }

        public AlertDialogBuilder setMessage(CharSequence msg) {
            ((TextView) mView.findViewById(R.id.message)).setText(msg);
            return this;
        }

        public AlertDialogBuilder setLeftButton(@StringRes int textId, final DialogInterface.OnClickListener listener) {
            TextView button = (TextView) mView.findViewById(R.id.button1);
            mView.findViewById(R.id.button3).setVisibility(View.GONE);
            button.setText(textId);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(mDialog, 0);
                    }
                    mDialog.dismiss();
                }
            });
            return this;
        }

        public AlertDialogBuilder setRightButton(@StringRes int textId, final DialogInterface.OnClickListener listener) {
            TextView button = (TextView) mView.findViewById(R.id.button2);
            button.setText(textId);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(mDialog, 1);
                    }
                    mDialog.dismiss();
                }
            });
            return this;
        }

        public AlertDialogBuilder setMiddleButton(@StringRes int textId, final DialogInterface.OnClickListener listener) {
            TextView button = (TextView) mView.findViewById(R.id.button3);
            mView.findViewById(R.id.button1).setVisibility(View.GONE);
            mView.findViewById(R.id.button2).setVisibility(View.GONE);
            button.setText(textId);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(mDialog, 2);
                    }
                    mDialog.dismiss();
                }
            });
            return this;
        }

        public AlertDialogBuilder setCancelable(boolean cancelable) {
            mDialog.setCancelable(cancelable);
            return this;
        }

        public AlertDialogBuilder setWidth(int width) {
            Window window = mDialog.getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = width;
            window.setAttributes(layoutParams);
            return this;
        }

        public Dialog build() {
            return mDialog;
        }

        public Dialog show() {
            Dialog dialog = build();
            dialog.show();
            return dialog;
        }
    }
}
