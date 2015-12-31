package client.enterprise.b2c.presenter.result;

import java.util.List;

import client.enterprise.b2c.model.bean.SearchHistoryData;

/**
 * Created by raohoulin on 2015.12.29.
 */
public interface SearchFinishedListener {
    public void onResumeFinished(List<SearchHistoryData> items);
    public void onClickSearchFinished();
    public void onClearHistoryFinished();
    public void onClearItemHistoryFinished(int position, List<SearchHistoryData> items);
}
