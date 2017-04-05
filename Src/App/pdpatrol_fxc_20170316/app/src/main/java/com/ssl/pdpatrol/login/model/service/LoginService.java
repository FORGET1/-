package com.ssl.pdpatrol.login.model.service;

import com.ssl.pdpatrol.common.util.Const;
import com.ssl.pdpatrol.login.model.bean.LoginBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface LoginService {

    @GET(Const.LOGIN)
    Observable<LoginBean> login(@Query("userCode") String userCode, @Query("userPwd") String password);
}
