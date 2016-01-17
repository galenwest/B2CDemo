package client.enterprise.b2c.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.OnClick;
import client.enterprise.b2c.R;
import client.enterprise.b2c.base.BaseFragment;
import client.enterprise.b2c.ui.activity.MainActivity;

/**
 * Created by raohoulin on 2016.1.6.
 */
public class ShoppingCar extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_shopping_car;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.go_shopping)
    void goShopping(View view) {
        MainActivity.actionStart(getContext(), true, false, false, false);
    }
}
