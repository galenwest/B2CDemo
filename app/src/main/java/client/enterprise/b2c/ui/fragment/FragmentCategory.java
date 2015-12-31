package client.enterprise.b2c.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import client.enterprise.b2c.R;

/**
 * Created by noruto on 2015.12.26.
 */
public class FragmentCategory extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index_category, null);
        return view;
    }
}
