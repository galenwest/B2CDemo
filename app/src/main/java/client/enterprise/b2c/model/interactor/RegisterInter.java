package client.enterprise.b2c.model.interactor;

import client.enterprise.b2c.presenter.result.OnRegisterFinishedListener;

/**
 * Created by raohoulin on 2016.1.23.
 */
public interface RegisterInter {

    public void register(String phone, String password, String checkNum, OnRegisterFinishedListener onRegisterFinishedListener);
}
