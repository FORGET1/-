package com.ssl.pdpatrol.securityCommitments.presenter;

public interface SecurityComPresenter {
    /**
     * 调用相机拍照
     */
    void takePhotoByCamera(int id);

    /**
     * 从相册中选择照片
     */
    void takePhotoByAlbum(int id);

}
