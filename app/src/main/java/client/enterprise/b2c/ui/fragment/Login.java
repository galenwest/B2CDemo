package client.enterprise.b2c.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import butterknife.Bind;
import butterknife.OnClick;
import client.enterprise.b2c.R;
import client.enterprise.b2c.base.BaseFragmentManager;
import client.enterprise.b2c.presenter.LoginPer;
import client.enterprise.b2c.presenter.impl.LoginPerImpl;
import client.enterprise.b2c.ui.activity.MainActivity;
import client.enterprise.b2c.ui.activity.SonActivity;
import client.enterprise.b2c.ui.view.LoginView;
import client.enterprise.b2c.util.MyProgerssDialog;
import client.enterprise.b2c.util.ToastUtils;
import client.enterprise.b2c.widget.LoginEditText;

/**
 * Created by raohoulin on 2016.1.9.
 */
public class Login extends BaseFragmentManager implements LoginView, LoginEditText.OnLoginListener {

    private static final String MSG_LOGIN_SUCCESS = "登陆成功";
    public static final String MSG_USERNAME_NOT_FOUND = "账户不存在";
    public static final String MSG_PASSWORD_ERROR = "密码错误";
    private static final String CONNECT_TIME_OUT = "请求服务器超时";
    private static final String RESPONSE_TIME_OUT = "服务器响应超时";
    private static final String NET_WORK_ERROR = "网络错误";

    @Bind(R.id.username)
    LoginEditText username;
    @Bind(R.id.password)
    LoginEditText password;
    @Bind(R.id.is_show_password)
    CheckBox isShowPwd;
    @Bind(R.id.login)
    Button login;
    ProgressDialog loginProgerss;

    private SonActivity sonActivity;
    private LoginPer loginPer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_login;
    }

    @Override
    public void initView() {
        isShowPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    Editable etable = password.getText();
                    Selection.setSelection(etable, etable.length());
                } else {
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    Editable etable = password.getText();
                    Selection.setSelection(etable, etable.length());
                }
            }
        });
        username.setLoginBtIsClickable(this);
        password.setLoginBtIsClickable(this);
        if (!(username.getText().length() > 0 && password.getText().length() > 0)) {
            login.setBackgroundResource(R.drawable.login_bt_bg_no_click);
            login.setClickable(false);
        }
    }

    @Override
    public void initData() {
        sonActivity = (SonActivity) getActivity();
        loginPer = new LoginPerImpl(this);
    }

    @OnClick(R.id.register)
    public void register(View view) {
        sonActivity.switchContent(new Register(), false);
    }

    @OnClick(R.id.login)
    public void login(View view) {
        loginPer.validateCredentials(username.getText().toString(), password.getText().toString());
    }

    @Override
    public void showProgress() {
        if (loginProgerss == null) {
            loginProgerss = new ProgressDialog(getActivity());
            MyProgerssDialog.dialog(loginProgerss, "登陆中...", android.R.drawable.ic_dialog_info);
        }
    }

    @Override
    public void hideProgress() {
        if (loginProgerss != null) {
            loginProgerss.dismiss();
            loginProgerss = null;
        }
    }

    @Override
    public void setUsernameEmpty() {
        username.setError(getString(R.string.username_empty));
    }

    @Override
    public void setPasswordEmpty() {
        password.setError(getString(R.string.password_empty));
    }

    @Override
    public void navigateToMine() {
        MainActivity.actionStart(getContext(), true, false, false, false, true);
    }

    @Override
    public void showPasswordError() {
        ToastUtils.showToastInCenter(MSG_PASSWORD_ERROR);
    }

    @Override
    public void showUsernameNotFoundError() {
        ToastUtils.showToastInCenter(MSG_USERNAME_NOT_FOUND);
    }

    @Override
    public void showLoginSuccess() {
        ToastUtils.showToastInCenter(MSG_LOGIN_SUCCESS);
    }

    @Override
    public void loginBtIsClickable(boolean clickable) {
        if (clickable) {
            if (username.getText().length() > 0 && password.getText().length() > 0){
                login.setBackgroundResource(R.drawable.login_bt_bg);
                login.setClickable(clickable);
            }
        } else {
            login.setBackgroundResource(R.drawable.login_bt_bg_no_click);
            login.setClickable(clickable);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (loginProgerss != null) {
            loginProgerss.dismiss();
            loginProgerss = null;
        }
    }
}
