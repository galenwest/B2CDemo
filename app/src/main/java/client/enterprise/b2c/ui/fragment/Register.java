package client.enterprise.b2c.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.OnClick;
import client.enterprise.b2c.R;
import client.enterprise.b2c.base.BaseFragmentManager;
import client.enterprise.b2c.ui.activity.SonActivity;
import client.enterprise.b2c.ui.view.RegisterView;
import client.enterprise.b2c.util.MyProgerssDialog;

/**
 * Created by raohoulin on 2016.1.10.
 */
public class Register extends BaseFragmentManager implements RegisterView {
    private SonActivity sonActivity;

    ProgressDialog loginProgerss;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_register;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        sonActivity = (SonActivity) getActivity();
    }

    @OnClick(R.id.register)
    public void nextView(View view) {
    }

    @Override
    public void showProgress() {
        if (loginProgerss == null) {
            loginProgerss = new ProgressDialog(getActivity());
            MyProgerssDialog.dialog(loginProgerss, "正在请求中...", android.R.drawable.ic_dialog_info);
        }
    }

    @Override
    public void hideProgress() {
        if (loginProgerss != null) {
            loginProgerss.dismiss();
            loginProgerss = null;
        }
    }
}
