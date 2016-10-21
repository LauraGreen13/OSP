package ro.cs.upb.osp.lab2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @version $Id$
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "menu.db";
    private static final int DB_VERSION = 1;

    protected final static String NAME = "name";
    protected final static String DESCRIPTION = "desc";
    protected final static String PRICE = "price";

    public DatabaseHelper(Context context) {
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS menu (" +
                "id INTEGER PRIMARY KEY," +
                NAME + " TEXT," +
                DESCRIPTION + " TEXT," +
                PRICE + " INTEGER" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS menu");
        onCreate(db);
    }
}
