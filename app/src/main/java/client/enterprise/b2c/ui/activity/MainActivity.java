package client.enterprise.b2c.ui.activity;

import android.content.Context;
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

    private static final String ISHOMETAG = "isHome";
    private static final String ISCATEGORYTAG = "isCategory";
    private static final String ISFINDTAG = "isFind";
    private static final String ISMINETAG = "isMine";

    public static final String HOME = "home";
    public static final String CATEGOTY = "category";
    public static final String FIND = "find";
    public static final String MINE = "mine";

    private DoubleClickExitHelper mDoubleClickExit;
    private FragmentManager fragmentManager;
    private Fragment homeFragment, categoryFragment, findFragment, mineFragment;
    private boolean isHome,isCategory,isFind,isMine;

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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        processExtraData();
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        group.setOnCheckedChangeListener(this);
        mTopbar.setOnTopbarClickListener(
                new TopBar.topbarClickListener() {

                    @Override
                    public void rightClick() {
                        SonActivity.actionStart(MainActivity.this, SonActivity.SHOPPINGCAR);
                    }

                    @Override
                    public void titleViewClick() {
                        SearchActivity.actionStart(MainActivity.this);
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
        processExtraData();
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
                changeFragment(HOME, true);
                break;
            case R.id.main_category:
                changeFragment(CATEGOTY, true);
                break;
            case R.id.main_find:
                changeFragment(FIND, true);
                break;
            case R.id.main_mine:
                changeFragment(MINE, true);
                break;
            default:
                break;
        }
    }

    public void changeFragment(String tab, boolean isInit) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        switch (tab) {
            case HOME:
                homeFragment=fragmentManager.findFragmentByTag(HOME);
                hideTab(transaction);
                if (homeFragment == null){
                    homeFragment = new FragmentHome();
                    transaction.add(R.id.main_content, homeFragment,HOME);
                } else{
                    transaction.show(homeFragment);
                }
                break;
            case CATEGOTY:
                categoryFragment=fragmentManager.findFragmentByTag(CATEGOTY);
                hideTab(transaction);
                if (categoryFragment == null){
                    categoryFragment = new FragmentCategory();
                    transaction.add(R.id.main_content, categoryFragment,CATEGOTY);
                } else{
                    transaction.show(categoryFragment);
                }
                break;
            case FIND:
                findFragment=fragmentManager.findFragmentByTag(FIND);
                hideTab(transaction);
                if (findFragment == null){
                    findFragment = new FragmentFind();
                    transaction.add(R.id.main_content, findFragment,FIND);
                } else{
                    transaction.show(findFragment);
                }
                break;
            case MINE:
                mineFragment=fragmentManager.findFragmentByTag(MINE);
                hideTab(transaction);
                if (mineFragment == null){
                    mineFragment = new FragmentMine();
                    transaction.add(R.id.main_content, mineFragment,MINE);
                } else{
                    transaction.show(mineFragment);
                }
                break;
            default:
                break;
        }

        if (!isInit) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    private void hideTab(FragmentTransaction transaction){
        if (homeFragment != null)
        {
            transaction.hide(homeFragment);
        }
        if (categoryFragment != null)
        {
            transaction.hide(categoryFragment);
        }
        if (findFragment != null)
        {
            transaction.hide(findFragment);
        }
        if (mineFragment != null)
        {
            transaction.hide(mineFragment);
        }
    }

    public static void actionStart(Context context, boolean isHome, boolean isCategory, boolean isFind, boolean isMine) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(ISHOMETAG, isHome);
        intent.putExtra(ISCATEGORYTAG, isCategory);
        intent.putExtra(ISFINDTAG, isFind);
        intent.putExtra(ISMINETAG, isMine);
        context.startActivity(intent);
    }

    private void processExtraData() {
        Intent intent = getIntent();
        isHome = intent.getBooleanExtra(ISHOMETAG, false);
        isCategory = intent.getBooleanExtra(ISCATEGORYTAG, false);
        isFind = intent.getBooleanExtra(ISFINDTAG, false);
        isMine = intent.getBooleanExtra(ISMINETAG, false);
        fragmentManager = getSupportFragmentManager();
        if (isHome) {
            mainHome.setChecked(true);
            changeFragment(HOME, false);
        }
        if (isCategory) {
            mainCategory.setChecked(true);
            changeFragment(CATEGOTY, false);
        }
        if (isFind) {
            mainFind.setChecked(true);
            changeFragment(FIND, false);
        }
        if (isMine) {
            mainMine.setChecked(true);
            changeFragment(MINE, false);
        }
    }

}
