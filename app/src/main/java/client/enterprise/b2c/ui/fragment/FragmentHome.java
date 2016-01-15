package client.enterprise.b2c.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import client.enterprise.b2c.R;
import client.enterprise.b2c.base.BaseFragment;

/**
 * Created by noruto on 2015.12.26.
 */
public class FragmentHome extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public int getLayoutID() {
        return R.layout.index_home;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
    }
}
