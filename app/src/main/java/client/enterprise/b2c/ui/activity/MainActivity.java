package client.enterprise.b2c.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.Bind;
import client.enterprise.b2c.R;
import client.enterprise.b2c.base.BaseFragmentActivity;
import client.enterprise.b2c.ui.fragment.FragmentCategory;
import client.enterprise.b2c.ui.fragment.FragmentFind;
import client.enterprise.b2c.ui.fragment.FragmentHome;
import client.enterprise.b2c.ui.fragment.FragmentMine;
import client.enterprise.b2c.ui.helper.DoubleClickExitHelper;
import client.enterprise.b2c.widget.TopBar;

/**
 * Created by raohoulin on 2015.12.25.
 */
public class MainActivity extends BaseFragmentActivity implements RadioGroup.OnCheckedChangeListener {

    private DoubleClickExitHelper mDoubleClickExit;
    private FragmentManager fragmentManager;

    @Bind(R.id.main_button_tabs) RadioGroup group;
    @Bind(R.id.main_home) RadioButton mainHome;
    @Bind(R.id.main_category) RadioButton mainCategory;
    @Bind(R.id.main_find) RadioButton mainFind;
    @Bind(R.id.main_mine) RadioButton mainMine;
    @Bind(R.id.top_bar) TopBar mTopbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        fragmentManager = getSupportFragmentManager();
        mainHome.setChecked(true);
        group.setOnCheckedChangeListener(this);
        changeFragment(new FragmentHome(), false);
        mTopbar.setOnTopbarClickListener(
                new TopBar.topbarClickListener() {

                    @Override
                    public void rightClick() {
                        Toast.makeText(MainActivity.this,
                                "right", Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void titleViewClick() {
                        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void leftClick() {
                        Toast.makeText(MainActivity.this,
                                "left", Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        // 控制topbar上组件的状态
        mTopbar.setButtonVisable(0, true);
        mTopbar.setButtonVisable(1, true);
    }

    @Override
    public void initData() {
        mDoubleClickExit = new DoubleClickExitHelper(this);
    }

    /**
     * 监听返回--是否退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 是否退出应用
            return mDoubleClickExit.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.main_home:
                changeFragment(new FragmentHome(), true);
                break;
            case R.id.main_category:
                changeFragment(new FragmentCategory(), true);
                break;
            case R.id.main_find:
                changeFragment(new FragmentFind(), true);
                break;
            case R.id.main_mine:
                changeFragment(new FragmentMine(), true);
                break;
            default:
                break;
        }
    }

    public void changeFragment(Fragment fragment, boolean isInit) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_content, fragment);
        if (!isInit) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}