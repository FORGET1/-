package com.ssl.pdpatrol.common;


public interface MvpView {

    void showLoading();

    void hideLoading();

    void onError(String message);

    boolean isNetworkConnected();

}
