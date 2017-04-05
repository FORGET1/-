package com.ssl.pdpatrol.login.model;


import android.content.Context;

import com.ssl.pdpatrol.login.model.bean.LoginBean;

import rx.Observable;

public interface LoginModel {

    /**
     * 登录
     */
    Observable<LoginBean> login(String userName, String password);

    /**
     * 获取自动保存的用户名
     */
    String getAutoLoginUserName(Context context);

    /**
     * 获取自动保存的密码
     */
    String getAutoLoginPwd(Context context);

    /**
     * 是否选择保存密码
     */
    Boolean getRemember(Context context);


    /**
     * 是否选择自动登录
     */
    Boolean getAutoLogin(Context context);


    /**
     * 退出登录后的自动登录设置
     */
    void autoLoginIfLogout();

    /**
     * 登录成功以后保存用户名密码
     */
    void storeUserNameAndUserPwd(String name, String pwd, Boolean rememberIsCheck, Boolean autoLoginIsCheck);
}
