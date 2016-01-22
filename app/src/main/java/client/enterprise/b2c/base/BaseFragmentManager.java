package client.enterprise.b2c.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import client.enterprise.b2c.AppManager;

/**
 * Created by raohoulin on 2016.1.18.
 */
public abstract class BaseFragmentManager extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addFragment(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishFragment(this);
    }
}
