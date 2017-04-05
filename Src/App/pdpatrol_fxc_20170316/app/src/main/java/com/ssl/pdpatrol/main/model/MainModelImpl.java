package com.ssl.pdpatrol.main.model;


import com.ssl.pdpatrol.common.util.Const;
import com.ssl.pdpatrol.main.model.bean.WarningInfo;
import com.ssl.pdpatrol.main.model.service.LatestWarningInfoService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class MainModelImpl implements MainModel {

    Observable<WarningInfo> warningInfoObservable = null;

    @Override
    public Observable<WarningInfo> getLatestWarningInfo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        LatestWarningInfoService latestWarningInfoService = retrofit.create(LatestWarningInfoService.class);

        warningInfoObservable = latestWarningInfoService.getLatestWarningInfo();

        return warningInfoObservable;
    }
}
