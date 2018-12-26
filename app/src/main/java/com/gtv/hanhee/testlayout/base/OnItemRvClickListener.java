package com.gtv.hanhee.testlayout.base;

import android.view.View;

public interface OnItemRvClickListener<T> {
    void onItemRvClick(View view, T item, int adapterPosition);
}
