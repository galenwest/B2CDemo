package client.enterprise.b2c.util;

import android.app.ProgressDialog;

/**
 * Created by raohoulin on 2016.1.17.
 */
public class MyProgerssDialog {

    public static void dialog(ProgressDialog _ProgressDialog, String message, int resource) {

        _ProgressDialog.setTitle("请等待");
        _ProgressDialog.setMessage(message);
        _ProgressDialog.setCancelable(false);
        _ProgressDialog.setIcon(resource);
        _ProgressDialog.show();
    }

}
