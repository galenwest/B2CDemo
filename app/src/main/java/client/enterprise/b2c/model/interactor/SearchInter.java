package client.enterprise.b2c.model.interactor;

import client.enterprise.b2c.model.bean.po.SearchHistoryData;
import client.enterprise.b2c.presenter.result.SearchFinishedListener;

/**
 * Created by raohoulin on 2015.12.29.
 */
public interface SearchInter {

    public void findItem(SearchFinishedListener finishedListener);

    public void insertItem(SearchFinishedListener finishedListener, SearchHistoryData data);

    public void deleteAllItem(SearchFinishedListener finishedListener);

    public void deleteAItem(SearchFinishedListener finishedListener, long time, int position);
}
