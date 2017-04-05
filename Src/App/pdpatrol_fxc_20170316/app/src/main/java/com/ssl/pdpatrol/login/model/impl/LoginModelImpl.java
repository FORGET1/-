package com.ssl.pdpatrol.login.model.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.ssl.pdpatrol.common.util.Const;
import com.ssl.pdpatrol.login.model.LoginModel;
import com.ssl.pdpatrol.login.model.bean.LoginBean;
import com.ssl.pdpatrol.login.model.service.LoginService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class LoginModelImpl implements LoginModel {

    Observable<LoginBean> loginBeanObservable = null;
    private SharedPreferences sp;

    @Override
    public Observable<LoginBean> login(final String userName, final String password) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        LoginService loginService = retrofit.create(LoginService.class);

        loginBeanObservable = loginService.login(userName, password);

        return loginBeanObservable;
    }

    @Override
    public String getAutoLoginUserName(Context context) {
        sp = context.getSharedPreferences("userInfo", 0);
        String userCode = sp.getString("userCode", "");    // 获取保存的用户名，如果没有，则默认为空
        return userCode;
    }

    @Override
    public String getAutoLoginPwd(Context context) {
        sp = context.getSharedPreferences("userInfo", 0);
        String userPwd = sp.getString("userPwd", "");    // 获取保存的用户名，如果没有，则默认为空
        return userPwd;
    }

    @Override
    public Boolean getAutoLogin(Context context) {
        sp = context.getSharedPreferences("userInfo", 0);
        return sp.getBoolean("autoLogin", false);
    }

    @Override
    public Boolean getRemember(Context context) {
        sp = context.getSharedPreferences("userInfo", 0);
        return sp.getBoolean("remember", false);
    }

    @Override
    public void autoLoginIfLogout() {
        // 保存用户名和密码
        SharedPreferences.Editor editor = sp.edit();
        // 取消记住密码
        editor.putBoolean("remember", false);
        // 取消自动登录
        editor.putBoolean("autoLogin", false);
        editor.apply();
    }

    @Override
    public void storeUserNameAndUserPwd(String name, String pwd, Boolean rememberIsCheck, Boolean autoLoginIsCheck) {
        // 保存用户名和密码
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("userCode", name);
        editor.putString("userPwd", pwd);
        // 是否记住密码
        editor.putBoolean("remember", rememberIsCheck);
        // 是否自动登录
        editor.putBoolean("autoLogin", autoLoginIsCheck);
        editor.apply();
    }
}
