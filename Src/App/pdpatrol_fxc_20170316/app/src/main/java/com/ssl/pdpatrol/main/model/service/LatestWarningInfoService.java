package com.ssl.pdpatrol.main.model.service;


import com.ssl.pdpatrol.common.util.Const;
import com.ssl.pdpatrol.main.model.bean.WarningInfo;

import retrofit2.http.GET;
import rx.Observable;

public interface LatestWarningInfoService {

    @GET(Const.GET_LATEST_WARNING)
    Observable<WarningInfo> getLatestWarningInfo();
}
