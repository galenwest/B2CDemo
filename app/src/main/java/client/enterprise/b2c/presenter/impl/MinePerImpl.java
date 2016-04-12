package client.enterprise.b2c.presenter.impl;

import client.enterprise.b2c.AppContext;
import client.enterprise.b2c.model.bean.po.User;
import client.enterprise.b2c.model.interactor.MineInter;
import client.enterprise.b2c.model.interactor.impl.MineInterImpl;
import client.enterprise.b2c.presenter.MinePer;
import client.enterprise.b2c.presenter.result.OnGetMineFinishedListener;
import client.enterprise.b2c.ui.view.MineView;
import client.enterprise.b2c.util.NetWorkUtils;

/**
 * Created by raohoulin on 2016.1.21.
 */
public class MinePerImpl implements MinePer, OnGetMineFinishedListener {

    private MineView mineView;
    private MineInter mineInter;

    public MinePerImpl(MineView mineView) {
        this.mineView = mineView;
        mineInter = new MineInterImpl();
    }

    @Override
    public void pullUserData() {
        mineView.showProgress();
        mineInter.pullData(this);
    }

    @Override
    public void cleanUserData() {
        mineView.showProgress();
        mineInter.loginOut(AppContext.getInstance().getSignature(), this);
    }

    @Override
    public void onPullUserDataSucceed(User user) {
        mineView.hideProgress();
        mineView.showUserInfo(user);
    }

    @Override
    public void onPullUserDataError() {
        mineView.showLoginHeadImg();
        mineView.hideProgress();
    }

    @Override
    public void onLoginOutSucceed() {
        if (!NetWorkUtils.isNetAvailable()) {
            AppContext.getInstance().showConntctError();
            return;
        }
        mineView.showLoginHeadImg();
        mineView.hideProgress();
    }

    @Override
    public void onLoginOutfailed() {
        mineView.showLoginHeadImg();
        mineView.hideProgress();
    }
}
