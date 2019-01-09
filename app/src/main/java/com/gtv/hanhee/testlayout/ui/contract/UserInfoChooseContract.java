package com.gtv.hanhee.testlayout.ui.contract;

import com.gtv.hanhee.testlayout.base.BaseContract;
import com.gtv.hanhee.testlayout.model.AddressInfo;
import com.gtv.hanhee.testlayout.model.Message;

import java.util.List;

public interface UserInfoChooseContract {

    interface View extends BaseContract.BaseView {
        void showListAddressInfo(List<AddressInfo> addressInfoListResult);
        void successRemoveAddressInfo(Message message);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getListAddressInfo(String accessToken);
        void removeAddressInfo(String accessToken, String addressId);
    }
}
