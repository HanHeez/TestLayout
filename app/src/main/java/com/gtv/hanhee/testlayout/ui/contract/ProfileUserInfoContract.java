package com.gtv.hanhee.testlayout.ui.contract;

import com.gtv.hanhee.testlayout.base.BaseContract;
import com.gtv.hanhee.testlayout.model.User;

public interface ProfileUserInfoContract {
    interface View extends BaseContract.BaseView {
        void showUserInfoByid(User userResult);
        void showUserInfoMom(User userResult);

    }
    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getUserInfoById(String accessToken, String userId);
        void getUserInfoMom(String accessToken);
    }
}
