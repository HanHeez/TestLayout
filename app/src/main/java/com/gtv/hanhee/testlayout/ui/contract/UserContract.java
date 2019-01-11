package com.gtv.hanhee.testlayout.ui.contract;

import com.gtv.hanhee.testlayout.base.BaseContract;
import com.gtv.hanhee.testlayout.model.CategoryService;
import com.gtv.hanhee.testlayout.model.UserSetting;

import java.util.List;

public interface UserContract {
    interface View extends BaseContract.BaseView {
        void showUserSettingList(List<UserSetting> userSettingListResult);
        void showUserServiceList(List<CategoryService> userServiceListResult);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getUserSettingList(String accessToken);
        void getUserServiceList(String accessToken);
    }
}
