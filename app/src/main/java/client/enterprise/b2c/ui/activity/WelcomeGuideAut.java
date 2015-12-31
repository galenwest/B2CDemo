package client.enterprise.b2c.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import client.enterprise.b2c.R;
import client.enterprise.b2c.base.BaseActivity;
import client.enterprise.b2c.ui.adapter.WelcomePagerAdapter;

/**
 * Created by raohoulin on 2015.12.25.
 */
public class WelcomeGuideAut extends BaseActivity {

    @Bind(R.id.welcome_pager) ViewPager pager;
    @Bind(R.id.redirect_button) Button redirectButton;

    private ImageView imageView;

    private List<View> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutID() {
        return R.layout.welcome_guide;
    }

    @Override
    public void initView() {
        pager.setAdapter(new WelcomePagerAdapter(list));
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == list.size()-1) {
                    redirectButton.setVisibility(View.VISIBLE);
                } else {
                    redirectButton.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void initData() {
        list = new ArrayList<>();
        imageView = new ImageView(this);
        imageView.setBackgroundColor(Color.parseColor("#EE4000"));
        list.add(imageView);
        imageView = new ImageView(this);
        imageView.setBackgroundColor(Color.parseColor("#9932CC"));
        list.add(imageView);
        imageView = new ImageView(this);
        imageView.setBackgroundColor(Color.parseColor("#1874CD"));
        list.add(imageView);
    }

    @OnClick(R.id.redirect_button)
    void redirectTo(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
