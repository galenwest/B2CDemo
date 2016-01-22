package client.enterprise.b2c.presenter.impl;

import client.enterprise.b2c.model.bean.po.User;
import client.enterprise.b2c.model.interactor.MineInter;
import client.enterprise.b2c.model.interactor.impl.MineInterImpl;
import client.enterprise.b2c.presenter.MinePer;
import client.enterprise.b2c.presenter.result.OnGetMineFinishedListener;
import client.enterprise.b2c.ui.view.MineView;

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
    public void onPullUserDataSucceed(User user) {
        mineView.showUserInfo(user);
        mineView.hideProgress();
    }

    @Override
    public void onPullUserDataError() {
        mineView.showLoginHeadImg();
        mineView.hideProgress();
    }
}
