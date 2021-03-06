package client.enterprise.b2c;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import client.enterprise.b2c.base.BaseActivity;
import client.enterprise.b2c.ui.activity.MainActivity;
import client.enterprise.b2c.ui.activity.WelcomeGuideAut;
import client.enterprise.b2c.util.SharedUtil;

/**
 * Created by raohoulin on 2015.12.25.
 */
public class AppStart extends BaseActivity {
    private int TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                redirectTo();
                return false;
            }
        }).sendEmptyMessageDelayed(0, 2000);
        */
        handler.postDelayed(runnable, TIME);
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // handler自带方法实现定时器
            try {
                if (AppContext.getInstance().isEnd()) {
                    redirectTo();
                } else {
                    handler.postDelayed(this, TIME);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public int getLayoutID() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {}

    @Override
    public void initData() {}

    private void redirectTo() {
        if (SharedUtil.getWelcomeBoolean(getBaseContext())) {
            MainActivity.actionStart(this, false, true, false, false, false);
        } else {
            Intent intent = new Intent(this, WelcomeGuideAut.class);
            startActivity(intent);
            SharedUtil.putWelcomeBoolean(getBaseContext(), true);
        }
        finish();
    }

}
