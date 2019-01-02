package com.gtv.hanhee.testlayout.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.MyApplication;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.ProductSection;
import com.gtv.hanhee.testlayout.utils.ImageUtils;
import com.gtv.hanhee.testlayout.utils.StringUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartAdapter extends BaseSectionQuickAdapter<ProductSection, BaseViewHolder> {

    public List<ProductSection> list;
    Activity activity;
    OnItemRvClickListener onItemRvClickListener;


    public CartAdapter(Activity activity, List<ProductSection> data, OnItemRvClickListener onItemRvClickListener) {
        super(R.layout.item_cart, R.layout.item_header_cart, data);
        this.onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.activity = activity;
    }


    @Override
    protected void convertHead(BaseViewHolder holder, ProductSection item) {
        //TODO set txtGroupTag = getTag
        holder.setText(R.id.txtShopName, item.header);
    }

    TextView txtName, txtShortDescription, txtTag, txtPrice, txtDiscountPrice, txtQuantity;
    ImageView imgProduct;

    @Override
    protected void convert(BaseViewHolder holder, ProductSection item) {
        //        Config View -----------------------
        txtName = holder.getView(R.id.txtName);
        txtShortDescription = holder.getView(R.id.txtShortDescription);
        txtTag = holder.getView(R.id.txtTag);
        txtPrice = holder.getView(R.id.txtPrice);
        txtDiscountPrice = holder.getView(R.id.txtDiscountPrice);
        txtQuantity = holder.getView(R.id.txtQuantity);
        imgProduct = holder.getView(R.id.imgProduct);

//        Setting Data ------------------------

        txtName.setText(item.t.getName());
        if (item.t.getShortDescription().length()>0) {
            txtShortDescription.setText(item.t.getShortDescription());
        } else {
            txtShortDescription.setVisibility(View.GONE);
        }
        txtPrice.setText(StringUtils.formatPrice(item.t.getPrice()+""));
        txtDiscountPrice.setText(StringUtils.formatPrice(item.t.getDiscountPrice()+""));
        txtQuantity.setText(String.format(activity.getString(R.string.shop_quantity), item.t.getQuantity()));
        txtQuantity.setVisibility(View.GONE);
        ImageUtils.loadImageByGlideWithResize(activity,  item.t.getAvatar(), imgProduct, 350, 350);
    }

}
