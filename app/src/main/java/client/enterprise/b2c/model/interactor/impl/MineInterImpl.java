package client.enterprise.b2c.model.interactor.impl;

import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.SocketTimeoutException;

import client.enterprise.b2c.AppContext;
import client.enterprise.b2c.model.bean.bo.LoginOutBuffer;
import client.enterprise.b2c.model.bean.vo.ResponseResult;
import client.enterprise.b2c.model.data.api.ApiHttpClient;
import client.enterprise.b2c.model.data.api.Urls;
import client.enterprise.b2c.model.interactor.MineInter;
import client.enterprise.b2c.presenter.result.OnGetMineFinishedListener;
import client.enterprise.b2c.util.Check;
import client.enterprise.b2c.util.ToastUtils;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.conn.ConnectTimeoutException;

/**
 * Created by raohoulin on 2016.1.21.
 */
public class MineInterImpl implements MineInter {

    private OnGetMineFinishedListener loginOutListener;

    @Override
    public void pullData(OnGetMineFinishedListener onGetMineFinishedListener) {
        if (AppContext.getInstance().isLogin()) {
            onGetMineFinishedListener.onPullUserDataSucceed(AppContext.getInstance().getLoginUser());
        } else {
            onGetMineFinishedListener.onPullUserDataError();
        }
    }

    @Override
    public void loginOut(String signature, OnGetMineFinishedListener onGetMineFinishedListener) {
        loginOutListener = onGetMineFinishedListener;
        RequestParams params = new RequestParams();
        params.put("signature", signature);
        ApiHttpClient.post(Urls.loginOut, params, loginOutHandler);
    }

    private final TextHttpResponseHandler loginOutHandler = new TextHttpResponseHandler() {

        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            AppContext.getInstance().cleanLoginInfo();
            loginOutListener.onLoginOutfailed();
            if (throwable.getClass().getName().equals(ConnectTimeoutException.class.getName())) {
                AppContext.getInstance().showConntctTimeOutError();
            } else if (throwable.getClass().getName().equals(SocketTimeoutException.class.getName())) {
                AppContext.getInstance().showConntctTimeOutError();
            } else {
                AppContext.getInstance().showConntctTimeOutError();
            }
        }

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
                    case LoginOutBuffer.LOGIN_OUT_SUCCESS:
                        AppContext.getInstance().cleanLoginInfo();
                        loginOutListener.onLoginOutSucceed();
                        break;
                    case LoginOutBuffer.LOGIN_OUT_ERROR:
                        loginOutListener.onLoginOutfailed();
                        ToastUtils.showToastInCenter("服务端注销失败");
                        break;
                    case LoginOutBuffer.LOGIN_OUT_SUCCESS_OLD:
                        AppContext.getInstance().cleanLoginInfo();
                        loginOutListener.onLoginOutSucceed();
                        break;
                    case LoginOutBuffer.SOME_ERROR:
                        loginOutListener.onLoginOutfailed();
                        ToastUtils.showToastInCenter("服务端注销失败");
                        break;
                    default:
                        AppContext.getInstance().cleanLoginInfo();
                        loginOutListener.onLoginOutSucceed();
                        break;
                }
            } else {
                loginOutListener.onLoginOutfailed();
            }
        }
    };
}
