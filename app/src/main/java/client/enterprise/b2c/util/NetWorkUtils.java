package client.enterprise.b2c.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import client.enterprise.b2c.AppContext;

/**
 * Created by raohoulin on 2016.1.17.
 */
public class NetWorkUtils {

    /**
     * 网络是否有效
     */
    public static boolean isNetAvailable() {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) AppContext.getInstance()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null) {
                    for (int i = 0; i < info.length; i++) {
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

}
