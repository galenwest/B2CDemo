package client.enterprise.b2c.base;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;
import client.enterprise.b2c.AppManager;
import client.enterprise.b2c.base.interf.BaseViewInterface;

/**
 * Created by raohoulin on 2015.12.25.
 */
public abstract class BaseActivity extends Activity implements BaseViewInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        if (getLayoutID() != 0) {
            setContentView(getLayoutID());
        }

        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            initData();
            initView();
        }
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
