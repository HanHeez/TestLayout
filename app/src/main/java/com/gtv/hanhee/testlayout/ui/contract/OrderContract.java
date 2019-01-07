package com.gtv.hanhee.testlayout.ui.contract;

import com.gtv.hanhee.testlayout.base.BaseContract;
import com.gtv.hanhee.testlayout.model.AddressInfo;
import com.gtv.hanhee.testlayout.model.Product;

import java.util.List;

public interface OrderContract {

    interface View extends BaseContract.BaseView {
        void showProduct(Product productResult);
        void showCartProduct(List<Product> productList);
        void showAddressInfoList(List<AddressInfo> addressInfoListResult);
        void successSendAddressInfo(String message);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getCartProduct();
        void getProduct(String accessToken, String id);
        void getAddressInfoList(String accessToken);
        void sendAddressInfo(String accessToken);
    }
}
