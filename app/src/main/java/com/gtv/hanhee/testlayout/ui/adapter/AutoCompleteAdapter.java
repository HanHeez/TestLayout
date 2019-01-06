package com.gtv.hanhee.testlayout.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.MyApplication;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.manager.ChangeProductAmountEvent;
import com.gtv.hanhee.testlayout.manager.CheckboxCartEvent;
import com.gtv.hanhee.testlayout.manager.CheckboxProductEvent;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.ProductSection;
import com.gtv.hanhee.testlayout.utils.ImageUtils;
import com.gtv.hanhee.testlayout.utils.RxBus;
import com.gtv.hanhee.testlayout.utils.StringUtils;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

public class AutoCompleteAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public List<String> list;
    private Context context;
    OnItemRvClickListener onItemRvClickListener;

    public AutoCompleteAdapter(Context context, List<String> data, OnItemRvClickListener onItemRvClickListener    ) {
        super(R.layout.item_grid_product_recommend, data);
        this. onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.context = context;    }



    @Override
    protected void convert(BaseViewHolder holder, String item) {
        holder.setText(R.id.txtCompleteItem, item);
    }
}


