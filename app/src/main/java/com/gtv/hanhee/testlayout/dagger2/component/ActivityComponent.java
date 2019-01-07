package com.gtv.hanhee.testlayout.dagger2.component;


import com.gtv.hanhee.testlayout.dagger2.PerActivity;
import com.gtv.hanhee.testlayout.dagger2.module.ActivityModule;
import com.gtv.hanhee.testlayout.ui.activity.CartActivity;
import com.gtv.hanhee.testlayout.ui.activity.OrderActivity;
import com.gtv.hanhee.testlayout.ui.activity.ProductDetailActivity;
import com.gtv.hanhee.testlayout.ui.activity.ShopDetailActivity;
import com.gtv.hanhee.testlayout.ui.fragment.ShopCategoryFragment;
import com.gtv.hanhee.testlayout.ui.fragment.ShopFragment;
import com.gtv.hanhee.testlayout.ui.fragment.ShopHomeFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    ShopHomeFragment inject(ShopHomeFragment shopHomeFragment);
    ShopCategoryFragment inject(ShopCategoryFragment shopCategoryFragment);
    ShopFragment inject(ShopFragment shopFragment);

    CartActivity inject(CartActivity cartActivity);
    ProductDetailActivity inject(ProductDetailActivity productDetailActivity);
    ShopDetailActivity inject(ShopDetailActivity shopDetailActivity);
    OrderActivity inject(OrderActivity orderActivity);
}
