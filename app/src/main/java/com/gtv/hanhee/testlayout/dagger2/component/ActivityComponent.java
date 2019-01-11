package com.gtv.hanhee.testlayout.dagger2.component;


import com.gtv.hanhee.testlayout.dagger2.PerActivity;
import com.gtv.hanhee.testlayout.dagger2.module.ActivityModule;
import com.gtv.hanhee.testlayout.ui.activity.CartActivity;
import com.gtv.hanhee.testlayout.ui.activity.HomeRemindActivity;
import com.gtv.hanhee.testlayout.ui.activity.OrderActivity;
import com.gtv.hanhee.testlayout.ui.activity.OrderListActivity;
import com.gtv.hanhee.testlayout.ui.activity.ProductDetailActivity;
import com.gtv.hanhee.testlayout.ui.activity.ProfileUserActivity;
import com.gtv.hanhee.testlayout.ui.activity.ShopDetailActivity;
import com.gtv.hanhee.testlayout.ui.activity.UserInfoAddActivity;
import com.gtv.hanhee.testlayout.ui.activity.UserInfoChooseActivity;
import com.gtv.hanhee.testlayout.ui.fragment.OrderListFragment;
import com.gtv.hanhee.testlayout.ui.fragment.ProfileUserInfoFragment;
import com.gtv.hanhee.testlayout.ui.fragment.ShopCategoryFragment;
import com.gtv.hanhee.testlayout.ui.fragment.ShopFragment;
import com.gtv.hanhee.testlayout.ui.fragment.ShopHomeFragment;
import com.gtv.hanhee.testlayout.ui.fragment.UserFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    ShopHomeFragment inject(ShopHomeFragment shopHomeFragment);
    ShopCategoryFragment inject(ShopCategoryFragment shopCategoryFragment);
    ShopFragment inject(ShopFragment shopFragment);
    ProfileUserInfoFragment inject(ProfileUserInfoFragment profileUserInfoFragment);
<<<<<<< HEAD
    UserFragment inject(UserFragment userFragment);
=======
    OrderListFragment inject(OrderListFragment orderListFragment);
>>>>>>> 7fa0cc1c64ac34c699c73a4a1dc240ae813d0cdf

    HomeRemindActivity inject(HomeRemindActivity cartActivity);
    CartActivity inject(CartActivity cartActivity);
    ProductDetailActivity inject(ProductDetailActivity productDetailActivity);
    ShopDetailActivity inject(ShopDetailActivity shopDetailActivity);
    OrderActivity inject(OrderActivity orderActivity);
    ProfileUserActivity inject(ProfileUserActivity profileUserActivity);
    UserInfoChooseActivity inject(UserInfoChooseActivity userInfoChooseActivity);
    UserInfoAddActivity inject(UserInfoAddActivity userInfoAddActivity);
<<<<<<< HEAD

=======
    OrderListActivity inject(OrderListActivity orderListActivity);
>>>>>>> 7fa0cc1c64ac34c699c73a4a1dc240ae813d0cdf
}
