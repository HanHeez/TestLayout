package com.gtv.hanhee.testlayout.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.AddressInfo;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.utils.ImageUtils;
import com.gtv.hanhee.testlayout.utils.StringUtils;

import java.util.List;

public class AddressInfoAdapter extends BaseQuickAdapter<AddressInfo, BaseViewHolder> {

    public List<AddressInfo> list;
    private Activity activity;
    OnItemRvClickListener onItemRvClickListener;

    public AddressInfoAdapter(Activity activity, List<AddressInfo> data, OnItemRvClickListener onItemRvClickListener    ) {
        super(R.layout.item_address_info, data);
        this. onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder holder, AddressInfo item) {

    }
}


