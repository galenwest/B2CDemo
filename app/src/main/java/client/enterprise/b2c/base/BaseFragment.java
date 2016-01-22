package client.enterprise.b2c.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import client.enterprise.b2c.base.interf.BaseFragmentInterface;
import client.enterprise.b2c.util.LogDebug;

/**
 * Created by raohoulin on 2016.1.6.
 */
public abstract class BaseFragment extends Fragment implements BaseFragmentInterface {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        if (getLayoutID() != 0) {
            view = inflater.inflate(getLayoutID(), container, false);
            ButterKnife.bind(this, view);
        }

        initView();
        initData();

        return view;
    }

    public int getLayoutID() {
        return 0;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
