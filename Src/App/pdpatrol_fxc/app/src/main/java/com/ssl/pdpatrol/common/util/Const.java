package com.ssl.pdpatrol.common.util;

import com.ssl.pdpatrol.login.model.bean.User;


public class Const {

    private User user;//当前登录用户

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public static final String SERVER = "http://101.200.85.96:8090/patrol/";

    public static final String LOGIN = "applogin.do";
    public static final String GET_LATEST_WARNING = "warning/appGetWarningTitle.do";//获取警报信息
    public static final String SEND_WARNING = "warning/appAddWarning.do";//报警

}
