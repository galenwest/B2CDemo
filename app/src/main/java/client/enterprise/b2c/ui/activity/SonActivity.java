package client.enterprise.b2c.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import butterknife.Bind;
import client.enterprise.b2c.AppManager;
import client.enterprise.b2c.R;
import client.enterprise.b2c.base.BaseFragmentActivity;
import client.enterprise.b2c.ui.fragment.Login;
import client.enterprise.b2c.widget.TopBarSon;

/**
 * Created by raohoulin on 2016.1.5.
 */
public class SonActivity extends BaseFragmentActivity {
    private static final String SONTAG = "sonTag";

    public static final String LOGIN = "fragment_login";

    @Bind(R.id.top_bar_son)
    TopBarSon topBarSon;
    private FragmentManager fragmentManager;
    private String tag;

    private Fragment mContent;

    public static void actionStart(Context context, String tag) {
        Intent intent = new Intent(context, SonActivity.class);
        intent.putExtra(SONTAG, tag);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_son;
    }

    @Override
    public void initView() {
        fragmentManager = getSupportFragmentManager();
        topBarSon.setOnTopbarClickListener(new TopBarSon.topbarClickListener() {
            @Override
            public void oneClick() {
                if (fragmentManager.popBackStackImmediate()) {
                    mContent = AppManager.getAppManager().currentFragment();
                } else {
                    finish();
                }
            }

            @Override
            public void logoClick() {
                MainActivity.actionStart(SonActivity.this, false, true, false, false, false);
            }

            @Override
            public void threeClick() {
                SearchActivity.actionStart(SonActivity.this);
            }

            @Override
            public void foreClick() {
                ShoppingCarActivity.actionStart(SonActivity.this);
            }
        });

        switch (tag) {
            case LOGIN:
                addFragment(new Login());
                break;
            default:
                break;
        }
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        tag = intent.getStringExtra(SONTAG);
    }
/*
    public void changeFragment(Fragment fragment, boolean isInit) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.son_content, fragment);
        if (!isInit) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
    */
    public void addFragment(Fragment fragment) {
        mContent = fragment;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.son_content, fragment);
        transaction.commit();
    }

    public void switchContent(Fragment to, boolean isInit) {
        if (mContent != to) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.hide(mContent).add(R.id.son_content, to);
                if (!isInit) {
                    transaction.addToBackStack(null);
                }
                transaction.commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(mContent).show(to);
                if (!isInit) {
                    transaction.addToBackStack(null);
                }
                transaction.commit(); // 隐藏当前的fragment，显示下一个
            }
            mContent = to;
        }
    }
}
