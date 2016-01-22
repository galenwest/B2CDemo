package client.enterprise.b2c;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.SocketTimeoutException;
import java.util.Properties;
import java.util.UUID;

import client.enterprise.b2c.base.BaseApplication;
import client.enterprise.b2c.model.bean.bo.LoginBuffer;
import client.enterprise.b2c.model.bean.po.User;
import client.enterprise.b2c.model.bean.vo.ResponseResult;
import client.enterprise.b2c.model.data.api.ApiHttpClient;
import client.enterprise.b2c.model.data.api.Urls;
import client.enterprise.b2c.util.Check;
import client.enterprise.b2c.util.LogDebug;
import client.enterprise.b2c.util.StringUtils;
import client.enterprise.b2c.util.ToastUtils;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.conn.ConnectTimeoutException;

/**
 * Created by raohoulin on 2015.12.29.
 */
public class AppContext extends BaseApplication {

    private static AppContext instance;

    private User user = null;
    private boolean isLogin;
    private String signature;

    public boolean isLogin() {
        return isLogin;
    }

    public String getSignature() {
        return signature;
    }

    /**
     * 获得当前app运行的AppContext
     *
     * @return
     */
    public static AppContext getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initNetWork();
        initLogin();
    }

    private void initLogin() {
        user = getLoginUser();
        if (null != user && !("".equals(user.getSignature()) || user.getSignature() == null)) {
            String username = user.getU_ClientNum();
            String password = user.getU_Pwd();
            checkAcount(username, password);
        } else {
            this.cleanLoginInfo();
        }
    }

    private void checkAcount(String username, String password) {
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
            if (ResponseResult.SUCCESS.equals(requestCode)) {
                String statue = null, info = null;
                try {
                    JSONObject loginBuffer = new JSONObject(result);
                    statue = loginBuffer.getString("Statue");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                switch (statue) {
                    case LoginBuffer.LOGIN_SUCCESS:
                        isLogin = true;
                        signature = user.getSignature();
                        break;
                    case LoginBuffer.SUCCEED_HAS_PHONE:
                        isLogin = true;
                        signature = user.getSignature();
                        break;
                    case LoginBuffer.SUCCEED_NO_PHONE:
                        isLogin = true;
                        signature = user.getSignature();
                        break;
                    case LoginBuffer.ERROR_USERNAME_IS_NULL:
                        AppContext.getInstance().cleanLoginInfo();
                        break;
                    case LoginBuffer.ERROR_PASSWORD_IS_NULL:
                        AppContext.getInstance().cleanLoginInfo();
                        break;
                    case LoginBuffer.PASSWORD_ERROR:
                        AppContext.getInstance().cleanLoginInfo();
                        break;
                    case LoginBuffer.ERROR_USERNAME_NOT_FOUND:
                        AppContext.getInstance().cleanLoginInfo();
                        break;
                    default:
                        break;
                }
            } else if (ResponseResult.ERROR.equals(requestCode)) {
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            ToastUtils.showToastInBottom("网络未连接，请检查网络");
        }

        @Override
        public void onFinish() {
            super.onFinish();
        }
    };

    private void initNetWork() {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        ApiHttpClient.setHttpClient(client);
        ApiHttpClient.setCookie(ApiHttpClient.getCookie(this));
    }

    public Properties getProperties() {
        return AppConfig.getAppConfig(this).get();
    }

    public void setProperties(Properties ps) {
        AppConfig.getAppConfig(this).set(ps);
    }

    public void setProperty(String key, String value) {
        AppConfig.getAppConfig(this).set(key, value);
    }

    /**
     * 获取App唯一标识
     *
     * @return
     */
    public String getAppId() {
        String uniqueID = getProperty(AppConfig.CONF_APP_UNIQUEID);
        if (StringUtils.isEmpty(uniqueID)) {
            uniqueID = UUID.randomUUID().toString();
            setProperty(AppConfig.CONF_APP_UNIQUEID, uniqueID);
        }
        return uniqueID;
    }

    /**
     * 获取cookie时传AppConfig.CONF_COOKIE
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {
        String res = AppConfig.getAppConfig(this).get(key);
        return res;
    }

    public void removeProperty(String... key) {
        AppConfig.getAppConfig(this).remove(key);
    }

    /**
     * 清除保存的缓存
     */
    public void cleanCookie() {
        removeProperty(AppConfig.CONF_COOKIE);
    }

    /**
     * 获取App安装包信息
     *
     * @return
     */
    public PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }

    /**
     * 保存登录信息
     *
     * @param user 用户信息
     */
    public void saveUserInfo(final User user) {
        this.signature = user.getSignature();
        this.isLogin = true;
        setProperties(new Properties() {
            {
                setProperty("user.signature", user.getSignature());
                setProperty("user.uid", String.valueOf(user.getID()));
                setProperty("user.name", user.getU_CName());
                setProperty("user.face", user.getU_UImg());// 用户头像-文件名
                setProperty("user.account", user.getU_ClientNum());
                setProperty("user.pwd", user.getU_Pwd());
                setProperty("user.registerData", user.getU_RegeistDate());
                setProperty("user.tellphone", user.getU_Tel());
                setProperty("user.type", String.valueOf(user.getU_Type()));
                setProperty("user.status", isNull(user.getU_Status()));
                setProperty("user.vipNum", user.getU_VIPNum());
            }
        });
    }

    private String isNull(String isNull) {
        return isNull==null ? "" : isNull;
    }

    /**
     * 获取登录信息
     * @return User
     */
    public User getLoginUser() {
        User user = new User();
        user.setSignature(getProperty("user.signature"));
        user.setID(StringUtils.toInt(getProperty("user.uid"), 0));
        user.setU_CName(getProperty("user.name"));
        user.setU_UImg(getProperty("user.face"));
        user.setU_ClientNum(getProperty("user.account"));
        user.setU_Pwd(getProperty("user.pwd"));
        user.setU_RegeistDate(getProperty("user.registerData"));
        user.setU_Tel(getProperty("user.tellphone"));
        user.setU_Type(StringUtils.toInt(getProperty("user.type"), 0));
        user.setU_Status(getProperty("user.status"));
        user.setU_VIPNum(getProperty("user.vipNum"));
        return user;
    }

    /**
     * 清除登录信息
     */
    public void cleanLoginInfo() {
        this.signature = "";
        this.isLogin = false;
        removeProperty("user.signature", "user.uid", "user.name", "user.face", "user.account",
                "user.pwd", "user.registerData", "user.tellphone",
                "user.type", "user.status", "user.vipNum");
    }
}
