package client.enterprise.b2c.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import butterknife.Bind;
import client.enterprise.b2c.R;
import client.enterprise.b2c.base.BaseActivity;
import client.enterprise.b2c.widget.TopBarSon;

/**
 * Created by raohoulin on 2016.1.5.
 */
public class ShoppingCarActivity extends BaseActivity {

    @Bind(R.id.top_bar_son) TopBarSon topBarSon;

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
        topBarSon.setOnTopbarClickListener(new TopBarSon.topbarClickListener() {
            @Override
            public void oneClick() {
                finish();
            }

            @Override
            public void logoClick() {
                MainActivity.actionStart(ShoppingCarActivity.this);
            }

            @Override
            public void threeClick() {
                SearchActivity.actionStart(ShoppingCarActivity.this);
            }

            @Override
            public void foreClick() {
            }
        });
    }

    @Override
    public void initData() {

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ShoppingCarActivity.class);
        context.startActivity(intent);
    }

}
