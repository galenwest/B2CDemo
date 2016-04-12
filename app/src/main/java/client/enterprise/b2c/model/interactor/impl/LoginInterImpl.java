package client.enterprise.b2c.model.interactor.impl;

import android.text.TextUtils;

import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.SocketTimeoutException;

import client.enterprise.b2c.AppContext;
import client.enterprise.b2c.model.bean.bo.LoginBuffer;
import client.enterprise.b2c.model.bean.vo.ResponseResult;
import client.enterprise.b2c.model.data.api.ApiHttpClient;
import client.enterprise.b2c.model.data.api.Urls;
import client.enterprise.b2c.model.interactor.LoginInteractor;
import client.enterprise.b2c.presenter.result.OnLoginFinishedListener;
import client.enterprise.b2c.util.Check;
import client.enterprise.b2c.util.LogDebug;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.conn.ConnectTimeoutException;

public class LoginInterImpl implements LoginInteractor {

    private OnLoginFinishedListener finishedListener;
    private final TextHttpResponseHandler loginHandler = new TextHttpResponseHandler() {
        @Override
        public void onSuccess(int statusCode, Header[] headers, String responseString) {
            String requestCode = null, result = null;
            try {
                JSONObject response = new JSONObject(responseString);
                requestCode = response.getString("requestCode");
                result = response.getString("result");
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            ResponseResult responseResult = new ResponseResult(requestCode, result);
            if (ResponseResult.SUCCESS.equals(requestCode)) {
                String statue = null, info = null;
                try {
                    JSONObject loginBuffer = new JSONObject(result);
                    statue = loginBuffer.getString("Statue");
                    info = loginBuffer.getString("Tip");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                switch (statue) {
                    case LoginBuffer.LOGIN_SUCCESS:
                        finishedListener.onLoginSuccess(statue, info);
                        break;
                    case LoginBuffer.SUCCEED_HAS_PHONE:
                        finishedListener.onLoginSuccess(statue, info);
                        break;
                    case LoginBuffer.SUCCEED_NO_PHONE:
                        finishedListener.onLoginSuccess(statue, info);
                        break;
                    case LoginBuffer.ERROR_USERNAME_IS_NULL:
                        finishedListener.onLoginError(statue, info);
                        break;
                    case LoginBuffer.ERROR_PASSWORD_IS_NULL:
                        finishedListener.onLoginError(statue, info);
                        break;
                    case LoginBuffer.PASSWORD_ERROR:
                        finishedListener.onLoginError(statue, info);
                        break;
                    case LoginBuffer.ERROR_USERNAME_NOT_FOUND:
                        finishedListener.onLoginError(statue, info);
                        break;
                    default:
                        break;
                }
            } else if (ResponseResult.ERROR.equals(requestCode)) {
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            if (throwable.getClass().getName().equals(ConnectTimeoutException.class.getName())) {
                AppContext.getInstance().showConntctTimeOutError();
            } else if (throwable.getClass().getName().equals(SocketTimeoutException.class.getName())) {
                AppContext.getInstance().showConntctTimeOutError();
            } else {
                AppContext.getInstance().showConntctTimeOutError();
            }
        }

        @Override
        public void onFinish() {
            super.onFinish();
            finishedListener.hideDialog();
        }
    };

    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {
        finishedListener = listener;
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
            RequestParams params = new RequestParams();
            if (Check.checkPhone(password)) {
                params.put("loag", 1);
            } else {
                params.put("loag", 0);
            }
            params.put("username", username);
            params.put("userpwd", password);
            ApiHttpClient.post(Urls.login, params, loginHandler);
        }

    }
}
