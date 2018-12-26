package com.gtv.hanhee.testlayout.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class FitWidthRecyclerUtils {
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }
}
