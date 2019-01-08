package com.gtv.hanhee.testlayout.ui.contract;

import com.gtv.hanhee.testlayout.base.BaseContract;
import com.gtv.hanhee.testlayout.model.AddressInfo;

import java.util.List;

public interface UserInfoChooseContract {

    interface View extends BaseContract.BaseView {
        void showListAddressInfo(List<AddressInfo> addressInfoListResult);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getListAddressInfo(String accessToken);
    }
}
