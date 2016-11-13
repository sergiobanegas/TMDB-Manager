package es.upm.miw.tmdb.manager.services;

import es.upm.miw.tmdb.manager.models.responses.post.APIPOSTResponse;
import es.upm.miw.tmdb.manager.models.responses.sessions.APIConfiguration;
import es.upm.miw.tmdb.manager.models.responses.movies.Credits;
import es.upm.miw.tmdb.manager.models.responses.movies.MovieData;
import es.upm.miw.tmdb.manager.models.responses.movies.Movies;
import es.upm.miw.tmdb.manager.models.responses.persons.PersonData;
import es.upm.miw.tmdb.manager.models.responses.persons.Persons;
import es.upm.miw.tmdb.manager.models.responses.sessions.SessionResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDBRESTAPIService {

    String api_key = "6c799f4acc31c6e31086ca862eb21504";

    @GET("configuration?api_key=" + api_key)
    Call<APIConfiguration> getConfiguration();

    @GET("search/movie?api_key=" + api_key)
    Call<Movies> getMovieByName(@Query("query") String name);

    @GET("search/person?api_key=" + api_key)
    Call<Persons> getPersonByName(@Query("query") String name);

    @GET("movie/{id}/credits?api_key=" + api_key)
    Call<Credits> getMovieCredits(@Path("id") int id);

    @GET("movie/{id}?api_key=" + api_key)
    Call<MovieData> getMovieData(@Path("id") int id);

    @GET("person/{id}?api_key=" + api_key)
    Call<PersonData> getPersonData(@Path("id") int id);

    @GET("discover/movie?sort_by=popularity.desc&include_adult=false&include_video=true&page=1&api_key=" + api_key)
    Call<Movies> getMostPopularMovies();

    @GET("discover/movie?sort_by=popularity.desc&include_adult=false&include_video=true&page=1&api_key=" + api_key)
    Call<Movies> getLastYearMostPopularMovies(@Query("year") int year);

    @GET("discover/movie?&sort_by=vote_count.desc&include_adult=false&include_video=true&page=1&api_key=" + api_key)
    Call<Movies> getMostRatedMovies();

    @GET("discover/movie?sort_by=vote_count.desc&include_adult=false&include_video=true&page=1&api_key=" + api_key)
    Call<Movies> getThisYearMostRatedMovies(@Query("year") int year);

    @GET("authentication/guest_session/new?api_key=" + api_key)
    Call<SessionResponse> getSession();

    @FormUrlEncoded
    @POST("movie/{id}/rating?api_key=" + api_key)
    Call<APIPOSTResponse> rateMovie(@Path("id") int id, @Field("value") double rating, @Query("guest_session_id") String sessionId);

}
