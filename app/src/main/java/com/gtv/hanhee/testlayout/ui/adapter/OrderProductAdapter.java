package com.gtv.hanhee.testlayout.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.MyApplication;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.manager.ChangeProductAmountEvent;
import com.gtv.hanhee.testlayout.manager.CheckboxCartEvent;
import com.gtv.hanhee.testlayout.manager.CheckboxProductEvent;
import com.gtv.hanhee.testlayout.model.ProductSection;
import com.gtv.hanhee.testlayout.utils.ImageUtils;
import com.gtv.hanhee.testlayout.utils.RxBus;
import com.gtv.hanhee.testlayout.utils.SharedPreferencesUtil;
import com.gtv.hanhee.testlayout.utils.StringUtils;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

public class OrderProductAdapter extends BaseSectionQuickAdapter<ProductSection, BaseViewHolder> {

    public List<ProductSection> list;
    Activity activity;
    OnItemRvClickListener onItemRvClickListener;


    public OrderProductAdapter(Activity activity, List<ProductSection> data, OnItemRvClickListener onItemRvClickListener) {
        super(R.layout.item_order_product, R.layout.item_header_order_product, data);
        this.onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.activity = activity;
    }

    private TextView txtShopName;

    @Override
    protected void convertHead(BaseViewHolder holder, ProductSection item) {
//        Setting View ----------------
        txtShopName = holder.getView(R.id.txtShopName);
//        Event -----------------------
        holder.setText(R.id.txtShopName, "Được giao bởi "+item.header);
    }

    private TextView txtName, txtShortDescription, txtTag,
            txtPrice, txtDiscountPrice, txtQuantity, txtDiscountPercent,txtAmount, txtFreeShip;
    private ImageView imgProduct;
    private View divider;
    private LinearLayout lnPrice;
    private RelativeLayout rlTotalPriceShop;

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
        txtAmount = holder.getView(R.id.txtAmount);
        txtFreeShip = holder.getView(R.id.txtFreeShip);
        rlTotalPriceShop = holder.getView(R.id.rlTotalPriceShop);
        txtDiscountPercent = holder.getView(R.id.txtDiscountPercent);
        lnPrice = holder.getView(R.id.lnPrice);

//        Setting Data ------------------------
        if (item.t.isFreeShip()) {
            txtFreeShip.setVisibility(View.VISIBLE);
        } else {
            txtFreeShip.setVisibility(View.GONE);
        }
        txtAmount.setText("Số lượng: "+item.t.getOrderAmount());
        txtName.setText(item.t.getName());
        if (item.t.getShortDescription().length() > 0) {
            txtShortDescription.setText(item.t.getShortDescription());
        } else {
            txtShortDescription.setVisibility(View.GONE);
        }
        long discountPrice = item.t.getPrice()*(100-item.t.getDiscountPercent())/100;
        txtPrice.setText(StringUtils.formatPrice(item.t.getPrice() + ""));
        txtDiscountPrice.setText(StringUtils.formatPrice(discountPrice + ""));
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

        ImageUtils.loadImageByGlideWithResize(activity, item.t.getAvatar(), imgProduct, 350, 350);

        if (item.isEnd()) {
            divider.setVisibility(View.GONE);
            rlTotalPriceShop.setVisibility(View.VISIBLE);
            holder.setText(R.id.txtTotalProductShop, item.t.getOrderShopTotalProduct()+"");
            holder.setText(R.id.txtTotalPriceShop, StringUtils.formatPrice(item.t.getOrderTotalPrice()+""));

        } else {
            divider.setVisibility(View.VISIBLE);
            rlTotalPriceShop.setVisibility(View.GONE);
        }
    }

}
