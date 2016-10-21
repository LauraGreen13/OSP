package ro.cs.upb.osp.lab2;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class ItemsProvider extends ContentProvider {

    public static final int TABLE = 1;
    public static final int ROW = 2;

    // Change the uriRoot to the proper package NAME
    public static final String uriRoot = "ro.cs.upb.osp.lab2";
    public static final String tableUri = "content://" + uriRoot + "/menu";

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static{
        uriMatcher.addURI(uriRoot, "menu", TABLE);
        uriMatcher.addURI(uriRoot, "menu/#", TABLE);
    }

    private DatabaseHelper dbHelper;

    public ItemsProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int ret = db.delete("menu", null, null);

        if (ret >= 0) {
            // Appends the URI to the tableUri path
            Uri noteUri = ContentUris.withAppendedId(Uri.parse(tableUri), ret);
            getContext().getContentResolver().notifyChange(noteUri, null);
            return ret;
        } else {
            throw new UnsupportedOperationException("Failed to delete " + uri);
        }
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.

        if (uriMatcher.match(uri) != TABLE) {
            throw new UnsupportedOperationException("Should insert to menu table");
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long rowId = db.insert("menu", null, values);
        if (rowId >= 0) {
            // Appends the URI to the tableUri path
            Uri noteUri = ContentUris.withAppendedId(Uri.parse(tableUri), rowId);
            getContext().getContentResolver().notifyChange(noteUri, null);
            return noteUri;
        } else {
            throw new UnsupportedOperationException("Failed to insert row into " + uri);
        }
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.

        dbHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables("menu");

        switch (uriMatcher.match(uri)) {
            case TABLE:
                break;
            case ROW:
                queryBuilder.appendWhereEscapeString("id = " + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("WRONG URI: " + uri);
        }

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        return queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
