package client.enterprise.b2c.model.interactor;

import client.enterprise.b2c.presenter.result.OnCategoryListener;

/**
 * Created by raohoulin on 2016.2.23.
 */
public interface CategoryInter {

    public void findCategoryItem(OnCategoryListener finishedListener);
}
