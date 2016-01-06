package client.enterprise.b2c.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import butterknife.Bind;
import client.enterprise.b2c.R;
import client.enterprise.b2c.base.BaseFragmentActivity;
import client.enterprise.b2c.widget.TopBarSon;

/**
 * Created by raohoulin on 2016.1.5.
 */
public class SonActivity extends BaseFragmentActivity {
    @Bind(R.id.top_bar_son)
    TopBarSon topBarSon;
    private FragmentManager fragmentManager;
    private String tag;

    public static void actionStart(Context context, String tag) {
        Intent intent = new Intent(context, SonActivity.class);
        intent.putExtra("tag", tag);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutID() {
        return R.layout.shopping_car;
    }

    @Override
    public void initView() {
        fragmentManager = getSupportFragmentManager();
        topBarSon.setOnTopbarClickListener(new TopBarSon.topbarClickListener() {
            @Override
            public void oneClick() {
                finish();
            }

            @Override
            public void logoClick() {
                MainActivity.actionStart(SonActivity.this);
            }

            @Override
            public void threeClick() {
                SearchActivity.actionStart(SonActivity.this);
            }

            @Override
            public void foreClick() {
            }
        });


    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        tag = intent.getStringExtra("tag");
    }

    public void changeFragment(Fragment fragment, boolean isInit) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.son_content, fragment);
        if (!isInit) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

}
