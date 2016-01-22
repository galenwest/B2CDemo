package client.enterprise.b2c.presenter.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import client.enterprise.b2c.AppContext;
import client.enterprise.b2c.model.bean.bo.LoginBuffer;
import client.enterprise.b2c.model.bean.po.User;
import client.enterprise.b2c.model.interactor.LoginInteractor;
import client.enterprise.b2c.model.interactor.impl.LoginInterImpl;
import client.enterprise.b2c.presenter.LoginPer;
import client.enterprise.b2c.presenter.result.OnLoginFinishedListener;
import client.enterprise.b2c.ui.fragment.FragmentMine;
import client.enterprise.b2c.ui.view.LoginView;
import client.enterprise.b2c.util.NetWorkUtils;
import client.enterprise.b2c.util.ToastUtils;

/**
 * Created by raohoulin on 2016.1.15.
 */
public class LoginPerImpl implements LoginPer, OnLoginFinishedListener{

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPerImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInterImpl();
    }

    @Override
    public void validateCredentials(String username, String password) {
        if (!NetWorkUtils.isNetAvailable()) {
            ToastUtils.showToastInCenter("当前没有可用网络");
            return;
        }
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
    public void onNetWorkError() {
        loginView.showNetWorkError();
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
    public void onLoginSuccess(String statue, String responseString) {
        Gson gson = new Gson();
        final User user = gson.fromJson(responseString, new TypeToken<User>(){}.getType());
        AppContext.getInstance().saveUserInfo(user);

        loginView.showLoginSuccess();
        loginView.navigateToMine();
    }

    @Override
    public void onLoginError(String statue, String responseString) {
        switch (statue) {
            case LoginBuffer.PASSWORD_ERROR:
                loginView.showPasswordError();
                break;
            case LoginBuffer.ERROR_USERNAME_NOT_FOUND:
                loginView.showUsernameNotFoundError();
                break;
            default:
                break;
        }
    }

    @Override
    public void hideDialog() {
        loginView.hideProgress();
    }
}
