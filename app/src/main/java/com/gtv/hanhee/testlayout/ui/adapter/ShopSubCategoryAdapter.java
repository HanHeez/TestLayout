package com.gtv.hanhee.testlayout.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.SubCategory;
import com.gtv.hanhee.testlayout.utils.ImageUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShopSubCategoryAdapter extends BaseQuickAdapter<SubCategory, BaseViewHolder> {

    public List<SubCategory> list;
    private Activity activity;
    OnItemRvClickListener onItemRvClickListener;

    public ShopSubCategoryAdapter(Activity activity, List<SubCategory> data, OnItemRvClickListener onItemRvClickListener    ) {
        super(R.layout.item_shop_subcategory, data);
        this. onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.activity = activity;
    }

    CircleImageView imgAvatar;
    TextView txtName;

    @Override
    protected void convert(BaseViewHolder holder, SubCategory item) {
//        Config View -----------------------
        imgAvatar = holder.getView(R.id.imgAvatar);
        txtName = holder.getView(R.id.txtName);

//        Setting Data ------------------------
        ImageUtils.loadCircleImageByGlideResize(activity, item.getImage(), imgAvatar, 300, 300);
        txtName.setText(item.getName());

    }
}


