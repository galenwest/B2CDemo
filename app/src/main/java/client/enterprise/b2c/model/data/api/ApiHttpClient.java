package client.enterprise.b2c.model.data.api;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import client.enterprise.b2c.AppContext;

/**
 * Created by raohoulin on 2016.1.17.
 */
public class ApiHttpClient {

    public static AsyncHttpClient client;
    public final static String HOST = "www.raohoulin.com";
    private static String API_URL = "http://" + HOST + "/%s";
    private static String appCookie;

    public ApiHttpClient() {
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
                            AsyncHttpResponseHandler handler) {
        client.post(getAbsoluteApiUrl(partUrl), params, handler);
    }

    public static void setCookie(String cookie) {
        client.addHeader("Cookie", cookie);
    }

    public static void cleanCookie() {
        appCookie = "";
    }

}
