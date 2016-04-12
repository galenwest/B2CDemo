package client.enterprise.b2c.presenter.impl;

import client.enterprise.b2c.model.interactor.CategoryInter;
import client.enterprise.b2c.presenter.CategoryPer;
import client.enterprise.b2c.presenter.result.OnCategoryListener;
import client.enterprise.b2c.ui.view.CategoryView;

/**
 * Created by raohoulin on 2016.2.23.
 */
public class CategoryPerImpl implements CategoryPer, OnCategoryListener {
    private CategoryView categoryView;
    private CategoryInter categoryInter;

    @Override
    public void onResume() {

    }

    @Override
    public void onItemClicked(int position) {

    }
}
