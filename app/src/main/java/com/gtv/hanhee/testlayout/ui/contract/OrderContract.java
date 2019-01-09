package com.gtv.hanhee.testlayout.ui.contract;

import com.gtv.hanhee.testlayout.base.BaseContract;
import com.gtv.hanhee.testlayout.model.AddressInfo;
import com.gtv.hanhee.testlayout.model.Message;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.ProductSection;

import java.util.List;

import io.reactivex.Observable;

public interface OrderContract {

    interface View extends BaseContract.BaseView {
        void showProduct(Product productResult);
        void showCartProduct(List<Product> productList);
        void showListAddressInfo(List<AddressInfo> addressInfoListResult);
        void successOrderAll(Message message);
        void successAddProductToCart(Message message);
        void successRemoveAllProductOnCart(Message message);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getCartProduct();
        void getProduct(String accessToken, String id);
        void getListAddressInfo(String accessToken);
        void orderAll(String accessToken, String fullName, String phoneNumber, String email, String address);
        void addProductToCart(String accessToken, List<ProductSection> productSectionList);
        void removeAllProductOnCart(String accessToken);
    }
}
