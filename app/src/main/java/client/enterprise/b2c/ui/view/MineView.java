package client.enterprise.b2c.ui.view;

import client.enterprise.b2c.model.bean.po.User;

/**
 * Created by raohoulin on 2016.1.21.
 */
public interface MineView {
    public void showProgress();

    public void hideProgress();

    public void showLoginHeadImg();

    public void showUserInfo(User user);


}
