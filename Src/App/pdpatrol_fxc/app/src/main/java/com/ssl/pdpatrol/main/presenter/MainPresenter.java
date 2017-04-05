package com.ssl.pdpatrol.main.presenter;

import com.ssl.pdpatrol.main.model.bean.WarningInfo;

import rx.Observable;

public interface MainPresenter {

    /**
     * 获取最新警报信息
     */
    void getLatestWarningInfo();

}
