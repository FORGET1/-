package com.ssl.pdpatrol.login.view;

import com.ssl.pdpatrol.common.MvpView;

public interface LoginView extends MvpView {

    /**
     * 跳转到主页面
     */
    void turnToMainActivity();

    /**
     * 显示自动保存的密码
     */
    void setAutoLoginPassword(String pwd);

    /**
     * 设置自动登录
     */
    void setAutoLogin(String name, String pwd);

    /**
     * 获取记住密码checkbox的状态
     */
    Boolean rememberIsCheck();

    /**
     * 获取自动登录CheckBox的状态
     */
    Boolean autoLoginIsCheck();

}
