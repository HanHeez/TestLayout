package com.gtv.hanhee.testlayout.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.utils.FormatUtils;
import com.gtv.hanhee.testlayout.utils.ImageUtils;
import com.gtv.hanhee.testlayout.utils.StringUtils;

import java.util.List;

public class ProductRecommendAdapter extends BaseQuickAdapter<Product, BaseViewHolder> {

    public List<Product> list;
    private Context context;
    OnItemRvClickListener onItemRvClickListener;

    public ProductRecommendAdapter(Context context, List<Product> data, OnItemRvClickListener onItemRvClickListener    ) {
        super(R.layout.item_grid_product_recommend, data);
        this. onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.context = context;    }

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
        txtPrice.setText(item.getPrice()+"");
        txtDiscountPrice.setText(StringUtils.formatPrice(item.getPrice()*(100-item.getDiscountPercent())/100 + ""));
        txtQuantity.setText(String.format(context.getString(R.string.shop_quantity), item.getQuantity()));
        txtQuantity.setVisibility(View.GONE);
        ImageUtils.loadImageByGlideWithResize(context,  item.getAvatar(), imgProduct, 350, 350);
    }
}


