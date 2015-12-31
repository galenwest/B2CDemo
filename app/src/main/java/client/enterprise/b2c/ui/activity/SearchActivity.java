package client.enterprise.b2c.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import client.enterprise.b2c.R;
import client.enterprise.b2c.base.BaseActivity;
import client.enterprise.b2c.model.bean.SearchHistoryData;
import client.enterprise.b2c.presenter.SearchPer;
import client.enterprise.b2c.presenter.impl.SearchPerImpl;
import client.enterprise.b2c.ui.adapter.SelectHistoryAdapter;
import client.enterprise.b2c.ui.view.SearchView;

/**
 * Created by raohoulin on 2015.12.27.
 */
public class SearchActivity extends BaseActivity implements SearchView, SelectHistoryAdapter.upSearchOnEditTextListener, View.OnClickListener, AdapterView.OnItemClickListener {

    @Bind(R.id.back) ImageView back;
    @Bind(R.id.search) ImageView search;
    @Bind(R.id.search_text) EditText searchText;
    @Bind(R.id.search_history_result) LinearLayout searchResult;
    @Bind(R.id.search_history_list) ListView searchList;
    @Bind(R.id.clean_history) Button cleanHistory;

    private SearchPer searchPer;
    private SelectHistoryAdapter selectHistoryAdapter;
    private List<SearchHistoryData> searchHistoryDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        searchPer.onResume();
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        back.setOnClickListener(this);
        search.setOnClickListener(this);
        cleanHistory.setOnClickListener(this);
    }

    @Override
    public void initData() {
        searchPer = new SearchPerImpl(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.search:
                searchPer.onClickSearch(searchText.getText().toString());
                break;
            case R.id.clean_history:
                searchPer.onClearHistory();
                break;
            default:
                break;
        }
    }

    @Override
    public void showSearchResult() {
        searchResult.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSearchResult() {
        searchResult.setVisibility(View.GONE);
    }

    @Override
    public void setListItems(List<SearchHistoryData> items) {
        searchHistoryDatas = items;
        selectHistoryAdapter = new SelectHistoryAdapter(this, searchHistoryDatas, searchPer);
        searchList.setAdapter(selectHistoryAdapter);
    }

    @Override
    public void navigateTo() {
        finish();
    }

    @Override
    public void toastSearchNull() {
        Toast.makeText(this, "请输入搜索商品", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cleanAllItem() {
        searchHistoryDatas.clear();
        selectHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void cleanAItem(int position) {
        searchHistoryDatas.remove(position);
        selectHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void upOnText(String search) {
        searchText.setText(search);
    }
}
