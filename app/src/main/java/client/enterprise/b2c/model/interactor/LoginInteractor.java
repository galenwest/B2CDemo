package client.enterprise.b2c.model.interactor;

import client.enterprise.b2c.presenter.result.OnLoginFinishedListener;

public interface LoginInteractor {

    public void login(String username, String password, OnLoginFinishedListener listener);

}
