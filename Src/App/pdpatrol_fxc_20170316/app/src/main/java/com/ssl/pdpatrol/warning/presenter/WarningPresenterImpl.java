package com.ssl.pdpatrol.warning.presenter;

import com.ssl.pdpatrol.common.BasePresenter;
import com.ssl.pdpatrol.common.ResultBean;
import com.ssl.pdpatrol.warning.model.WarningModel;
import com.ssl.pdpatrol.warning.model.WarningModelImpl;
import com.ssl.pdpatrol.warning.view.WarningView;

import rx.Subscriber;

public class WarningPresenterImpl extends BasePresenter<WarningView> implements WarningPresenter {

    private WarningModel warningModel;

    public WarningPresenterImpl(WarningView warningView) {
        attachView(warningView);
        this.warningModel = new WarningModelImpl();
    }

    @Override
    public void sendWarning(String warningTitle, String wariningContent) {
        if (warningTitle == null || warningTitle.isEmpty()) {
            mvpView.onError("警报标题为空");
            return;
        }
        if (wariningContent == null || wariningContent.isEmpty()) {
            mvpView.onError("警报内容为空");
            return;
        }

        mvpView.showLoading();

        addSubscription(warningModel.sendWarning(warningTitle, wariningContent), new Subscriber<ResultBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mvpView.hideLoading();
                mvpView.onError("发送失败");
            }

            @Override
            public void onNext(ResultBean resultBean) {
                mvpView.hideLoading();
                mvpView.onError("发送成功");
                mvpView.openMainactivity();
            }
        });
    }
}
