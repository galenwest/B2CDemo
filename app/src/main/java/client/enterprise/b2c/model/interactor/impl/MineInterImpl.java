package client.enterprise.b2c.model.interactor.impl;

import client.enterprise.b2c.AppContext;
import client.enterprise.b2c.model.interactor.MineInter;
import client.enterprise.b2c.presenter.result.OnGetMineFinishedListener;

/**
 * Created by raohoulin on 2016.1.21.
 */
public class MineInterImpl implements MineInter {

    @Override
    public void pullData(OnGetMineFinishedListener onGetMineFinishedListener) {
        if (AppContext.getInstance().isLogin()) {
            onGetMineFinishedListener.onPullUserDataSucceed(AppContext.getInstance().getLoginUser());
        } else {
            onGetMineFinishedListener.onPullUserDataError();
        }
    }
}
