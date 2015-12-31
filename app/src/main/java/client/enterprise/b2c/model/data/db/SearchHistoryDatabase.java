package client.enterprise.b2c.model.data.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import client.enterprise.b2c.model.bean.SearchHistoryData;

/**
 * Created by raohoulin on 2015.12.28.
 */
public class SearchHistoryDatabase {
    private DatabaseHelper dbHelper;

    public SearchHistoryDatabase(Context context) {
        super();
        dbHelper = new DatabaseHelper(context);
    }

    /**
     * 增
     *
     * @param data
     */
    public void insert(SearchHistoryData data) {
        String sql = "insert into " + DatabaseHelper.SEARCH_TABLE_NAME + "(search, writeTime) values(?, ?)";

        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        sqlite.execSQL(sql, new String[] {data.getSearch(), data.getWriteTime() + ""});
        sqlite.close();
    }

    /**
     * 删
     * @param id
     */
    public void delete(int id) {
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        String sql = ("delete from " + DatabaseHelper.SEARCH_TABLE_NAME + " where _id=?");
        sqlite.execSQL(sql, new Integer[] { id });
        sqlite.close();
    }

    /**
     * 根据时间删
     * @param time
     */
    public void delete(long time) {
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        String sql = ("delete from " + DatabaseHelper.SEARCH_TABLE_NAME + " where writeTime=?");
        sqlite.execSQL(sql, new Long[] { time });
        sqlite.close();
    }

    /**
     * 删除所有
     */
    public void delete() {
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        String sql = ("delete from " + DatabaseHelper.SEARCH_TABLE_NAME);
        sqlite.execSQL(sql);
        sqlite.close();
    }

    /**
     * 改
     *
     * @param data
     */
    public void update(SearchHistoryData data) {
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        String sql = ("update " + DatabaseHelper.SEARCH_TABLE_NAME + " set search=?, writeTime=? where _id=?");
        sqlite.execSQL(sql,
                new String[] { data.getSearch(), data.getWriteTime() + "", data.getId() + "" });
        sqlite.close();
    }

    /**
     * 查询所有
     * @return
     */
    public List<SearchHistoryData> query() {
        return query(" ");
    }

    /**
     * 自定义条件 查
     * @param where
     * @return
     */
    public List<SearchHistoryData> query(String where) {
        SQLiteDatabase sqlite = dbHelper.getReadableDatabase();
        ArrayList<SearchHistoryData> data = null;
        data = new ArrayList<SearchHistoryData>();
        Cursor cursor = sqlite.rawQuery("select _id, search, writeTime from "
                + DatabaseHelper.SEARCH_TABLE_NAME + where, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            SearchHistoryData searchData = new SearchHistoryData();
            searchData.setId(cursor.getInt(0));
            searchData.setSearch(cursor.getString(1));
            searchData.setWriteTime(cursor.getLong(2));
            data.add(searchData);
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        sqlite.close();

        return data;
    }

    /**
     * 重置
     *
     * @param datas
     */
    public void reset(List<SearchHistoryData> datas) {
        if (datas != null) {
            SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
            // 删除全部
            sqlite.execSQL("delete from " + DatabaseHelper.SEARCH_TABLE_NAME);
            // 重新添加
            for (SearchHistoryData data : datas) {
                insert(data);
            }
            sqlite.close();
        }
    }
}
