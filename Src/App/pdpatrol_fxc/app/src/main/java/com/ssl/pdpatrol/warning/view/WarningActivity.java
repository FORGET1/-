package com.ssl.pdpatrol.warning.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.ssl.pdpatrol.R;
import com.ssl.pdpatrol.common.BaseActivity;
import com.ssl.pdpatrol.main.view.MainActivity;
import com.ssl.pdpatrol.warning.presenter.WarningPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WarningActivity extends BaseActivity<WarningPresenterImpl> implements WarningView {

    private static final String TAG = "WarningActivity";

    @BindView(R.id.et_warning_title)
    EditText etWarningtitle;

    @BindView(R.id.et_warning_content)
    EditText etWarningContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning);

        ButterKnife.bind(this);
    }

    @Override
    protected WarningPresenterImpl createPresenter() {
        return new WarningPresenterImpl(this);
    }

    @OnClick(R.id.btn_warning_warning)
    void sendWarningBtnClick() {
        Log.d(TAG, "sendWarningBtnClick");
        mvpPresenter.sendWarning(etWarningtitle.getText().toString(), etWarningContent.getText().toString());
    }

    @Override
    public void openMainactivity() {
        Intent intent = new Intent(WarningActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
