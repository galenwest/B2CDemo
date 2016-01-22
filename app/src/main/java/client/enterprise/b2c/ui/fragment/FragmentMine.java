package client.enterprise.b2c.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.Bind;
import butterknife.OnClick;
import client.enterprise.b2c.AppContext;
import client.enterprise.b2c.R;
import client.enterprise.b2c.base.BaseFragment;
import client.enterprise.b2c.model.bean.po.User;
import client.enterprise.b2c.model.data.api.ApiHttpClient;
import client.enterprise.b2c.presenter.MinePer;
import client.enterprise.b2c.presenter.impl.MinePerImpl;
import client.enterprise.b2c.ui.activity.SonActivity;
import client.enterprise.b2c.ui.view.MineView;
import client.enterprise.b2c.util.MyProgerssDialog;

/**
 * Created by noruto on 2015.12.26.
 */
public class FragmentMine extends BaseFragment implements MineView{

    private MinePer minePer;

    @Bind(R.id.user_info_login) ImageView headImg;
    @Bind(R.id.user_info) RelativeLayout userInfo;
    @Bind(R.id.user_head) ImageView userHead;
    @Bind(R.id.user_account) TextView userAccount;
    ProgressDialog loginProgerss;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public int getLayoutID() {
        return R.layout.index_mine;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        minePer = new MinePerImpl(this);
        minePer.pullUserData();
    }

    @OnClick(R.id.user_info_login)
    public void loginView(View view) {
        SonActivity.actionStart(getContext(), SonActivity.LOGIN);
    }

    @Override
    public void showProgress() {
        if (loginProgerss == null) {
            loginProgerss = new ProgressDialog(getActivity());
            MyProgerssDialog.dialog(loginProgerss, "正在拉取信息...", android.R.drawable.ic_dialog_info);
        }
    }

    @Override
    public void hideProgress() {
        if (loginProgerss != null) {
            loginProgerss.dismiss();
            loginProgerss = null;
        }
    }

    @Override
    public void showLoginHeadImg() {
        headImg.setVisibility(View.VISIBLE);
        userInfo.setVisibility(View.GONE);
    }

    @Override
    public void showUserInfo(User user) {
        userInfo.setVisibility(View.VISIBLE);
        Glide.with(this)
                .load("http://" + ApiHttpClient.HOST + user.getU_UImg())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.user_info_head_selector)
                .crossFade()
                .into(userHead);
        userAccount.setText(user.getU_CName());
        headImg.setVisibility(View.GONE);
    }
}
