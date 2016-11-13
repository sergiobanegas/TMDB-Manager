package es.upm.miw.tmdb.manager.services.persons;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import es.upm.miw.tmdb.manager.models.database.KnownFor;
import es.upm.miw.tmdb.manager.models.database.Movie;
import es.upm.miw.tmdb.manager.models.database.Person;
import es.upm.miw.tmdb.manager.services.EntityContract;
import es.upm.miw.tmdb.manager.services.movies.MoviesResource;

public class PersonsRepository extends SQLiteOpenHelper {

    private static final String DB_NAME = EntityContract.DB_NAME;
    private static final int DB_VERSION = 1;
    private KnownForResource knownForResource;
    private MoviesResource moviesResource;

    public PersonsRepository(Context contexto) {
        super(contexto, DB_NAME, null, DB_VERSION);
        this.moviesResource = MoviesResource.getInstance(contexto);
        this.knownForResource = KnownForResource.getInstance(contexto);
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
        SQLQuery = "DROP TABLE IF EXISTS " + EntityContract.castTable.TABLE_NAME;
        db.execSQL(SQLQuery);
        onCreate(db);
    }

    public Person findOne(SQLiteDatabase db, Integer id) {
        String[] args = new String[]{String.valueOf(id)};
        String[] fields = new String[]{EntityContract.personTable.COL_ID, EntityContract.personTable.COL_NAME, EntityContract.personTable.COL_BIOGRAPHY, EntityContract.personTable.COL_BIRTHDAY, EntityContract.personTable.COL_DEATHDAY, EntityContract.personTable.COL_PLACEOFBIRTH, EntityContract.personTable.COL_POPULARITY, EntityContract.personTable.COL_BIGIMAGE, EntityContract.personTable.COL_SMALLIMAGE};
        Cursor cursor = db.query(EntityContract.personTable.TABLE_NAME, fields, "_id=?", args, null, null, null);
        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex(EntityContract.personTable.COL_NAME));
            String biography = cursor.getString(cursor.getColumnIndex(EntityContract.personTable.COL_BIOGRAPHY));
            String birthday = cursor.getString(cursor.getColumnIndex(EntityContract.personTable.COL_BIRTHDAY));
            String deathday = cursor.getString(cursor.getColumnIndex(EntityContract.personTable.COL_DEATHDAY));
            String placeOfBirth = cursor.getString(cursor.getColumnIndex(EntityContract.personTable.COL_PLACEOFBIRTH));
            Double popularity = cursor.getDouble(cursor.getColumnIndex(EntityContract.personTable.COL_POPULARITY));
            String imageBig = cursor.getString(cursor.getColumnIndex(EntityContract.personTable.COL_BIGIMAGE));
            String imageSmall = cursor.getString(cursor.getColumnIndex(EntityContract.personTable.COL_SMALLIMAGE));
            List<KnownFor> knownFors = knownForResource.findPersonKnownFors(id);
            List<Movie> moviesKnownFor = new ArrayList<>();
            if (knownFors.size() > 0) {
                for (KnownFor knownFor : knownFors) {
                    Movie movie = moviesResource.findOne(knownFor.getMovieId());
                    if (movie != null) {
                        moviesKnownFor.add(movie);
                    }
                }
            }
            return new Person(id, name, biography, birthday, deathday, placeOfBirth, popularity, imageBig, imageSmall, moviesKnownFor);
        } else {
            return null;
        }
    }

    public void delete(SQLiteDatabase db, Integer id) {
        List<KnownFor> knownFors = knownForResource.findPersonKnownFors(id);
        for (KnownFor knownFor : knownFors) {
            knownForResource.deletePerson(knownFor.getPersonId());
        }
        db.delete(EntityContract.personTable.TABLE_NAME, EntityContract.personTable.COL_ID + "=" + id, null);
    }

}
