package client.enterprise.b2c.model.interactor.impl;

import java.util.List;

import client.enterprise.b2c.AppContext;
import client.enterprise.b2c.model.bean.po.SearchHistoryData;
import client.enterprise.b2c.model.data.db.SearchHistoryDatabase;
import client.enterprise.b2c.model.interactor.SearchInter;
import client.enterprise.b2c.presenter.result.SearchFinishedListener;

/**
 * Created by raohoulin on 2015.12.29.
 */
public class SearchInterImpl implements SearchInter {

    private SearchHistoryDatabase searchDB;

    @Override
    public void findItem(SearchFinishedListener finishedListener) {
        searchDB = new SearchHistoryDatabase(AppContext.getInstance());
        finishedListener.onResumeFinished(searchDB.query(" ORDER BY writeTime DESC"));
    }

    @Override
    public void insertItem(SearchFinishedListener finishedListener, SearchHistoryData data) {
        searchDB = new SearchHistoryDatabase(AppContext.getInstance());
        List<SearchHistoryData> list = searchDB.query(" where search='" + data.getSearch() + "'");
        if (list.size() > 0) {
            searchDB.update(data);
        } else {
            searchDB.insert(data);
        }
        finishedListener.onClickSearchFinished();
    }

    @Override
    public void deleteAllItem(SearchFinishedListener finishedListener) {
        searchDB = new SearchHistoryDatabase(AppContext.getInstance());
        searchDB.delete();
        finishedListener.onClearHistoryFinished();
    }

    @Override
    public void deleteAItem(SearchFinishedListener finishedListener, long time, int position) {
        searchDB = new SearchHistoryDatabase(AppContext.getInstance());
        searchDB.delete(time);
        finishedListener.onClearItemHistoryFinished(position, searchDB.query());
    }
}
