package com.gtv.hanhee.testlayout.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.CategoryService;

import java.util.List;

public class UserServiceAdapter extends BaseQuickAdapter<CategoryService, BaseViewHolder> {

    public List<CategoryService> list;
    private Activity activity;
    OnItemRvClickListener onItemRvClickListener;


    public UserServiceAdapter(Activity activity, List<CategoryService> data, OnItemRvClickListener onItemRvClickListener    ) {
        super(R.layout.item_user_service, data);
        this. onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.activity = activity;
    }

    ImageView imgService;
    RelativeLayout rlService;
    @Override
    protected void convert(final BaseViewHolder holder, final CategoryService item) {
        holder.setText(R.id.txtDescription, item.getName());
        imgService = holder.getView(R.id.imgService);
        rlService = holder.getView(R.id.rlService);
        // load image picasso
        Glide.with(activity).load(item.getDrawableImage()).into(imgService);

        rlService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemRvClickListener.onItemRvClick(v, item, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
