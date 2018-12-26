package com.gtv.hanhee.testlayout.ui.customview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;

import com.gtv.hanhee.testlayout.R;


public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        this(context, 0);
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static LoadingDialog instance(Activity activity) {
        LoadingView v = (LoadingView) View.inflate(activity, R.layout.common_progress_view, null);
        v.setColor(ContextCompat.getColor(activity, R.color.menu_bg_color));
        LoadingDialog dialog = new LoadingDialog(activity, R.style.loading_dialog);
        dialog.setContentView(v,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)
        );
        return dialog;
    }
}