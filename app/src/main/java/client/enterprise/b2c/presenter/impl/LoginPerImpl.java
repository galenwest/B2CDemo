package client.enterprise.b2c.presenter.impl;

import client.enterprise.b2c.model.interactor.LoginInteractor;
import client.enterprise.b2c.model.interactor.impl.LoginInterImpl;
import client.enterprise.b2c.presenter.LoginPer;
import client.enterprise.b2c.presenter.result.OnLoginFinishedListener;
import client.enterprise.b2c.ui.view.LoginView;

/**
 * Created by raohoulin on 2016.1.15.
 */
public class LoginPerImpl implements LoginPer, OnLoginFinishedListener {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPerImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInterImpl();
    }

    @Override
    public void validateCredentials(String username, String password) {
        loginView.showProgress();
        loginInteractor.login(username, password, this);
    }

    @Override
    public void onUsernameEmpty() {
        loginView.setUsernameEmpty();
        loginView.hideProgress();
    }

    @Override
    public void onPasswordEmpty() {
        loginView.setPasswordEmpty();
        loginView.hideProgress();
    }

    @Override
    public void onUsernameOrPasswordError() {
        loginView.showUsernameOrPasswordError();
    }

    @Override
    public void onServerError() {
        loginView.showServerError();
    }

    @Override
    public void onConntctTimeOutError() {
        loginView.showConntctTimeOutError();
    }

    @Override
    public void onResponseTimeOutError() {
        loginView.showResponseTimeOutError();
    }

    @Override
    public void onLoginError() {
        loginView.showLoginError();
    }

    @Override
    public void onLoginSuccess() {
        loginView.showLoginSuccess();
        loginView.navigateToMine();
    }
}
