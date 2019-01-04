package com.gtv.hanhee.testlayout.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.manager.CheckboxCartEvent;
import com.gtv.hanhee.testlayout.model.ProductSection;
import com.gtv.hanhee.testlayout.utils.ImageUtils;
import com.gtv.hanhee.testlayout.utils.RxBus;
import com.gtv.hanhee.testlayout.utils.StringUtils;

import java.util.List;

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

    private CheckBox cbShop;
    private TextView txtShopName;

    @Override
    protected void convertHead(BaseViewHolder holder, ProductSection item) {
//        Setting View ----------------
        txtShopName = holder.getView(R.id.txtShopName);
        cbShop = holder.getView(R.id.cbShop);

//        Event -----------------------
        holder.setText(R.id.txtShopName, item.header);
        cbShop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    RxBus.getInstance().post(new CheckboxCartEvent(holder.getAdapterPosition(), isChecked));

            }
        });
    }

    private TextView txtName, txtShortDescription, txtTag, txtPrice, txtDiscountPrice, txtQuantity, btnDecrease, btnIncrease, txtDiscountPercent;
    LinearLayout lnPrice;
    private ImageView imgProduct;
    private View divider;
    private LinearLayout lnFreeship;
    private CheckBox cbProduct;
    private EditText edtAmount;

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
        divider = holder.getView(R.id.divider);
        lnFreeship = holder.getView(R.id.lnFreeship);
        cbProduct = holder.getView(R.id.cbProduct);
        edtAmount = holder.getView(R.id.edtAmount);
        btnDecrease = holder.getView(R.id.btnDecrease);
        btnIncrease = holder.getView(R.id.btnIncrease);
        txtDiscountPercent = holder.getView(R.id.txtDiscountPercent);
        lnPrice = holder.getView(R.id.lnPrice);

//        Setting Data ------------------------

        txtName.setText(item.t.getName());
        if (item.t.getShortDescription().length() > 0) {
            txtShortDescription.setText(item.t.getShortDescription());
        } else {
            txtShortDescription.setVisibility(View.GONE);
        }
        txtPrice.setText(StringUtils.formatPrice(item.t.getPrice() + ""));
        txtDiscountPrice.setText(StringUtils.formatPrice(item.t.getPrice()*(100-item.t.getDiscountPercent())/100 + ""));
        if (item.t.getDiscountPercent()>0) {
            txtDiscountPercent.setText("-"+item.t.getDiscountPercent() + "%");
            txtDiscountPercent.setVisibility(View.VISIBLE);
            lnPrice.setVisibility(View.VISIBLE);
        } else {
            txtDiscountPercent.setVisibility(View.GONE);
            lnPrice.setVisibility(View.GONE);
        }
        txtQuantity.setText(String.format(activity.getString(R.string.shop_quantity), item.t.getQuantity()));
        txtQuantity.setVisibility(View.GONE);
        cbProduct.setChecked(item.isChecked());
        ImageUtils.loadImageByGlideWithResize(activity, item.t.getAvatar(), imgProduct, 350, 350);

        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.t.getOrderAmount()>1) {
                    item.t.setOrderAmount(item.t.getOrderAmount() - 1);
                } else {

                }
            }
        });

        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        if (item.isEnd()) {
            divider.setVisibility(View.GONE);
        } else {
            divider.setVisibility(View.VISIBLE);
        }

        if (item.isEnd() && (item.t.isFreeShip())) {
            divider.setVisibility(View.GONE);
            lnFreeship.setVisibility(View.VISIBLE);
        } else {
            divider.setVisibility(View.VISIBLE);
            lnFreeship.setVisibility(View.GONE);
        }
    }

}
