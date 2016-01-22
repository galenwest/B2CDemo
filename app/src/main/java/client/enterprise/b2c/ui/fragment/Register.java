package client.enterprise.b2c.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.OnClick;
import client.enterprise.b2c.R;
import client.enterprise.b2c.base.BaseFragmentManager;
import client.enterprise.b2c.ui.activity.SonActivity;

/**
 * Created by raohoulin on 2016.1.10.
 */
public class Register extends BaseFragmentManager {
    private SonActivity sonActivity;

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

    @OnClick(R.id.next)
    public void nextView(View view) {
//        sonActivity.switchContent(new RegisterPassword(), false);
    }
}
