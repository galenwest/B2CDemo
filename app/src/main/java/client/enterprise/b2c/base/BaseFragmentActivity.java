package client.enterprise.b2c.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;
import client.enterprise.b2c.AppManager;
import client.enterprise.b2c.base.interf.BaseViewInterface;

/**
 * Created by noruto on 2015.12.26.
 */
public abstract class BaseFragmentActivity extends FragmentActivity implements BaseViewInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        if (getLayoutID() != 0) {
            setContentView(getLayoutID());
        }

        ButterKnife.bind(this);
        initData();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        AppManager.getAppManager().finishActivity(this);
    }

    public int getLayoutID() {
        return 0;
    }

}
