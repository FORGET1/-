package com.ssl.pdpatrol.main.presenter;

import android.util.Log;

import com.ssl.pdpatrol.common.BasePresenter;
import com.ssl.pdpatrol.main.model.MainModel;
import com.ssl.pdpatrol.main.model.MainModelImpl;

import com.ssl.pdpatrol.main.model.bean.WarningInfo;
import com.ssl.pdpatrol.main.view.MainView;

import rx.Subscriber;

public class MainPresenterImpl extends BasePresenter<MainView> implements MainPresenter {

    private static final String TAG = "MainPresenterImpl";

    private MainModel mainModel;

    public MainPresenterImpl(MainView mainView) {
        attachView(mainView);
        this.mainModel = new MainModelImpl();
    }

    @Override
    public void getLatestWarningInfo() {

        Log.d(TAG, "getLatestWarningInfo");

        addSubscription(mainModel.getLatestWarningInfo(), new Subscriber<WarningInfo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                Log.d(TAG, "getLatestWarningInfo----onError");

                mvpView.updateWarningInfo("获取失败", "获取失败");
                mvpView.onError("获取最新警报信息失败，请检查网络设置");
            }

            @Override
            public void onNext(WarningInfo warningInfo) {

                Log.d(TAG, "getLatestWarningInfo----onNext");

                mvpView.updateWarningInfo(warningInfo.getWarningTitle(), warningInfo.getWarningContent());
            }
        });
    }
}
