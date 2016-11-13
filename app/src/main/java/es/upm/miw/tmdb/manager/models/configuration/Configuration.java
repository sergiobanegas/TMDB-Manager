package es.upm.miw.tmdb.manager.models.configuration;

import es.upm.miw.tmdb.manager.models.responses.sessions.APIConfiguration;

public class Configuration {

    private static Configuration instance;
    private String movieBackdropImageSmallUrl;
    private String movieBackdropImageBigUrl;
    private String moviePosterImageUrl;
    private String personProfileImageBigUrl;
    private String personProfileImageSmallUrl;
    private String baseUrl;

    private Configuration() {

    }

    public void init(APIConfiguration config, String baseUrl) {
        this.baseUrl = baseUrl;
        String imageBaseUrl = config.getImages().getSecureBaseUrl();
        this.movieBackdropImageSmallUrl = imageBaseUrl + config.getImages().getBackdropSizes().get(1);
        this.movieBackdropImageBigUrl = imageBaseUrl + config.getImages().getBackdropSizes().get(2);
        this.moviePosterImageUrl = imageBaseUrl + config.getImages().getProfileSizes().get(1);
        this.personProfileImageBigUrl = imageBaseUrl + "w300/";
        this.personProfileImageSmallUrl = imageBaseUrl + config.getImages().getProfileSizes().get(1);
    }

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getMovieBackdropImageBigUrl() {
        return movieBackdropImageBigUrl;
    }

    public String getMoviePosterImageUrl() {
        return moviePosterImageUrl;
    }

    public String getPersonProfileImageSmallUrl() {
        return personProfileImageSmallUrl;
    }

    public String getPersonProfileImageBigUrl() {
        return personProfileImageBigUrl;
    }

    public String getMovieBackdropImageSmallUrl() {
        return movieBackdropImageSmallUrl;
    }
}
