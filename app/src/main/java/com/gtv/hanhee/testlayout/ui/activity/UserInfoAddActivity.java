package com.gtv.hanhee.testlayout.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.BaseActivity;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.AddressInfo;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.ui.adapter.ShopAdapter;
import com.gtv.hanhee.testlayout.ui.contract.ProfileUserContract;
import com.gtv.hanhee.testlayout.ui.contract.UserInfoAddContract;
import com.gtv.hanhee.testlayout.ui.presenter.ProfileUserPresenter;
import com.gtv.hanhee.testlayout.ui.presenter.UserInfoAddPresenter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class UserInfoAddActivity extends BaseActivity implements UserInfoAddContract.View {
    private static final String ADDRESS_ID = "addressId";
    private String addressId;
    private boolean isError= true;

    @BindView(R.id.edtAddress)
    EditText edtAddress;
    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtPhone)
    EditText edtPhone;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.inputEdtName)
    TextInputLayout inputEdtName;
    @BindView(R.id.inputEdtAddress)
    TextInputLayout inputEdtAddress;
    @BindView(R.id.inputEdtPhone)
    TextInputLayout inputEdtPhone;
    @BindView(R.id.inputEdtEmail)
    TextInputLayout inputEdtEmail;

    @BindView(R.id.btnRemoveName)
    ImageView btnRemoveName;
    @BindView(R.id.btnRemoveAddress)
    ImageView btnRemoveAddress;
    @BindView(R.id.btnRemovePhone)
    ImageView btnRemovePhone;
    @BindView(R.id.btnRemoveEmail)
    ImageView btnRemoveEmail;
    @BindView(R.id.cbDefault)
    CheckBox cbDefault;
    @BindView(R.id.txtTip)
    TextView txtTip;
    @BindView(R.id.rootView)
    LinearLayout rootView;


    @Inject
    UserInfoAddPresenter mPresenter;
    private final MyHandler mHandler = new MyHandler(this);
    private String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private Pattern emailPattern = Pattern.compile(regex);

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_info_add;
    }

    public static void startActivity(Context context, String addressId) {
        Intent intent = new Intent(context, UserInfoAddActivity.class);
        intent.putExtra(ADDRESS_ID, addressId);
        context.startActivity(intent);
    }


    @Override
    protected void initDataGetFromIntent(Bundle savedInstanceState) {
        super.initDataGetFromIntent(savedInstanceState);
        if (savedInstanceState != null) {
            addressId = savedInstanceState.getString(ADDRESS_ID);
        } else {
            addressId = getIntent().getStringExtra(ADDRESS_ID);
        }
    }

    @Override
    public void initToolBar() {

    }


    @Override
    public void initDatas() {
        activityComponent().inject(this);
        mPresenter.attachView(this);
        showTip = false;
//        mPresenter.getCartProduct();
    }


    @Override
    protected void onRefreshing() {

    }


    @Override
    public void configViews() {
        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkName();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkAddress();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkPhone();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkEmail();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


//        edtName.setOnFocusChangeListener(this);
//        edtAddress.setOnFocusChangeListener(this);
//        edtPhone.setOnFocusChangeListener(this);

        if (!addressId.equals("")) {
            mPresenter.getAddressInfoById(token, addressId);
        }

    }

    @OnClick(R.id.btnBack)
    public void onBack() {
        onBackPressed();
    }

    @OnClick(R.id.btnSave)
    public void saveUserInfo() {
        isError = false;
        checkName();
        checkAddress();
        checkPhone();
        if (isError) {
            showTipViewRepeat("Vui lòng nhập thông tin chính xác", txtTip, mHandler);
        } else {
            if (addressId.equals("")) {
                mPresenter.addAddressInfo(token, edtName.getText().toString(), edtPhone.getText().toString(), edtEmail.getText().toString(), edtAddress.getText().toString());
            } else {
                mPresenter.updateAddressInfo(token, addressId, edtName.getText().toString(), edtPhone.getText().toString(), edtEmail.getText().toString(), edtAddress.getText().toString());
            }
        }
    }

    @OnClick(R.id.btnRemoveAddress)
    public void removeAddress() {
        edtAddress.requestFocus();
        isError = true;
        edtAddress.setText("");
    }

    @OnClick(R.id.btnRemoveEmail)
    public void removeEmail() {
        edtEmail.requestFocus();
        isError = true;
        edtEmail.setText("");
    }

    @OnClick(R.id.btnRemovePhone)
    public void removePhone() {
        edtPhone.requestFocus();
        isError = true;
        edtPhone.setText("");
    }
    @OnClick(R.id.btnRemoveName)
    public void removeName() {
        edtName.requestFocus();
        isError = true;
        edtName.setText("");
    }


    private void checkEmail() {
        String email = edtEmail.getText().toString();
        Matcher emailMatcher = emailPattern.matcher(email);

        if (email.trim().equals("") || email.equals(null)) {
            inputEdtEmail.setErrorEnabled(true);
            inputEdtEmail.setError("Bạn không thể để trống.");
            isError = true;
        } else if (!emailMatcher.matches()){
            inputEdtEmail.setErrorEnabled(true);
            inputEdtEmail.setError("Địa chỉ email chưa chính xác");
            isError = true;
        } else {
            inputEdtEmail.setErrorEnabled(false);
            inputEdtEmail.setError("");
        }
    }

    private void checkPhone() {
        String phone = edtPhone.getText().toString();
        if (phone.trim().equals("") || phone.equals(null)) {
            inputEdtPhone.setErrorEnabled(true);
            inputEdtPhone.setError("Bạn không thể để trống.");
            isError = true;
        } else if (phone.length()!=10){
            inputEdtPhone.setErrorEnabled(true);
            inputEdtPhone.setError("Số điện thoại chính xác cầ̀n 10 số.");
            isError = true;
        } else {
            inputEdtPhone.setErrorEnabled(false);
            inputEdtPhone.setError("");
        }
    }

    private void checkName() {
        String name = edtName.getText().toString();
        if (name.trim().equals("") || name.equals(null)) {
            inputEdtName.setErrorEnabled(true);
            inputEdtName.setError("Bạn không thể để trống.");
            isError = true;
        } else {
            inputEdtName.setErrorEnabled(false);
            inputEdtName.setError("");
        }
    }

    private void checkAddress() {
        String address = edtAddress.getText().toString();
        if (address.trim().equals("") || address.equals(null)) {
            inputEdtAddress.setErrorEnabled(true);
            inputEdtAddress.setError("Bạn không thể để trống.");
            isError = true;
        } else if (address.length()<5 || address.length()>350){
            inputEdtAddress.setErrorEnabled(true);
            inputEdtAddress.setError("Chiều dài địa chỉ nên từ 5 đến 350 ký tự.");
            isError = true;
        } else {
            inputEdtAddress.setErrorEnabled(false);
            inputEdtAddress.setError("");
        }
    }


    @Override
    public void showError() {
        showTipViewRepeat("Số điện thoại không hợp lệ", txtTip, mHandler);
    }

    @Override
    public void complete() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void successAddAddressInfo(com.gtv.hanhee.testlayout.model.Message message) {
        finish();
    }

    @Override
    public void successUpdateAddressInfo(com.gtv.hanhee.testlayout.model.Message message) {
        finish();
    }

    @Override
    public void showAddressInfoById(AddressInfo addressInfoResult) {
        edtPhone.setText(addressInfoResult.getPhoneNumber());
        edtAddress.setText(addressInfoResult.getAddress());
        edtName.setText(addressInfoResult.getFullName());
        edtEmail.setText(addressInfoResult.getEmail());
    }

    @Override
    public void onSkeletonViewClick(View view) {

    }

    private static class MyHandler extends Handler {
        private final WeakReference<UserInfoAddActivity> mActivity;

        public MyHandler(UserInfoAddActivity activity) {
            mActivity = new WeakReference<UserInfoAddActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            UserInfoAddActivity activity = mActivity.get();
            if (activity != null) {
                // ...
            }
        }
    }
}



