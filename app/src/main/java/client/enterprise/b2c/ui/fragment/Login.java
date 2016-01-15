package client.enterprise.b2c.ui.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import butterknife.Bind;
import butterknife.OnClick;
import client.enterprise.b2c.R;
import client.enterprise.b2c.base.BaseFragment;
import client.enterprise.b2c.ui.activity.SonActivity;
import client.enterprise.b2c.widget.SearchEditText;

/**
 * Created by raohoulin on 2016.1.9.
 */
public class Login extends BaseFragment {
    @Bind(R.id.username)
    SearchEditText username;
    @Bind(R.id.password)
    SearchEditText password;
    @Bind(R.id.is_show_password)
    CheckBox isShowPwd;
    private SonActivity sonActivity;

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
    }

    @Override
    public void initData() {
        sonActivity = (SonActivity) getActivity();
    }

    @OnClick(R.id.register)
    public void register(View view) {
        sonActivity.changeFragment(new RegisterOne(), false);
    }

    @OnClick(R.id.login)
    public void login(View view) {

    }
}
