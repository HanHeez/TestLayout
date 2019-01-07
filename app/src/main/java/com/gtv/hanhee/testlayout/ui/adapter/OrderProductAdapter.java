package com.gtv.hanhee.testlayout.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        super(R.layout.item_cart, R.layout.item_header_cart, data);
        this.onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.activity = activity;
    }

    private CheckBox cbShop;
    private LinearLayout lnCbShop;
    private TextView txtShopName;

    @Override
    protected void convertHead(BaseViewHolder holder, ProductSection item) {
//        Setting View ----------------
        txtShopName = holder.getView(R.id.txtShopName);
        cbShop = holder.getView(R.id.cbShop);
        lnCbShop = holder.getView(R.id.lnCbShop);

//        Event -----------------------
        lnCbShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemRvClickListener.onItemRvClick(v, item, holder.getAdapterPosition());
            }
        });
        cbShop.setChecked(item.isCheckedShop());
        holder.setText(R.id.txtShopName, item.header);
        cbShop.setOnClickListener(v -> {
            CheckBox cb = (CheckBox) v;
            RxBus.getInstance().post(new CheckboxCartEvent(holder.getAdapterPosition(), cb.isChecked()));
        });
    }

    private TextView txtName, txtShortDescription, txtTag,
            txtPrice, txtDiscountPrice, txtQuantity, txtDiscountPercent,txtAmount;
    private ImageView imgProduct;
    private View divider;
    private LinearLayout lnFreeship, lnCbProduct, btnIncrease, lnPrice, btnDecrease;
    private CheckBox cbProduct;
    private int amountProductCart;

    @Override
    protected void convert(BaseViewHolder holder, ProductSection item) {
        //        Config View -----------------------
        amountProductCart = SharedPreferencesUtil.getInstance().getInt("amountCart", 0);

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
        txtAmount = holder.getView(R.id.txtAmount);
        btnDecrease = holder.getView(R.id.btnDecrease);
        btnIncrease = holder.getView(R.id.btnIncrease);
        txtDiscountPercent = holder.getView(R.id.txtDiscountPercent);
        lnPrice = holder.getView(R.id.lnPrice);
        lnCbProduct = holder.getView(R.id.lnCbProduct);

//        Setting Data ------------------------
        lnCbProduct.setOnClickListener(v -> onItemRvClickListener.onItemRvClick(v, item.t, holder.getAdapterPosition()));

        txtAmount.setText(item.t.getOrderAmount()+"");
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
        cbProduct.setChecked(item.t.isCheckedProduct());
        txtQuantity.setText(String.format(activity.getString(R.string.shop_quantity), item.t.getQuantity()));
        txtQuantity.setVisibility(View.GONE);

        ImageUtils.loadImageByGlideWithResize(activity, item.t.getAvatar(), imgProduct, 350, 350);

        btnDecrease.setOnClickListener(v -> {
            if (item.t.getOrderAmount()>1) {
                amountProductCart = SharedPreferencesUtil.getInstance().getInt("amountCart", 0);
                amountProductCart--;
                SharedPreferencesUtil.getInstance().putInt("amountCart", amountProductCart);
                item.t.setOrderAmount(item.t.getOrderAmount()-1);
                Completable.fromAction(() -> MyApplication.mProductDao.updateProducts(item.t)).subscribeOn(Schedulers.io())
                        .subscribe();
                RxBus.getInstance().post(new ChangeProductAmountEvent(discountPrice, "decrease"));
                notifyDataSetChanged();
            }
        });

        cbProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                RxBus.getInstance().post(new CheckboxProductEvent(holder.getAdapterPosition(), cb.isChecked()));
            }
        });

        btnIncrease.setOnClickListener(v -> {
            if (item.t.getOrderAmount()<5) {
                amountProductCart = SharedPreferencesUtil.getInstance().getInt("amountCart", 0);
                amountProductCart++;
                SharedPreferencesUtil.getInstance().putInt("amountCart", amountProductCart);
                item.t.setOrderAmount(item.t.getOrderAmount()+1);
                Completable.fromAction(() -> MyApplication.mProductDao.updateProducts(item.t)).subscribeOn(Schedulers.io())
                        .subscribe();
                RxBus.getInstance().post(new ChangeProductAmountEvent(discountPrice, "increase"));
                notifyDataSetChanged();
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
