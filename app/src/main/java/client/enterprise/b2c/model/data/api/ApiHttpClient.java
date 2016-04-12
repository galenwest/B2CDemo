package client.enterprise.b2c.model.data.api;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.Locale;

import client.enterprise.b2c.AppContext;
import cz.msebera.android.httpclient.client.params.ClientPNames;

/**
 * Created by raohoulin on 2016.1.17.
 */
public class ApiHttpClient {

    public static AsyncHttpClient client;
    public final static String HOST = "115.158.64.20";
    private static String API_URL = "http://" + HOST + "/%s";
    private static String appCookie;

    public ApiHttpClient() {
    }

    public static AsyncHttpClient getHttpClient() {
        if (client == null) {
            synchronized (ApiHttpClient.class) {
                if (client == null) {
                    client = new AsyncHttpClient();
                }
            }
        }
        return client;
    }

    public static void setHttpClient(AsyncHttpClient c) {
        client = c;
        client.setTimeout(3000);
        client.addHeader("Accept-Language", Locale.getDefault().toString());
        client.addHeader("Host", HOST);
        client.addHeader("Connection", "Keep-Alive");
        client.getHttpClient().getParams()
                .setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
        client.setUserAgent(ApiClientHelper.getUserAgent(AppContext.getInstance()));
    }

    public static String getAbsoluteApiUrl(String partUrl) {
        String url = partUrl;
        if (!partUrl.startsWith("http:") && !partUrl.startsWith("https:")) {
            url = String.format(API_URL, partUrl);
        }
        return url;
    }

    public static void get(String partUrl, AsyncHttpResponseHandler handler) {
        client.get(getAbsoluteApiUrl(partUrl), handler);
    }

    public static void get(String partUrl, RequestParams params,
                           AsyncHttpResponseHandler handler) {
        client.get(getAbsoluteApiUrl(partUrl), params, handler);
    }

    public static void post(String partUrl, RequestParams params,
                            TextHttpResponseHandler handler) {
        client.post(getAbsoluteApiUrl(partUrl), params, handler);
    }

    public static void setCookie(String cookie) {
        client.addHeader("Cookie", cookie);
    }

    public static void cleanCookie() {
        appCookie = "";
    }

    public static String getCookie(AppContext appContext) {
        if (appCookie == null || appCookie == "") {
            appCookie = appContext.getProperty("cookie");
        }
        return appCookie;
    }
}
