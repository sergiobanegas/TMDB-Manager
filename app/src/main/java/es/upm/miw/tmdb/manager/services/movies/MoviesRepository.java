package es.upm.miw.tmdb.manager.services.movies;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import es.upm.miw.tmdb.manager.models.database.Cast;
import es.upm.miw.tmdb.manager.models.database.Movie;
import es.upm.miw.tmdb.manager.services.EntityContract;

import static es.upm.miw.tmdb.manager.services.EntityContract.movieTable;
import static es.upm.miw.tmdb.manager.services.EntityContract.personTable;
import static es.upm.miw.tmdb.manager.services.EntityContract.knownForTable;
import static es.upm.miw.tmdb.manager.services.EntityContract.castTable;

public class MoviesRepository extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private CastResource castResource;

    public MoviesRepository(Context contexto, CastResource castResource) {
        super(contexto,  EntityContract.DB_NAME, null, DB_VERSION);
        this.castResource = castResource;
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
        String SQLQuery = "DROP TABLE IF EXISTS " + movieTable.TABLE_NAME;
        db.execSQL(SQLQuery);
        onCreate(db);
        SQLQuery = "DROP TABLE IF EXISTS " + personTable.TABLE_NAME;
        db.execSQL(SQLQuery);
        onCreate(db);
        SQLQuery = "DROP TABLE IF EXISTS " + knownForTable.TABLE_NAME;
        db.execSQL(SQLQuery);
        onCreate(db);
        SQLQuery = "DROP TABLE IF EXISTS " + castTable.TABLE_NAME;
        db.execSQL(SQLQuery);
        onCreate(db);
    }

    public Movie findOne(SQLiteDatabase db, Integer id) {
        String[] args = new String[]{String.valueOf(id)};
        String[] fields = new String[]{movieTable.COL_ID, movieTable.COL_TITLE, movieTable.COL_OVERVIEW, movieTable.COL_DIRECTING, movieTable.COL_WRITING, movieTable.COL_RELEASEDATE, movieTable.COL_POPULARITY, movieTable.COL_POSTERIMAGE, movieTable.COL_BACKDROPIMAGE};
        Cursor cursor = db.query(movieTable.TABLE_NAME, fields, "_id=?", args, null, null, null);
        if (cursor.moveToFirst()) {
            String title = cursor.getString(cursor.getColumnIndex(movieTable.COL_TITLE));
            String overview = cursor.getString(cursor.getColumnIndex(movieTable.COL_OVERVIEW));
            String directing = cursor.getString(cursor.getColumnIndex(movieTable.COL_DIRECTING));
            String writing = cursor.getString(cursor.getColumnIndex(movieTable.COL_WRITING));
            String releaseDate = cursor.getString(cursor.getColumnIndex(movieTable.COL_RELEASEDATE));
            Double popularity = cursor.getDouble(cursor.getColumnIndex(movieTable.COL_POPULARITY));
            String posterImage = cursor.getString(cursor.getColumnIndex(movieTable.COL_POSTERIMAGE));
            String backdropImage = cursor.getString(cursor.getColumnIndex(movieTable.COL_BACKDROPIMAGE));
            return new Movie(id, title, overview, directing, writing, releaseDate, popularity, posterImage, backdropImage);
        } else {
            return null;
        }
    }

    public void delete(SQLiteDatabase db, Integer id) {
        List<Cast> cast = castResource.getMovieCast(id);
        for (Cast movieCast : cast) {
            castResource.deleteMovie(movieCast.getMovieId());
        }
        db.delete(movieTable.TABLE_NAME, movieTable.COL_ID + "=" + id, null);
    }
}
