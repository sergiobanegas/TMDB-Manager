package es.upm.miw.tmdb.manager.models.responses.persons;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class PersonData {

    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("also_known_as")
    @Expose
    private List<Object> alsoKnownAs = new ArrayList<Object>();
    @SerializedName("biography")
    @Expose
    private String biography;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("deathday")
    @Expose
    private String deathday;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("homepage")
    @Expose
    private String homepage;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("imdb_id")
    @Expose
    private String imdbId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("place_of_birth")
    @Expose
    private String placeOfBirth;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("profile_path")
    @Expose
    private String profilePath;

    /**
     * No args constructor for use in serialization
     */
    public PersonData() {
    }

    /**
     * @param profilePath
     * @param birthday
     * @param alsoKnownAs
     * @param placeOfBirth
     * @param adult
     * @param biography
     * @param imdbId
     * @param homepage
     * @param id
     * @param deathday
     * @param name
     * @param gender
     * @param popularity
     */
    public PersonData(Boolean adult, List<Object> alsoKnownAs, String biography, String birthday, String deathday, Integer gender, String homepage, Integer id, String imdbId, String name, String placeOfBirth, Double popularity, String profilePath) {
        this.adult = adult;
        this.alsoKnownAs = alsoKnownAs;
        this.biography = biography;
        this.birthday = birthday;
        this.deathday = deathday;
        this.gender = gender;
        this.homepage = homepage;
        this.id = id;
        this.imdbId = imdbId;
        this.name = name;
        this.placeOfBirth = placeOfBirth;
        this.popularity = popularity;
        this.profilePath = profilePath;
    }

    /**
     * @return The adult
     */
    public Boolean getAdult() {
        return adult;
    }

    /**
     * @param adult The adult
     */
    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    /**
     * @return The alsoKnownAs
     */
    public List<Object> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    /**
     * @param alsoKnownAs The also_known_as
     */
    public void setAlsoKnownAs(List<Object> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    /**
     * @return The biography
     */
    public String getBiography() {
        return biography;
    }

    /**
     * @param biography The biography
     */
    public void setBiography(String biography) {
        this.biography = biography;
    }

    /**
     * @return The birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday The birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return The deathday
     */
    public String getDeathday() {
        return deathday;
    }

    /**
     * @param deathday The deathday
     */
    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }

    /**
     * @return The gender
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * @param gender The gender
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * @return The homepage
     */
    public String getHomepage() {
        return homepage;
    }

    /**
     * @param homepage The homepage
     */
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The imdbId
     */
    public String getImdbId() {
        return imdbId;
    }

    /**
     * @param imdbId The imdb_id
     */
    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The placeOfBirth
     */
    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    /**
     * @param placeOfBirth The place_of_birth
     */
    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    /**
     * @return The popularity
     */
    public Double getPopularity() {
        return popularity;
    }

    /**
     * @param popularity The popularity
     */
    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    /**
     * @return The profilePath
     */
    public String getProfilePath() {
        return profilePath;
    }

    /**
     * @param profilePath The profile_path
     */
    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

}