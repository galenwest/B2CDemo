package client.enterprise.b2c.ui.view;

import java.util.List;

import client.enterprise.b2c.model.bean.SearchHistoryData;

/**
 * Created by raohoulin on 2015.12.29.
 */
public interface SearchView {
    public void showSearchResult();
    public void hideSearchResult();
    public void setListItems(List<SearchHistoryData> items);
    public void navigateTo();
    public void toastSearchNull();
    public void cleanAllItem();
    public void cleanAItem(int position);
}
