package client.enterprise.b2c.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import butterknife.Bind;
import butterknife.OnClick;
import client.enterprise.b2c.R;
import client.enterprise.b2c.base.BaseActivity;
import client.enterprise.b2c.widget.TopBarSon;

/**
 * Created by raohoulin on 2016.1.18.
 */
public class ShoppingCarActivity extends BaseActivity {

    @Bind(R.id.top_bar_buy)
    TopBarSon topBarbuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_shopping_car;
    }

    @Override
    public void initView() {
        topBarbuy.setOnTopbarClickListener(new TopBarSon.topbarClickListener() {
            @Override
            public void oneClick() {
                finish();
            }

            @Override
            public void logoClick() {
                MainActivity.actionStart(ShoppingCarActivity.this, false, true, false, false, false);
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

    @OnClick(R.id.go_shopping)
    void goShopping(View view) {
        MainActivity.actionStart(this, false, true, false, false, false);
    }
}
