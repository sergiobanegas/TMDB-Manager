package es.upm.miw.tmdb.manager.models.database;

public class Movie {

    private int id;
    private String title;
    private String overview;
    private String directing;
    private String writing;
    private String releaseDate;
    private Double popularity;
    private String posterImage;
    private String backDropImage;

    public Movie() {

    }

    public Movie(int id, String title, String overview, String directing, String writing, String releaseDate, Double popularity, String posterImage, String backDropImage) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.directing = directing;
        this.writing = writing;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.posterImage = posterImage;
        this.backDropImage = backDropImage;
    }

    public String getBackDropImage() {
        return backDropImage;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Double getPopularity() {
        return popularity;
    }

    public int getId() {
        return id;
    }

    public String getDirecting() {
        return directing;
    }

    public String getOverview() {
        return overview;
    }

    public String getTitle() {
        return title;
    }

    public String getWriting() {
        return writing;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public void setBackDropImage(String backDropImage) {
        this.backDropImage = backDropImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDirecting(String directing) {
        this.directing = directing;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWriting(String writing) {
        this.writing = writing;
    }
}
