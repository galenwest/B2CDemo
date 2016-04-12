package client.enterprise.b2c.model.interactor;

import client.enterprise.b2c.presenter.result.OnGetMineFinishedListener;

/**
 * Created by raohoulin on 2016.1.21.
 */
public interface MineInter {

    public void pullData(OnGetMineFinishedListener onGetMineFinishedListener);

    public void loginOut(String signature, OnGetMineFinishedListener onGetMineFinishedListener);
}
