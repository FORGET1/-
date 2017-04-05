package com.ssl.pdpatrol.login.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import com.ssl.pdpatrol.R;
import com.ssl.pdpatrol.common.BaseActivity;
import com.ssl.pdpatrol.login.presenter.LoginPresenterImpl;
import com.ssl.pdpatrol.login.view.LoginView;
import com.ssl.pdpatrol.main.view.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenterImpl> implements LoginView {

    @BindView(R.id.et_login_username)
    EditText userName;

    @BindView(R.id.et_login_passwd)
    EditText password;

    @BindView(R.id.cb_login_remember_pwd)
    CheckBox rememberPwdCb;

    @BindView(R.id.cb_login_auto_login)
    CheckBox autoLoginCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        initAutoLoginSetting(this);
    }

    @Override
    protected LoginPresenterImpl createPresenter() {
        return new LoginPresenterImpl(this);
    }

    @OnClick(R.id.btn_login_login)
    void loginBtnClick() {
        mvpPresenter.login(userName.getText().toString(), password.getText().toString());
    }

    @OnCheckedChanged(R.id.cb_login_auto_login)
    void autologinIsCheck(boolean checked) {
        if (checked)
            rememberPwdCb.setChecked(true);
    }

    @OnCheckedChanged(R.id.cb_login_remember_pwd)
    void rememberIsCheck(boolean checked) {
        if (!checked)
            autoLoginCb.setChecked(false);
    }

    /**
     * 跳转到主页面
     */
    @Override
    public void turnToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 设置自动登录
     */
    public void initAutoLoginSetting(Context context) {
        //设置自动记住账号
        userName.setText(mvpPresenter.getAutoLoginUserName(context));
        mvpPresenter.autoLoginSetting(this);
    }

    /**
     * 选择保存密码
     */
    @Override
    public void setAutoLoginPassword(String pwd) {
        password.setText(pwd);
        rememberPwdCb.setChecked(true);
    }

    /**
     * 选择了自动登录
     */
    @Override
    public void setAutoLogin(String userName, String pwd) {
        autoLoginCb.setChecked(true);
        mvpPresenter.login(userName, pwd);
    }

    /**
     * 返回记住密码状态
     */
    @Override
    public Boolean rememberIsCheck() {
        return rememberPwdCb.isChecked();
    }

    /**
     * 返回自动登录状态
     */
    @Override
    public Boolean autoLoginIsCheck() {
        return autoLoginCb.isChecked();
    }
}