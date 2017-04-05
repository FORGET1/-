package com.ssl.pdpatrol.warning.model.service;


import com.ssl.pdpatrol.common.ResultBean;
import com.ssl.pdpatrol.common.util.Const;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface SendWarningService {

    @GET(Const.SEND_WARNING)
    Observable<ResultBean> sendWarning(@Query("warningTitle") String warningTitle, @Query("warningPerson") String userCode,@Query("warningContent") String warningContnte);
}
