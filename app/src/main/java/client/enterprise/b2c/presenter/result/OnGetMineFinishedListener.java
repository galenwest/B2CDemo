package client.enterprise.b2c.presenter.result;

import client.enterprise.b2c.model.bean.po.User;

/**
 * Created by raohoulin on 2016.1.21.
 */
public interface OnGetMineFinishedListener {

    public void onPullUserDataSucceed(User user);

    public void onPullUserDataError();
}
