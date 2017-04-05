package com.ssl.pdpatrol.securityCommitments.presenter;

import android.graphics.Bitmap;

import com.ssl.pdpatrol.R;
import com.ssl.pdpatrol.common.BasePresenter;
import com.ssl.pdpatrol.securityCommitments.view.SecurityComView;


public class SecurityComPresenterImpl extends BasePresenter<SecurityComView> implements SecurityComPresenter {

    private Bitmap bitmap = null;

    @Override
    public void takePhotoByCamera(int id) {

        //TODO 完成 直接拍照 ----> 处理照片 ----> 照片保存本地 ----> 得到Bitmap


        setPhoto(id, bitmap);
    }

    @Override
    public void takePhotoByAlbum(int id) {

        //TODO 完成 相册中选取 ----> 处理照片 ---->照片保存本地 ----> 得到Bitmap


        setPhoto(id, bitmap);
    }

    /**
     * 设置照片
     */
    void setPhoto(int id, Bitmap bitmap) {
        switch (id) {
            case R.id.camera_enetrprise:
                mvpView.setEnterprisePublicity(bitmap);
                break;
            case R.id.camera_sign:
                mvpView.setSignatureOfPrincipal(bitmap);
                break;
            default:
                break;
        }
    }
}
