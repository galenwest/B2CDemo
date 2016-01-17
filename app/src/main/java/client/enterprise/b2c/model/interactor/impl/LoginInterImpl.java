package client.enterprise.b2c.model.interactor.impl;

import android.os.Handler;
import android.text.TextUtils;

import client.enterprise.b2c.model.interactor.LoginInteractor;
import client.enterprise.b2c.presenter.result.OnLoginFinishedListener;
import client.enterprise.b2c.util.HttpCallbackListener;
import client.enterprise.b2c.util.HttpUtil;

public class LoginInterImpl implements LoginInteractor, HttpCallbackListener {

    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {
        // Mock login. I'm creating a handler to delay the answer a couple of seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean error = false;
                if (TextUtils.isEmpty(username)) {
                    listener.onUsernameEmpty();
                    error = true;
                }
                if (TextUtils.isEmpty(password)) {
                    listener.onPasswordEmpty();
                    error = true;
                }
                if (!error) {
                    listener.onLoginSuccess();
                }
            }
        }, 1000);
        HttpUtil.sendHttpGetRequest("laskjf", this);

    }

    @Override
    public void onFinish(String response) {

    }

    @Override
    public void onError(Exception e) {

    }
}
