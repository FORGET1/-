package com.ssl.pdpatrol.login.presenter;

import android.content.Context;

import com.ssl.pdpatrol.common.BasePresenter;
import com.ssl.pdpatrol.common.util.MD5Util;
import com.ssl.pdpatrol.login.model.LoginModel;
import com.ssl.pdpatrol.login.model.bean.LoginBean;
import com.ssl.pdpatrol.login.model.impl.LoginModelImpl;
import com.ssl.pdpatrol.login.presenter.LoginPresenter;
import com.ssl.pdpatrol.login.view.LoginView;
import com.ssl.pdpatrol.login.view.activity.LoginActivity;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class LoginPresenterImpl extends BasePresenter<LoginView> implements LoginPresenter {

    private LoginModel loginModel;

    //    Boolean logout = mvpView.getIntent().getBooleanExtra("logout", false);
    //TODO 修改退出登录配置
    Boolean logout = false;

    public LoginPresenterImpl(LoginView loginView) {
        attachView(loginView);
        this.loginModel = new LoginModelImpl();
    }

    @Override
    public void login(final String username, final String password) {

        if (username == null || username.isEmpty()) {
            mvpView.onError("用户名为空");
            return;
        }
        if (password == null || password.isEmpty()) {
            mvpView.onError("密码为空");
            return;
        }

        mvpView.showLoading();

        addSubscription(
                //observable
                loginModel.login(username, password)
                        .flatMap(new Func1<LoginBean, Observable<String>>() {
                            @Override
                            public Observable<String> call(LoginBean loginBean) {
                                String loginStatus;

                                if (loginBean.getUserPwd().equals(MD5Util.md5Code(loginBean.getSalt(), password))) {
                                    loginStatus = "success";
                                } else {
                                    loginStatus = "fail";
                                }
                                Observable observable = Observable.just(loginStatus);
                                return observable;
                            }
                        }),
                //subscriber
                new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mvpView.hideLoading();
                        mvpView.onError("登录失败,请检查网络设置");
                    }

                    @Override
                    public void onNext(String s) {
                        mvpView.hideLoading();
                        if (s.equals("success")) {

                            loginModel.storeUserNameAndUserPwd(username, password, mvpView.rememberIsCheck(), mvpView.autoLoginIsCheck());

                            mvpView.turnToMainActivity();

                        } else if (s.equals("fail")) {
                            mvpView.onError("登录失败，请检查用户名或密码是否正确");
                        }
                    }
                });
    }

    @Override
    public String getAutoLoginUserName(Context context) {
        return loginModel.getAutoLoginUserName(context);
    }

    @Override
    public String getAutoLoginPwd(Context context) {
        return loginModel.getAutoLoginPwd(context);
    }

    @Override
    public void autoLoginSetting(Context context) {
        if (!logout) {
            // 之前选择了保存密码
            if (loginModel.getRemember(context)) {
                mvpView.setAutoLoginPassword(loginModel.getAutoLoginPwd(context));
            }
            // 之前选择了自动登录
            if (loginModel.getAutoLogin(context)) {
                mvpView.setAutoLogin(loginModel.getAutoLoginUserName(context), loginModel.getAutoLoginPwd(context));
            }
        } else {
            loginModel.autoLoginIfLogout();
        }
    }
}

