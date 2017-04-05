package com.ssl.pdpatrol.warning.model;


import com.ssl.pdpatrol.common.ResultBean;

import rx.Observable;

public interface WarningModel {

    /**
     * 发送警报
     */
    Observable<ResultBean> sendWarning(String warningTitle, String warningContent);
}
