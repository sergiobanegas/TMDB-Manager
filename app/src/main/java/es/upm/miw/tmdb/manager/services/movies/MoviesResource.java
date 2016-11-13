package es.upm.miw.tmdb.manager.services.movies;

import android.content.ContentValues;
import android.content.Context;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import es.upm.miw.tmdb.manager.models.responses.movies.Cast;
import es.upm.miw.tmdb.manager.models.configuration.Configuration;
import es.upm.miw.tmdb.manager.models.responses.persons.PersonData;
import es.upm.miw.tmdb.manager.models.database.Movie;
import es.upm.miw.tmdb.manager.models.database.Person;
import es.upm.miw.tmdb.manager.services.EntityContract;

public class MoviesResource {

    private MoviesRepository re;
    private CastResource castResource;
    private static MoviesResource instance;


    private MoviesResource(Context context) {
        castResource = CastResource.getInstance(context);
        re = new MoviesRepository(context, CastResource.getInstance(context));
    }

    public static MoviesResource getInstance(Context context) {
        if (instance == null) {
            instance = new MoviesResource(context);
        }
        return instance;
    }

    public Movie findOne(Integer id) {
        return re.findOne(re.getReadableDatabase(), id);
    }

    public void insert(int id, String title, String overview, String directing, String writing, String releaseDate, Double popularity, String posterImage, String backdropImage, List<Cast> castList, List<PersonData> personList) {
        ContentValues values = new ContentValues();
        values.put(EntityContract.movieTable.COL_ID, id);
        values.put(EntityContract.movieTable.COL_TITLE, title);
        values.put(EntityContract.movieTable.COL_OVERVIEW, overview);
        values.put(EntityContract.movieTable.COL_DIRECTING, directing);
        values.put(EntityContract.movieTable.COL_WRITING, writing);
        values.put(EntityContract.movieTable.COL_RELEASEDATE, releaseDate);
        values.put(EntityContract.movieTable.COL_POPULARITY, Double.parseDouble(new DecimalFormat("#.##").format(popularity)));
        values.put(EntityContract.movieTable.COL_POSTERIMAGE, posterImage);
        values.put(EntityContract.movieTable.COL_BACKDROPIMAGE, backdropImage);
        re.getWritableDatabase().insert(EntityContract.movieTable.TABLE_NAME, null, values);
        for (int i = 0; i < personList.size(); i++) {
            if (castList.get(i) != null) {
                PersonData personData = personList.get(i);
                castResource.insert(id, castList.get(i).getCharacter(), new Person(personData.getId(), personData.getName(), personData.getBiography(), personData.getBirthday(), personData.getDeathday(), personData.getPlaceOfBirth(), personData.getPopularity(), Configuration.getInstance().getPersonProfileImageBigUrl() + personData.getProfilePath(), Configuration.getInstance().getPersonProfileImageSmallUrl() + personData.getProfilePath(), new ArrayList<Movie>()));
            }
        }
    }

    public void delete(Integer id) {
        re.delete(re.getWritableDatabase(), id);
        List<es.upm.miw.tmdb.manager.models.database.Cast> cast = castResource.getMovieCast(id);
        for (es.upm.miw.tmdb.manager.models.database.Cast personCast : cast) {
            castResource.delete(personCast);
        }
    }

}
