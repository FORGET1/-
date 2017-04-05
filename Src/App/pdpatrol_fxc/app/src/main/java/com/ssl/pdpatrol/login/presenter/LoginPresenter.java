package com.ssl.pdpatrol.login.presenter;

import android.content.Context;

public interface LoginPresenter {

    /**
     * 登录操作
     */
    void login(String userName, String password);

    /**
     * 获取自动保存的用户名和密码
     */
    String getAutoLoginUserName(Context context);


    /**
     * 获取自动保存的密码
     */
    String getAutoLoginPwd(Context context);

    /**
     * 自动登录配置
     */
    void autoLoginSetting(Context context);
}
