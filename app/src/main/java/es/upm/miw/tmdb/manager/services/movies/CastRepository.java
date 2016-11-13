package es.upm.miw.tmdb.manager.services.movies;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import es.upm.miw.tmdb.manager.models.database.Cast;
import es.upm.miw.tmdb.manager.services.EntityContract;

import static es.upm.miw.tmdb.manager.services.EntityContract.knownForTable;
import static es.upm.miw.tmdb.manager.services.EntityContract.movieTable;
import static es.upm.miw.tmdb.manager.services.EntityContract.personTable;
import static es.upm.miw.tmdb.manager.services.EntityContract.castTable;

public class CastRepository extends SQLiteOpenHelper {

    private static final String DB_NAME = EntityContract.DB_NAME;
    private static final int DB_VERSION = 1;
    private static final String[] fields = new String[]{castTable.COL_ID, castTable.COL_MOVIEID, castTable.COL_PERSONID, castTable.COL_PERSONNAME, castTable.COL_PERSONCHARACTER, castTable.COL_PERSONBIOGRAPHY, castTable.COL_PERSONBIRTHDAY, castTable.COL_PERSONDEATHDAY, castTable.COL_PERSONPLACEOFBIRTH, castTable.COL_PERSONPOPULARITY, castTable.COL_PERSONBIGIMAGE, castTable.COL_PERSONSMALLIMAGE};

    public CastRepository(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQLQuery = "CREATE TABLE " + personTable.TABLE_NAME + " ("
                + personTable.COL_ID + " INTEGER PRIMARY KEY, "
                + personTable.COL_NAME + " TEXT, "
                + personTable.COL_BIOGRAPHY + " TEXT, "
                + personTable.COL_BIRTHDAY + " TEXT, "
                + personTable.COL_DEATHDAY + " TEXT, "
                + personTable.COL_PLACEOFBIRTH + " TEXT, "
                + personTable.COL_POPULARITY + " REAL, "
                + personTable.COL_BIGIMAGE + " TEXT, "
                + personTable.COL_SMALLIMAGE + " TEXT)";
        db.execSQL(SQLQuery);
        SQLQuery = "CREATE TABLE " + movieTable.TABLE_NAME + " ("
                + movieTable.COL_ID + " INTEGER PRIMARY KEY, "
                + movieTable.COL_TITLE + " TEXT, "
                + movieTable.COL_OVERVIEW + " TEXT, "
                + movieTable.COL_DIRECTING + " TEXT, "
                + movieTable.COL_WRITING + " TEXT, "
                + movieTable.COL_RELEASEDATE + " TEXT, "
                + movieTable.COL_POPULARITY + " TEXT, "
                + movieTable.COL_POSTERIMAGE + " REAL, "
                + movieTable.COL_BACKDROPIMAGE + " TEXT)";
        db.execSQL(SQLQuery);
        SQLQuery = "CREATE TABLE " + knownForTable.TABLE_NAME + " ("
                + knownForTable.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + knownForTable.COL_PERSONID + " INTEGER, "
                + knownForTable.COL_PERSONPOPULARITY + " REAL, "
                + knownForTable.COL_MOVIEID + " INTEGER, "
                + knownForTable.COL_MOVIETITLE + " TEXT, "
                + knownForTable.COL_MOVIEOVERVIEW + " TEXT, "
                + knownForTable.COL_MOVIEDIRECTING + " TEXT, "
                + knownForTable.COL_MOVIEWRITING + " TEXT, "
                + knownForTable.COL_MOVIERELEASEDATE + " TEXT, "
                + knownForTable.COL_MOVIEPOPULARITY + " REAL, "
                + knownForTable.COL_MOVIEPOSTERIMAGE + " TEXT, "
                + knownForTable.COL_MOVIEBACKDROPIMAGE + " TEXT)";
        db.execSQL(SQLQuery);
        SQLQuery = "CREATE TABLE " + castTable.TABLE_NAME + " ("
                + castTable.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + castTable.COL_MOVIEID + " INTEGER, "
                + castTable.COL_PERSONID + " INTEGER, "
                + castTable.COL_PERSONNAME + " TEXT, "
                + castTable.COL_PERSONCHARACTER + " TEXT, "
                + castTable.COL_PERSONBIOGRAPHY + " TEXT, "
                + castTable.COL_PERSONBIRTHDAY + " TEXT, "
                + castTable.COL_PERSONDEATHDAY + " TEXT, "
                + castTable.COL_PERSONPLACEOFBIRTH + " TEXT, "
                + castTable.COL_PERSONPOPULARITY + " TEXT, "
                + castTable.COL_PERSONBIGIMAGE + " TEXT, "
                + castTable.COL_PERSONSMALLIMAGE + " TEXT)";
        db.execSQL(SQLQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQLQuery = "DROP TABLE IF EXISTS " + personTable.TABLE_NAME;
        db.execSQL(SQLQuery);
        onCreate(db);
        SQLQuery = "DROP TABLE IF EXISTS " + movieTable.TABLE_NAME;
        db.execSQL(SQLQuery);
        onCreate(db);
        SQLQuery = "DROP TABLE IF EXISTS " + knownForTable.TABLE_NAME;
        db.execSQL(SQLQuery);
        onCreate(db);
        SQLQuery = "DROP TABLE IF EXISTS " + castTable.TABLE_NAME;
        db.execSQL(SQLQuery);
        onCreate(db);
    }

    public List<Cast> findMovieCast(SQLiteDatabase db, Integer id) {
        String[] args = new String[]{String.valueOf(id)};
        Cursor cursor = db.query(castTable.TABLE_NAME, fields, castTable.COL_MOVIEID + "=?", args, null, null, null);
        if (cursor.moveToFirst()) {
            List<Cast> cast = new ArrayList<>();
            do {
                int movieId = cursor.getInt(cursor.getColumnIndex(castTable.COL_MOVIEID));
                int personId = cursor.getInt(cursor.getColumnIndex(castTable.COL_PERSONID));
                String personName = cursor.getString(cursor.getColumnIndex(castTable.COL_PERSONNAME));
                String personCharacter = cursor.getString(cursor.getColumnIndex(castTable.COL_PERSONCHARACTER));
                String personBiography = cursor.getString(cursor.getColumnIndex(castTable.COL_PERSONBIOGRAPHY));
                String personBirthday = cursor.getString(cursor.getColumnIndex(castTable.COL_PERSONBIRTHDAY));
                String personDeathday = cursor.getString(cursor.getColumnIndex(castTable.COL_PERSONDEATHDAY));
                String personPlaceOfBirth = cursor.getString(cursor.getColumnIndex(castTable.COL_PERSONPLACEOFBIRTH));
                Double personPopularity = cursor.getDouble(cursor.getColumnIndex(castTable.COL_PERSONPOPULARITY));
                String personBigImage = cursor.getString(cursor.getColumnIndex(castTable.COL_PERSONBIGIMAGE));
                String personSmallImage = cursor.getString(cursor.getColumnIndex(castTable.COL_PERSONSMALLIMAGE));
                cast.add(new Cast(id, movieId, personId, personName, personCharacter, personBiography, personBirthday, personDeathday, personPlaceOfBirth, personPopularity, personBigImage, personSmallImage));
            } while (cursor.moveToNext());
            cursor.close();
            return cast;
        } else {
            cursor.close();
            return new ArrayList<>();
        }
    }


}
