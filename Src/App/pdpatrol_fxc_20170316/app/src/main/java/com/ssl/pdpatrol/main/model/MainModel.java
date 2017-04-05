package com.ssl.pdpatrol.main.model;


import com.ssl.pdpatrol.main.model.bean.WarningInfo;

import rx.Observable;

public interface MainModel {
    /**
     * 获取最新警报信息
     */
    Observable<WarningInfo> getLatestWarningInfo();
}
