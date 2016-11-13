package es.upm.miw.tmdb.manager.models.database;

public class Cast {

    private int id;
    private int movieId;
    private int personId;
    private String personName;
    private String personCharacter;
    private String personBiography;
    private String personBirthday;
    private String personDeathday;
    private String personPlaceOfBirth;
    private Double personPopularity;
    private String personBigImage;
    private String personSmallImage;

    public Cast() {

    }

    public Cast(int id, int movieId, int personId, String personName, String personCharacter, String personBiography, String personBirthday, String personDeathday, String personPlaceOfBirth, Double personPopularity, String personBigImage, String personSmallImage) {
        this.id = id;
        this.movieId = movieId;
        this.personId = personId;
        this.personName = personName;
        this.personCharacter = personCharacter;
        this.personBiography = personBiography;
        this.personBirthday = personBirthday;
        this.personDeathday = personDeathday;
        this.personPlaceOfBirth = personPlaceOfBirth;
        this.personPopularity = personPopularity;
        this.personBigImage = personBigImage;
        this.personSmallImage = personSmallImage;
    }

    public int getPersonId() {
        return personId;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getId() {
        return id;
    }

    public Double getPersonPopularity() {
        return personPopularity;
    }

    public String getPersonBigImage() {
        return personBigImage;
    }

    public String getPersonBiography() {
        return personBiography;
    }

    public String getPersonBirthday() {
        return personBirthday;
    }

    public String getPersonCharacter() {
        return personCharacter;
    }

    public String getPersonDeathday() {
        return personDeathday;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonPlaceOfBirth() {
        return personPlaceOfBirth;
    }

    public String getPersonSmallImage() {
        return personSmallImage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setPersonBigImage(String personBigImage) {
        this.personBigImage = personBigImage;
    }

    public void setPersonBiography(String personBiography) {
        this.personBiography = personBiography;
    }

    public void setPersonBirthday(String personBirthday) {
        this.personBirthday = personBirthday;
    }

    public void setPersonCharacter(String personCharacter) {
        this.personCharacter = personCharacter;
    }

    public void setPersonDeathday(String personDeathday) {
        this.personDeathday = personDeathday;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setPersonPlaceOfBirth(String personPlaceOfBirth) {
        this.personPlaceOfBirth = personPlaceOfBirth;
    }

    public void setPersonPopularity(Double personPopularity) {
        this.personPopularity = personPopularity;
    }

    public void setPersonSmallImage(String personSmallImage) {
        this.personSmallImage = personSmallImage;
    }

}
