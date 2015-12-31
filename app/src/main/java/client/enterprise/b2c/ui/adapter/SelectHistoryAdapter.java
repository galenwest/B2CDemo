package client.enterprise.b2c.ui.adapter;

import android.content.Context;
import android.media.Image;
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
import client.enterprise.b2c.ui.activity.SearchActivity;

/**
 * Created by raohoulin on 2015.12.29.
 */
public class SelectHistoryAdapter extends BaseAdapter {
    public List<SearchHistoryData> searchData;
    private Context context;
    private SearchPer searchPer;

    private upSearchOnEditTextListener upListener;

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
            viewHolder.upItem = (ImageView) convertView.findViewById(R.id.up_item);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // set item values to the viewHolder:
        SearchHistoryData historyData = getItem(position);
        if (null != historyData) {
            viewHolder.searchItem.setText(historyData.getSearch());
            viewHolder.deleteItem.setOnClickListener(new DeleteListener(position, historyData.getWriteTime()));
            viewHolder.upItem.setOnClickListener(new DeleteListener(position));
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView searchItem;
        ImageView deleteItem;
        ImageView upItem;
    }

    private class DeleteListener implements View.OnClickListener{
        int position;
        long time;

        public DeleteListener(int position) {
            this.position = position;
        }

        public DeleteListener(int position, long time) {
            this.position = position;
            this.time = time;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.delete_item:
                    searchPer.onClearItemHistory(position, time);
                    break;
                case R.id.up_item:
                    SelectHistoryAdapter.this.setUpSearchOnEditTextListener((SearchActivity)context);
                    upListener.upOnText(getItem(position).getSearch());
                    break;
                default:
                    break;
            }
        }
    }

    public void setUpSearchOnEditTextListener(upSearchOnEditTextListener upListener) {
        this.upListener = upListener;
    }

    public interface upSearchOnEditTextListener {
        void upOnText(String search);
    }
}
