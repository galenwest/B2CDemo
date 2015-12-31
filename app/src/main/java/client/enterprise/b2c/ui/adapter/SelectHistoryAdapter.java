package client.enterprise.b2c.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import client.enterprise.b2c.AppContext;
import client.enterprise.b2c.R;
import client.enterprise.b2c.model.bean.SearchHistoryData;
import client.enterprise.b2c.presenter.SearchPer;

/**
 * Created by raohoulin on 2015.12.29.
 */
public class SelectHistoryAdapter extends BaseAdapter {
    public List<SearchHistoryData> searchData;
    private Context context;
    private SearchPer searchPer;

    public SelectHistoryAdapter(Context context, List<SearchHistoryData> searchData, SearchPer searchPer) {
        this.context = context;
        this.searchData = searchData;
        this.searchPer = searchPer;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (null != searchData) {
            count = searchData.size();
        }
        return count;
    }

    @Override
    public SearchHistoryData getItem(int position) {
        SearchHistoryData item = null;

        if (null != searchData) {
            item = searchData.get(position);
        }

        return item;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(R.layout.item_search_histroy, null);

            viewHolder.searchItem = (TextView) convertView.findViewById(R.id.search_item);
            viewHolder.deleteItem = (ImageView) convertView.findViewById(R.id.delete_item);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // set item values to the viewHolder:

        SearchHistoryData historyData = getItem(position);
        if (null != historyData) {
            viewHolder.searchItem.setText(historyData.getSearch());
            viewHolder.deleteItem.setOnClickListener(new DeleteListener(position, historyData.getWriteTime()));
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView searchItem;
        ImageView deleteItem;
    }

    private class DeleteListener implements View.OnClickListener{
        int position;
        long time;

        public DeleteListener(int position, long time) {
            this.position = position;
            this.time = time;
        }

        @Override
        public void onClick(View v) {
            searchPer.onClearItemHistory(position, time);
        }
    }
}
