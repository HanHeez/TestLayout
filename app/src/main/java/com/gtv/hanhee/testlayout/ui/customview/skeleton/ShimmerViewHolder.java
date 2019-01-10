package com.gtv.hanhee.testlayout.ui.customview.skeleton;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gtv.hanhee.testlayout.R;

public class ShimmerViewHolder extends RecyclerView.ViewHolder {

    public ShimmerViewHolder(LayoutInflater inflater, ViewGroup parent, int innerViewResId) {
        super(inflater.inflate(R.layout.layout_shimmer, parent, false));
        ViewGroup layout = (ViewGroup) itemView;
        View view = inflater.inflate(innerViewResId, layout, false);
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp != null) {
            layout.setLayoutParams(lp);
        }
        layout.addView(view);
    }
}