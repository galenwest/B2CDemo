package client.enterprise.b2c.model.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by raohoulin on 2015.12.28.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String BTC_DATABASE_NAME = "btoc.db";
    public static final String SEARCH_TABLE_NAME = "search_history";

    public static String CREATE_HISTORY = "create table " +
            SEARCH_TABLE_NAME +
            "(_id integer primary key autoincrement, " +
            "search text, " +
            "writeTime integer)";

    public DatabaseHelper(Context context) {
        super(context, BTC_DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_HISTORY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                db.execSQL(CREATE_HISTORY);
            default:
        }
    }
}
