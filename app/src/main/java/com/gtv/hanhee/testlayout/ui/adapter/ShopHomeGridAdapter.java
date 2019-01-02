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
import com.gtv.hanhee.testlayout.utils.ImageUtils;
import com.gtv.hanhee.testlayout.utils.StringUtils;

import java.util.List;

public class ShopHomeGridAdapter extends BaseQuickAdapter<Product, BaseViewHolder> {

    public List<Product> list;
    private Activity activity;
    OnItemRvClickListener onItemRvClickListener;

    public ShopHomeGridAdapter(Activity activity, List<Product> data, OnItemRvClickListener onItemRvClickListener    ) {
        super(R.layout.item_grid_shop_home, data);
        this. onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.activity = activity;
    }

    TextView txtName, txtTag, txtPrice, txtDiscountPrice, txtQuantity;
    ImageView imgProduct;

    @Override
    protected void convert(BaseViewHolder holder, Product item) {
//        Config View -----------------------
        txtName = holder.getView(R.id.txtName);
        txtTag = holder.getView(R.id.txtTag);
        txtPrice = holder.getView(R.id.txtPrice);
        txtDiscountPrice = holder.getView(R.id.txtDiscountPrice);
        txtQuantity = holder.getView(R.id.txtQuantity);
        imgProduct = holder.getView(R.id.imgProduct);

//        Setting Data ------------------------

        txtName.setText(item.getName());
        txtPrice.setText(StringUtils.formatPrice(item.getPrice()+""));
        txtDiscountPrice.setText(StringUtils.formatPrice(item.getDiscountPrice()+""));
        txtQuantity.setText(String.format(activity.getString(R.string.shop_quantity), item.getQuantity()));
        txtQuantity.setVisibility(View.GONE);
        ImageUtils.loadImageByGlideWithResize(activity,  item.getAvatar(), imgProduct, 350, 350);
    }
}


