package com.ssl.pdpatrol.main.view;


import com.ssl.pdpatrol.common.MvpView;

public interface MainView extends MvpView {
    /**
     * 更新警报信息
     */
    void updateWarningInfo(String warningTitle, String warningContent);
}
