package client.enterprise.b2c.presenter;

/**
 * Created by raohoulin on 2015.12.29.
 */
public interface SearchPer {
    public void onResume();
    public void onItemClicked(int position);
    public void onClickSearch(String search);
    public void onTextSearch();
    public void onClearHistory();
    public void onClearItemHistory(int position, long time);
}
