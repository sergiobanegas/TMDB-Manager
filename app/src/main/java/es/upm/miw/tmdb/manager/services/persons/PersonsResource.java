package es.upm.miw.tmdb.manager.services.persons;

import android.content.ContentValues;
import android.content.Context;

import java.text.DecimalFormat;
import java.util.List;

import es.upm.miw.tmdb.manager.models.configuration.Configuration;
import es.upm.miw.tmdb.manager.models.responses.movies.MovieData;
import es.upm.miw.tmdb.manager.models.database.KnownFor;
import es.upm.miw.tmdb.manager.models.database.Movie;
import es.upm.miw.tmdb.manager.models.database.Person;
import es.upm.miw.tmdb.manager.services.EntityContract;

public class PersonsResource {

    private PersonsRepository personsRepository;
    private KnownForResource knownForResource;
    private static PersonsResource instance;

    private PersonsResource(Context context) {
        knownForResource = KnownForResource.getInstance(context);
        personsRepository = new PersonsRepository(context);
    }

    public static PersonsResource getInstance(Context context) {
        if (instance == null) {
            instance = new PersonsResource(context);
        }
        return instance;
    }

    public Person findOne(Integer id) {
        return personsRepository.findOne(personsRepository.getReadableDatabase(), id);
    }

    public void insert(int id, String name, String biography, String birthday, String deathday, String placeOfBirth, Double popularity, String smallImage, String bigImage, List<MovieData> knownFors, String directing, String writing) {
        ContentValues values = new ContentValues();
        values.put(EntityContract.personTable.COL_ID, id);
        values.put(EntityContract.personTable.COL_NAME, name);
        values.put(EntityContract.personTable.COL_BIOGRAPHY, biography);
        values.put(EntityContract.personTable.COL_BIRTHDAY, birthday);
        values.put(EntityContract.personTable.COL_DEATHDAY, deathday);
        values.put(EntityContract.personTable.COL_PLACEOFBIRTH, placeOfBirth);
        values.put(EntityContract.personTable.COL_POPULARITY, popularity);
        values.put(EntityContract.personTable.COL_BIGIMAGE, bigImage);
        values.put(EntityContract.personTable.COL_SMALLIMAGE, smallImage);
        personsRepository.getWritableDatabase().insert(EntityContract.personTable.TABLE_NAME, null, values);
        for (MovieData movieData : knownFors) {
            if (movieData!=null){
                knownForResource.insert(id, popularity, new Movie(movieData.getId(), movieData.getTitle(), movieData.getOverview(), directing, writing, movieData.getReleaseDate(), Double.parseDouble(new DecimalFormat("#.##").format(movieData.getPopularity())), Configuration.getInstance().getMoviePosterImageUrl() + movieData.getPosterPath(), Configuration.getInstance().getMovieBackdropImageBigUrl() + movieData.getBackdropPath()));
            }
        }
    }

    public void delete(Integer id) {
        personsRepository.delete(personsRepository.getWritableDatabase(), id);
        List<KnownFor> knownFors = knownForResource.findPersonKnownFors(id);
        for (KnownFor knownfor : knownFors) {
            knownForResource.delete(knownfor);
        }
    }

}
