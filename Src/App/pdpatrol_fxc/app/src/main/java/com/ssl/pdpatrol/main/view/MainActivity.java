package com.ssl.pdpatrol.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ssl.pdpatrol.R;
import com.ssl.pdpatrol.common.BaseActivity;
import com.ssl.pdpatrol.main.presenter.MainPresenterImpl;
import com.ssl.pdpatrol.warning.view.WarningActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenterImpl> implements MainView {

    @BindView(R.id.main_warning_title)
    TextView tvWarningTitle;

    @BindView(R.id.main_warining_content)
    TextView tvWarningContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //获取最新的警报
        mvpPresenter.getLatestWarningInfo();
    }


//    @OnClick(R.id.iBtn_main_message_manage)
//    void msgBtnClick() {
//
//    }
//
//
//    @OnClick(R.id.iBtn_main_file_manage)
//    void fileManagerBtnClick() {
//        Intent intent = new Intent(MainActivity.class,);
//        startActivity(intent);
//    }
//
//    @OnClick(R.id.iBtn_main_safety_check)
//    void safeCheckBtnClick() {
//        Intent intent = new Intent(MainActivity.class,);
//        startActivity(intent);
//    }
//
//    @OnClick(R.id.iBtn_main_security_commitments)
//    void securityBtnClick() {
//        Intent intent = new Intent(MainActivity.class,);
//        startActivity(intent);
//    }
//
//    @OnClick(R.id.iBtn_main_license_manage)
//    void licenseManageBtnClick() {
//        Intent intent = new Intent(MainActivity.class,);
//        startActivity(intent);
//    }
//
//    @OnClick(R.id.iBtn_main_record_check)
//    void recordCheckBtnClick() {
//        Intent intent = new Intent(MainActivity.class,);
//        startActivity(intent);
//    }
//
//    @OnClick(R.id.iBtn_main_emergency_rescue)
//    void emergencyRescueBtnClick() {
//        Intent intent = new Intent(MainActivity.class,);
//        startActivity(intent);
//    }

    @OnClick(R.id.iBtn_main_alarm)
    void alarmBtnClick() {
        Intent intent = new Intent(MainActivity.this, WarningActivity.class);
        startActivity(intent);
    }

//    @OnClick(R.id.iBtn_main_user_setting)
//    void userBtnClick() {
//        Intent intent = new Intent(MainActivity.class,);
//        startActivity(intent);
//    }

    @Override
    protected MainPresenterImpl createPresenter() {
        return new MainPresenterImpl(this);
    }

    @Override
    public void updateWarningInfo(String warningTitle, String warningContent) {
        //更新警报标题
        tvWarningTitle.setText(warningTitle);
        //更新警报内容
        tvWarningContent.setText(warningContent);
    }
}
