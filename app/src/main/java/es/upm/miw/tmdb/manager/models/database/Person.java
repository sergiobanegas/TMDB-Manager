package es.upm.miw.tmdb.manager.models.database;

import java.util.List;

public class Person {

    private int id;
    private String name;
    private String biography;
    private String birthday;
    private String deathday;
    private String placeOfBirth;
    private Double popularity;
    private String smallImage;
    private String bigImage;
    private List<Movie> knownFor;


    public Person() {

    }

    public Person(int id, String name, String biography, String birthday, String deathday, String placeOfBirth, Double popularity, String bigImage, String smallImage, List<Movie> knownFor) {
        this.id = id;
        this.name = name;
        this.biography = biography;
        this.birthday = birthday;
        this.deathday = deathday;
        this.placeOfBirth = placeOfBirth;
        this.popularity = popularity;
        this.smallImage = smallImage;
        this.bigImage = bigImage;
        this.knownFor = knownFor;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBiography() {
        return biography;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getDeathday() {
        return deathday;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public Double getPopularity() {
        return popularity;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public String getBigImage() {
        return bigImage;
    }

    public List<Movie> getKnownFor() {
        return knownFor;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public void setBigImage(String bigImage) {
        this.bigImage = bigImage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public void setKnownFor(List<Movie> knownFor) {
        this.knownFor = knownFor;
    }
}
