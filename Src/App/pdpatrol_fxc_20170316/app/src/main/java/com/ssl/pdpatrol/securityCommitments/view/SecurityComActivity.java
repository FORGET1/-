package com.ssl.pdpatrol.securityCommitments.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.GridHolder;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.ssl.pdpatrol.R;
import com.ssl.pdpatrol.common.BaseActivity;
import com.ssl.pdpatrol.common.BasePresenter;
import com.ssl.pdpatrol.securityCommitments.model.adapter.SimpleAdapter;
import com.ssl.pdpatrol.securityCommitments.presenter.SecurityComPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecurityComActivity extends BaseActivity<SecurityComPresenterImpl> implements SecurityComView {

    private Bitmap enterpriseBitmap;

    private Bitmap signBitmap;


    @BindView(R.id.camera_enetrprise)
    ImageView enterprisephoto;

    @BindView(R.id.camera_sign)
    ImageView signPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_commitments);

        enterpriseBitmap = null;
        signBitmap = null;

        ButterKnife.bind(this);
    }

    @Override
    protected SecurityComPresenterImpl createPresenter() {
        return new SecurityComPresenterImpl();
    }

    @OnClick({R.id.camera_enetrprise, R.id.camera_sign})
    void cameraBtnClick() {
        DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder(new GridHolder(2))
                .setHeader(R.layout.header)
                .setGravity(Gravity.CENTER)
                .setAdapter(new SimpleAdapter(SecurityComActivity.this))
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                        switch (position) {
                            case 0:
                                //调用相机拍照
                                mvpPresenter.takePhotoByCamera(view.getId());
                                break;
                            case 1:
                                //从相册中选取照相
                                mvpPresenter.takePhotoByAlbum(view.getId());
                                break;
                            default:
                                break;
                        }
                    }
                })
                .create();
        dialog.show();
    }

    @Override
    public void setEnterprisePublicity(Bitmap bitmap) {
        this.enterpriseBitmap = bitmap;
        enterprisephoto.setImageBitmap(bitmap);
    }

    @Override
    public void setSignatureOfPrincipal(Bitmap bitmap) {
        this.signBitmap = bitmap;
        signPhoto.setImageBitmap(bitmap);
    }
}
