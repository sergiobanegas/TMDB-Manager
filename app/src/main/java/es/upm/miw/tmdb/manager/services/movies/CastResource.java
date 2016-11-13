package es.upm.miw.tmdb.manager.services.movies;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import es.upm.miw.tmdb.manager.models.database.Cast;
import es.upm.miw.tmdb.manager.models.database.Person;
import es.upm.miw.tmdb.manager.services.EntityContract;

public class CastResource {

    private CastRepository re;
    private static CastResource instance;

    private CastResource(Context context) {
        re = new CastRepository(context);
    }

    public static CastResource getInstance(Context context) {
        if (instance == null) {
            instance = new CastResource(context);
        }
        return instance;
    }

    public void insert(int movieId, String character, Person person) {
        ContentValues values = new ContentValues();
        values.put(EntityContract.castTable.COL_MOVIEID, movieId);
        values.put(EntityContract.castTable.COL_PERSONID, person.getId());
        values.put(EntityContract.castTable.COL_PERSONNAME, person.getName());
        values.put(EntityContract.castTable.COL_PERSONCHARACTER, character);
        values.put(EntityContract.castTable.COL_PERSONBIOGRAPHY, person.getBiography());
        values.put(EntityContract.castTable.COL_PERSONBIRTHDAY, person.getBirthday());
        values.put(EntityContract.castTable.COL_PERSONDEATHDAY, person.getDeathday());
        values.put(EntityContract.castTable.COL_PERSONPLACEOFBIRTH, person.getPlaceOfBirth());
        values.put(EntityContract.castTable.COL_PERSONPOPULARITY, person.getPopularity());
        values.put(EntityContract.castTable.COL_PERSONSMALLIMAGE, person.getSmallImage());
        values.put(EntityContract.castTable.COL_PERSONBIGIMAGE, person.getBigImage());
        re.getWritableDatabase().insert(EntityContract.castTable.TABLE_NAME, null, values);
    }

    public void delete(Cast cast) {
        SQLiteDatabase db = re.getWritableDatabase();
        db.delete(EntityContract.castTable.TABLE_NAME, EntityContract.castTable.COL_ID + "=" + cast.getId(), null);
    }

    public List<Cast> getMovieCast(Integer id) {
        return re.findMovieCast(re.getReadableDatabase(), id);
    }

    public void deleteMovie(Integer id) {
        SQLiteDatabase db = re.getWritableDatabase();
        db.delete(EntityContract.castTable.TABLE_NAME, EntityContract.castTable.COL_MOVIEID + "=" + id, null);
    }
}
