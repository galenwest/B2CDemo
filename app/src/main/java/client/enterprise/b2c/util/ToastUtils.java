package client.enterprise.b2c.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import client.enterprise.b2c.AppContext;

/**
 * Created by raohoulin on 2016.1.17.
 */
public class ToastUtils {

    public static void showToastInBottom(String msg) {
        Context context = AppContext.getInstance();
        Toast toast = Toast.makeText(context, msg + "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, ScreenUtils.px2dip(context, 150));
        toast.show();
    }

    public static void showToastInCenter(String msg) {
        Context context = AppContext.getInstance();
        Toast toast = Toast.makeText(context, msg + "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
