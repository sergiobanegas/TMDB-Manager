package es.upm.miw.tmdb.manager.services.persons;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import es.upm.miw.tmdb.manager.models.database.KnownFor;
import es.upm.miw.tmdb.manager.models.database.Movie;
import es.upm.miw.tmdb.manager.services.EntityContract;

public class KnownForResource {

    private KnownForRepository knownForRepository;
    private static KnownForResource instance;

    private KnownForResource(Context context) {
        knownForRepository = new KnownForRepository(context);
    }

    public static KnownForResource getInstance(Context context) {
        if (instance==null){
            instance = new KnownForResource(context);
        }
        return instance;
    }

    public void insert(int personId, Double personPopularity, Movie movie) {
        ContentValues values = new ContentValues();
        values.put(EntityContract.knownForTable.COL_PERSONID, personId);
        values.put(EntityContract.knownForTable.COL_PERSONPOPULARITY, personPopularity);
        values.put(EntityContract.knownForTable.COL_MOVIEID, movie.getId());
        values.put(EntityContract.knownForTable.COL_MOVIETITLE, movie.getTitle());
        values.put(EntityContract.knownForTable.COL_MOVIEOVERVIEW, movie.getOverview());
        values.put(EntityContract.knownForTable.COL_MOVIEDIRECTING, movie.getDirecting());
        values.put(EntityContract.knownForTable.COL_MOVIEWRITING, movie.getWriting());
        values.put(EntityContract.knownForTable.COL_MOVIERELEASEDATE, movie.getReleaseDate());
        values.put(EntityContract.knownForTable.COL_MOVIEPOPULARITY, movie.getPopularity());
        values.put(EntityContract.knownForTable.COL_MOVIEPOSTERIMAGE, movie.getPosterImage());
        values.put(EntityContract.knownForTable.COL_MOVIEBACKDROPIMAGE, movie.getBackDropImage());
        knownForRepository.getWritableDatabase().insert(EntityContract.knownForTable.TABLE_NAME, null, values);
    }

    public void delete(KnownFor knownFor) {
        SQLiteDatabase db = knownForRepository.getWritableDatabase();
        db.delete(EntityContract.knownForTable.TABLE_NAME, EntityContract.knownForTable.COL_ID + "=" + knownFor.getId(), null);
    }

    public List<KnownFor> findPersonKnownFors(Integer id) {
        return knownForRepository.findPersonKnownFors(knownForRepository.getReadableDatabase(), id);
    }

    public void deletePerson(Integer id) {
        SQLiteDatabase db = knownForRepository.getWritableDatabase();
        db.delete(EntityContract.knownForTable.TABLE_NAME, EntityContract.knownForTable.COL_PERSONID + "=" + id, null);
    }

}
