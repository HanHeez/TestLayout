package com.gtv.hanhee.testlayout.ui.customview.skeleton;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class Skeleton {
    public static RecyclerViewSkeletonScreen.Builder bind(RecyclerView recyclerView) {
        return new RecyclerViewSkeletonScreen.Builder(recyclerView);
    }

    public static ViewSkeletonScreen.Builder bind(View view) {
        return new ViewSkeletonScreen.Builder(view);
    }
}
