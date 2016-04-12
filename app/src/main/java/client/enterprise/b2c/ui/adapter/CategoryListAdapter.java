package client.enterprise.b2c.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import client.enterprise.b2c.R;
import client.enterprise.b2c.model.bean.po.Category;

/**
 * Created by raohoulin on 2016.2.4.
 */
public class CategoryListAdapter extends BaseAdapter {
    private List<Category> categoryData;
    private Context context;

    public CategoryListAdapter(Context context, List<Category> categoryData) {
        this.context = context;
        this.categoryData = categoryData;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (null != categoryData) {
            count = categoryData.size();
        }
        return count;
    }

    @Override
    public Category getItem(int position) {
        Category item = null;

        if (null != categoryData) {
            item = categoryData.get(position);
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
            convertView = mInflater.inflate(R.layout.item_category_list, null);

            viewHolder.categoryItem = (TextView) convertView.findViewById(R.id.category_name);
            viewHolder.selectedBg = convertView.findViewById(R.id.selected_bg);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // set item values to the viewHolder:
        Category categoryData = getItem(position);
        if (null != categoryData) {
            viewHolder.categoryItem.setText(categoryData.getName());
        }

        return convertView;
    }

    private static class ViewHolder {
        View selectedBg;
        TextView categoryItem;
    }
}
