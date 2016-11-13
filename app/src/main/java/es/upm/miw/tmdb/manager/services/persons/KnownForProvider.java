package es.upm.miw.tmdb.manager.services.persons;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import static es.upm.miw.tmdb.manager.services.EntityContract.knownForTable;

public class KnownForProvider extends ContentProvider {

    private static final String authority = KnownForProvider.class.getPackage().getName() + ".kwownfor.provider";
    private static final String entity = "content://" + authority + "/knownfor";
    private static UriMatcher uriMatcher;
    private static final int ID_URI_ENTITIES = 0;
    private static final int ID_URI_UNIQUE_ENTITY = 1;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(authority, entity, ID_URI_ENTITIES);
        uriMatcher.addURI(authority, entity + "/#", ID_URI_UNIQUE_ENTITY);
    }

    private KnownForRepository knownForRepository;

    @Override
    public boolean onCreate() {
        knownForRepository = new KnownForRepository(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String where = selection;
        if (uriMatcher.match(uri) == ID_URI_UNIQUE_ENTITY) {
            where = "id = " + uri.getLastPathSegment();
        }
        SQLiteDatabase db = knownForRepository.getReadableDatabase();
        Cursor cursor = db.query(knownForTable.TABLE_NAME, projection, where, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = knownForRepository.getWritableDatabase();
        long id = db.insert(knownForTable.TABLE_NAME, null, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String where = selection;
        int rowsAffected;
        if (where != null && selectionArgs.length > 0) {
            where = selection + selectionArgs[0];
        }
        SQLiteDatabase db = knownForRepository.getWritableDatabase();
        rowsAffected = db.delete(knownForTable.TABLE_NAME, where, null);
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsAffected;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        String where = selection;
        int rowsAffected = 0;
        if (where != null && selectionArgs.length > 0) {
            where = selection + selectionArgs[0];
            SQLiteDatabase db = knownForRepository.getWritableDatabase();
            rowsAffected = db.update(knownForTable.TABLE_NAME, values, where, null);
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsAffected;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case ID_URI_ENTITIES:
                return "vnd.android.cursor.dir/vnd.es.upm.miw.provider.persons.knownfor";
            case ID_URI_UNIQUE_ENTITY:
                return "vnd.android.cursor.item/vnd.es.upm.miw.provider.persons.kwownfor";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

}
