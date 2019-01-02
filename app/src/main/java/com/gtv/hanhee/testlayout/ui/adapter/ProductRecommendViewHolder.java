package com.gtv.hanhee.testlayout.ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.utils.ImageUtils;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductRecommendViewHolder implements MZViewHolder<List<Product>>, OnItemRvClickListener<Product> {

    @BindView(R.id.rvRecommend)
    RecyclerView rvRecommend;
    private ProductRecommendAdapter productRecommendAdapter;

    @Override
    public View createView(Context context) {
        // 返回页面布局
        View view = LayoutInflater.from(context).inflate(R.layout.custom_product_recommend_banner,null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onBind(Context context, int position, List<Product> data) {


//        Rv Product -----------------
        productRecommendAdapter = new ProductRecommendAdapter(context, data, this);      ;
        rvRecommend.setHasFixedSize(true);
        rvRecommend.setNestedScrollingEnabled(false);

        GridLayoutManager gridLayoutManagerShop = new GridLayoutManager(context, 3);
        rvRecommend.setLayoutManager(gridLayoutManagerShop);
        rvRecommend.setAdapter(productRecommendAdapter);
        productRecommendAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRvClick(View view, Product item, int adapterPosition) {

    }
}
