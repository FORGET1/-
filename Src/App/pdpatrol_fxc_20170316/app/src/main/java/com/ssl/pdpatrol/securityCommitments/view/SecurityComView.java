package com.ssl.pdpatrol.securityCommitments.view;

import android.graphics.Bitmap;

import com.ssl.pdpatrol.common.MvpView;

public interface SecurityComView extends MvpView {
    /**
     * 设置企业公示图片
     */
    void setEnterprisePublicity(Bitmap bitmap);

    /**
     * 设置负责人签字图片
     */
    void setSignatureOfPrincipal(Bitmap bitmap);
}
