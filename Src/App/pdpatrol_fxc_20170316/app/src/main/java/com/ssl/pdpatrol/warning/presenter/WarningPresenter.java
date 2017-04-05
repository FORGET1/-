package com.ssl.pdpatrol.warning.presenter;


public interface WarningPresenter {
    /**
     * 发送警报
     */
    void sendWarning(String warningTitle,String wariningContent);
}
