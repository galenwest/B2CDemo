package client.enterprise.b2c.presenter.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;

import client.enterprise.b2c.AppConfig;
import client.enterprise.b2c.AppContext;
import client.enterprise.b2c.model.bean.bo.LoginBuffer;
import client.enterprise.b2c.model.bean.po.User;
import client.enterprise.b2c.model.data.api.ApiHttpClient;
import client.enterprise.b2c.model.interactor.LoginInteractor;
import client.enterprise.b2c.model.interactor.impl.LoginInterImpl;
import client.enterprise.b2c.presenter.LoginPer;
import client.enterprise.b2c.presenter.result.OnLoginFinishedListener;
import client.enterprise.b2c.ui.fragment.FragmentMine;
import client.enterprise.b2c.ui.view.LoginView;
import client.enterprise.b2c.util.LogDebug;
import client.enterprise.b2c.util.NetWorkUtils;
import client.enterprise.b2c.util.ToastUtils;
import cz.msebera.android.httpclient.client.CookieStore;
import cz.msebera.android.httpclient.client.protocol.ClientContext;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.protocol.HttpContext;

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
            AppContext.getInstance().showConntctError();
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
    public void onLoginSuccess(String statue, String responseString) {
        AsyncHttpClient client = ApiHttpClient.getHttpClient();
        HttpContext httpContext = client.getHttpContext();
        CookieStore cookies = (CookieStore) httpContext.getAttribute(ClientContext.COOKIE_STORE);
        if (cookies != null) {
            String tmpcookies = "";
            for (Cookie c : cookies.getCookies()) {
                tmpcookies += (c.getName() + "=" + c.getValue()) + ";";
            }
            LogDebug.log("cookies:" + tmpcookies);
            AppContext.getInstance().setProperty(AppConfig.CONF_COOKIE, tmpcookies);
            ApiHttpClient.setCookie(ApiHttpClient.getCookie(AppContext.getInstance()));
        }

        Gson gson = new Gson();
        final User user = gson.fromJson(responseString, new TypeToken<User>(){}.getType());
        AppContext.getInstance().saveUserInfo(user);
        LogDebug.log("getSignature============="+user.getSignature());
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
