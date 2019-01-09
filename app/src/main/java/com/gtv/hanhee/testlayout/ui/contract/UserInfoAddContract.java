package com.gtv.hanhee.testlayout.ui.contract;

import com.gtv.hanhee.testlayout.base.BaseContract;
import com.gtv.hanhee.testlayout.model.AddressInfo;
import com.gtv.hanhee.testlayout.model.Message;
import com.gtv.hanhee.testlayout.model.Product;

import java.util.List;

public interface UserInfoAddContract {

    interface View extends BaseContract.BaseView {
        void successAddAddressInfo(Message message);
        void successUpdateAddressInfo(Message message);
        void showAddressInfoById(AddressInfo addressInfoResult);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void addAddressInfo(String accessToken, String fullname, String phoneNumber, String email, String address);
        void getAddressInfoById(String accessToken, String addressId);
        void updateAddressInfo(String accessToken, String addressId, String fullname, String phoneNumber, String email, String address);
    }
}

