package es.upm.miw.tmdb.manager.services.persons;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import es.upm.miw.tmdb.manager.models.database.KnownFor;
import es.upm.miw.tmdb.manager.services.EntityContract;

public class KnownForRepository extends SQLiteOpenHelper {

    private static final String DB_NAME = EntityContract.DB_NAME;
    private static final int DB_VERSION = 1;

    public KnownForRepository(Context contexto) {
        super(contexto, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQLQuery = "CREATE TABLE " + EntityContract.personTable.TABLE_NAME + " ("
                + EntityContract.personTable.COL_ID + " INTEGER PRIMARY KEY, "
                + EntityContract.personTable.COL_NAME + " TEXT, "
                + EntityContract.personTable.COL_BIOGRAPHY + " TEXT, "
                + EntityContract.personTable.COL_BIRTHDAY + " TEXT, "
                + EntityContract.personTable.COL_DEATHDAY + " TEXT, "
                + EntityContract.personTable.COL_PLACEOFBIRTH + " TEXT, "
                + EntityContract.personTable.COL_POPULARITY + " REAL, "
                + EntityContract.personTable.COL_BIGIMAGE + " TEXT, "
                + EntityContract.personTable.COL_SMALLIMAGE + " TEXT)";
        db.execSQL(SQLQuery);
        SQLQuery = "CREATE TABLE " + EntityContract.movieTable.TABLE_NAME + " ("
                + EntityContract.movieTable.COL_ID + " INTEGER PRIMARY KEY, "
                + EntityContract.movieTable.COL_TITLE + " TEXT, "
                + EntityContract.movieTable.COL_OVERVIEW + " TEXT, "
                + EntityContract.movieTable.COL_DIRECTING + " TEXT, "
                + EntityContract.movieTable.COL_WRITING + " TEXT, "
                + EntityContract.movieTable.COL_RELEASEDATE + " TEXT, "
                + EntityContract.movieTable.COL_POPULARITY + " TEXT, "
                + EntityContract.movieTable.COL_POSTERIMAGE + " REAL, "
                + EntityContract.movieTable.COL_BACKDROPIMAGE + " TEXT)";
        db.execSQL(SQLQuery);
        SQLQuery = "CREATE TABLE " + EntityContract.knownForTable.TABLE_NAME + " ("
                + EntityContract.knownForTable.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EntityContract.knownForTable.COL_PERSONID + " INTEGER, "
                + EntityContract.knownForTable.COL_PERSONPOPULARITY + " REAL, "
                + EntityContract.knownForTable.COL_MOVIEID + " INTEGER, "
                + EntityContract.knownForTable.COL_MOVIETITLE + " TEXT, "
                + EntityContract.knownForTable.COL_MOVIEOVERVIEW + " TEXT, "
                + EntityContract.knownForTable.COL_MOVIEDIRECTING + " TEXT, "
                + EntityContract.knownForTable.COL_MOVIEWRITING + " TEXT, "
                + EntityContract.knownForTable.COL_MOVIERELEASEDATE + " TEXT, "
                + EntityContract.knownForTable.COL_MOVIEPOPULARITY + " REAL, "
                + EntityContract.knownForTable.COL_MOVIEPOSTERIMAGE + " TEXT, "
                + EntityContract.knownForTable.COL_MOVIEBACKDROPIMAGE + " TEXT)";
        db.execSQL(SQLQuery);
        SQLQuery = "CREATE TABLE " + EntityContract.castTable.TABLE_NAME + " ("
                + EntityContract.castTable.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EntityContract.castTable.COL_MOVIEID + " INTEGER, "
                + EntityContract.castTable.COL_PERSONID + " INTEGER, "
                + EntityContract.castTable.COL_PERSONNAME + " TEXT, "
                + EntityContract.castTable.COL_PERSONCHARACTER + " TEXT, "
                + EntityContract.castTable.COL_PERSONBIOGRAPHY + " TEXT, "
                + EntityContract.castTable.COL_PERSONBIRTHDAY + " TEXT, "
                + EntityContract.castTable.COL_PERSONDEATHDAY + " TEXT, "
                + EntityContract.castTable.COL_PERSONPLACEOFBIRTH + " TEXT, "
                + EntityContract.castTable.COL_PERSONPOPULARITY + " TEXT, "
                + EntityContract.castTable.COL_PERSONBIGIMAGE + " TEXT, "
                + EntityContract.castTable.COL_PERSONSMALLIMAGE + " TEXT)";
        db.execSQL(SQLQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQLQuery = "DROP TABLE IF EXISTS " + EntityContract.personTable.TABLE_NAME;
        db.execSQL(SQLQuery);
        onCreate(db);
        SQLQuery = "DROP TABLE IF EXISTS " + EntityContract.movieTable.TABLE_NAME;
        db.execSQL(SQLQuery);
        onCreate(db);
        SQLQuery = "DROP TABLE IF EXISTS " + EntityContract.knownForTable.TABLE_NAME;
        db.execSQL(SQLQuery);
        onCreate(db);
    }

    public List<KnownFor> findPersonKnownFors(SQLiteDatabase db, Integer id) {
        String[] args = new String[]{String.valueOf(id)};
        String[] fields = new String[]{EntityContract.knownForTable.COL_ID, EntityContract.knownForTable.COL_PERSONID, EntityContract.knownForTable.COL_MOVIEID, EntityContract.knownForTable.COL_MOVIETITLE, EntityContract.knownForTable.COL_MOVIEOVERVIEW, EntityContract.knownForTable.COL_MOVIEDIRECTING, EntityContract.knownForTable.COL_MOVIEWRITING, EntityContract.knownForTable.COL_MOVIERELEASEDATE, EntityContract.knownForTable.COL_MOVIEPOPULARITY, EntityContract.knownForTable.COL_MOVIEPOSTERIMAGE, EntityContract.knownForTable.COL_MOVIEBACKDROPIMAGE};
        Cursor cursor = db.query(EntityContract.knownForTable.TABLE_NAME, fields, EntityContract.knownForTable.COL_PERSONID + "=?", args, null, null, null);
        if (cursor.moveToFirst()) {
            List<KnownFor> knownForsIds = new ArrayList<>();
            do {
                int knownForId = cursor.getInt(cursor.getColumnIndex(EntityContract.knownForTable.COL_ID));
                int personId = cursor.getInt(cursor.getColumnIndex(EntityContract.knownForTable.COL_PERSONID));
                int movieId = cursor.getInt(cursor.getColumnIndex(EntityContract.knownForTable.COL_MOVIEID));
                String movieTitle = cursor.getString(cursor.getColumnIndex(EntityContract.knownForTable.COL_MOVIETITLE));
                String movieOverview = cursor.getString(cursor.getColumnIndex(EntityContract.knownForTable.COL_MOVIEOVERVIEW));
                String movieDirecting = cursor.getString(cursor.getColumnIndex(EntityContract.knownForTable.COL_MOVIEDIRECTING));
                String movieWriting = cursor.getString(cursor.getColumnIndex(EntityContract.knownForTable.COL_MOVIEWRITING));
                String movieReleaseDate = cursor.getString(cursor.getColumnIndex(EntityContract.knownForTable.COL_MOVIERELEASEDATE));
                Double moviePopularity = cursor.getDouble(cursor.getColumnIndex(EntityContract.knownForTable.COL_MOVIEPOPULARITY));
                String moviePosterImage = cursor.getString(cursor.getColumnIndex(EntityContract.knownForTable.COL_MOVIEPOSTERIMAGE));
                String movieBackdropImage = cursor.getString(cursor.getColumnIndex(EntityContract.knownForTable.COL_MOVIEPOSTERIMAGE));
                knownForsIds.add(new KnownFor(knownForId, personId, movieId, movieTitle, movieOverview, movieDirecting, movieWriting, movieReleaseDate, moviePopularity, moviePosterImage, movieBackdropImage));
            } while (cursor.moveToNext());
            return knownForsIds;
        } else {
            return new ArrayList<>();
        }
    }


}
