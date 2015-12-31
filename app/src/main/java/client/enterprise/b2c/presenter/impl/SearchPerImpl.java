package client.enterprise.b2c.presenter.impl;

import java.util.List;

import client.enterprise.b2c.model.bean.SearchHistoryData;
import client.enterprise.b2c.model.interactor.impl.SearchInterImpl;
import client.enterprise.b2c.presenter.SearchPer;
import client.enterprise.b2c.presenter.result.SearchFinishedListener;
import client.enterprise.b2c.ui.view.SearchView;

/**
 * Created by raohoulin on 2015.12.29.
 */
public class SearchPerImpl implements SearchPer, SearchFinishedListener {
    private SearchView searchView;
    private SearchInterImpl searchInter;

    public SearchPerImpl(SearchView searchView) {
        this.searchView = searchView;
        searchInter = new SearchInterImpl();
    }

    @Override
    public void onResume() {
        searchInter.findItem(this);
    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void onClickSearch(String search) {
        if (search == null || "".equals(search)) {
            searchView.toastSearchNull();
        } else {
            java.util.Date writeTime = new java.util.Date();
            SearchHistoryData data = new SearchHistoryData(search, writeTime.getTime());
            searchInter.insertItem(this, data);
        }
    }

    @Override
    public void onTextSearch() {
    }

    @Override
    public void onClearHistory() {
        searchInter.deleteAllItem(this);
    }

    @Override
    public void onResumeFinished(List<SearchHistoryData> items) {
        if (items.size() > 0) {
            searchView.setListItems(items);
            searchView.showSearchResult();
        } else {
            searchView.hideSearchResult();
        }
    }

    @Override
    public void onClickSearchFinished() {
        searchView.navigateTo();
    }

    @Override
    public void onClearHistoryFinished() {
        searchView.notifyDataSetChanged();
        searchView.hideSearchResult();
    }
}
