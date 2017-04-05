package com.ssl.pdpatrol.warning.model;

import com.ssl.pdpatrol.common.ResultBean;
import com.ssl.pdpatrol.common.util.Const;
import com.ssl.pdpatrol.warning.model.service.SendWarningService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class WarningModelImpl implements WarningModel {

    Observable<ResultBean> resultBeanObservable = null;

    @Override
    public Observable<ResultBean> sendWarning(String warningTitle, String warningContent) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        SendWarningService sendWarningService = retrofit.create(SendWarningService.class);

        resultBeanObservable = sendWarningService.sendWarning(warningTitle, Const.userCode, warningContent);

        return resultBeanObservable;
    }
}
