package es.upm.miw.tmdb.manager.models.database;

public class KnownFor {

    private int id;
    private int personId;
    private int movieId;
    private String movieTitle;
    private String movieOverview;
    private String movieDirecting;
    private String movieWriting;
    private String movieReleaseDate;
    private Double moviePopularity;
    private String moviePosterImage;
    private String movieBackdropImage;

    public KnownFor() {

    }

    public KnownFor(int id, int personId, int movieId, String movieTitle, String movieOverview, String movieDirecting, String movieWriting, String movieReleaseDate, Double moviePopularity, String moviePosterImage, String movieBackdropImage) {
        this.id = id;
        this.personId = personId;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieOverview = movieOverview;
        this.movieDirecting = movieDirecting;
        this.movieWriting = movieWriting;
        this.movieReleaseDate = movieReleaseDate;
        this.moviePopularity = moviePopularity;
        this.moviePosterImage = moviePosterImage;
        this.movieBackdropImage = movieBackdropImage;
    }

    public int getId() {
        return id;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getPersonId() {
        return personId;
    }

    public Double getMoviePopularity() {
        return moviePopularity;
    }

    public String getMovieBackdropImage() {
        return movieBackdropImage;
    }

    public String getMovieDirecting() {
        return movieDirecting;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public String getMoviePosterImage() {
        return moviePosterImage;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMovieWriting() {
        return movieWriting;
    }

    public void setMovieBackdropImage(String movieBackdropImage) {
        this.movieBackdropImage = movieBackdropImage;
    }

    public void setMoviePosterImage(String moviePosterImage) {
        this.moviePosterImage = moviePosterImage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMovieDirecting(String movieDirecting) {
        this.movieDirecting = movieDirecting;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public void setMoviePopularity(Double moviePopularity) {
        this.moviePopularity = moviePopularity;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setMovieWriting(String movieWriting) {
        this.movieWriting = movieWriting;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
