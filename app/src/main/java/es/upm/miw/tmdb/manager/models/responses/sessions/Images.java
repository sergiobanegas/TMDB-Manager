package es.upm.miw.tmdb.manager.models.responses.sessions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Images {

    @SerializedName("base_url")
    @Expose
    private String baseUrl;
    @SerializedName("secure_base_url")
    @Expose
    private String secureBaseUrl;
    @SerializedName("backdrop_sizes")
    @Expose
    private List<String> backdropSizes = new ArrayList<String>();
    @SerializedName("logo_sizes")
    @Expose
    private List<String> logoSizes = new ArrayList<String>();
    @SerializedName("poster_sizes")
    @Expose
    private List<String> posterSizes = new ArrayList<String>();
    @SerializedName("profile_sizes")
    @Expose
    private List<String> profileSizes = new ArrayList<String>();
    @SerializedName("still_sizes")
    @Expose
    private List<String> stillSizes = new ArrayList<String>();

    /**
     * No args constructor for use in serialization
     */
    public Images() {
    }

    /**
     * @param baseUrl
     * @param profileSizes
     * @param posterSizes
     * @param logoSizes
     * @param stillSizes
     * @param backdropSizes
     * @param secureBaseUrl
     */
    public Images(String baseUrl, String secureBaseUrl, List<String> backdropSizes, List<String> logoSizes, List<String> posterSizes, List<String> profileSizes, List<String> stillSizes) {
        this.baseUrl = baseUrl;
        this.secureBaseUrl = secureBaseUrl;
        this.backdropSizes = backdropSizes;
        this.logoSizes = logoSizes;
        this.posterSizes = posterSizes;
        this.profileSizes = profileSizes;
        this.stillSizes = stillSizes;
    }

    /**
     * @return The baseUrl
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * @param baseUrl The base_url
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * @return The secureBaseUrl
     */
    public String getSecureBaseUrl() {
        return secureBaseUrl;
    }

    /**
     * @param secureBaseUrl The secure_base_url
     */
    public void setSecureBaseUrl(String secureBaseUrl) {
        this.secureBaseUrl = secureBaseUrl;
    }

    /**
     * @return The backdropSizes
     */
    public List<String> getBackdropSizes() {
        return backdropSizes;
    }

    /**
     * @param backdropSizes The backdrop_sizes
     */
    public void setBackdropSizes(List<String> backdropSizes) {
        this.backdropSizes = backdropSizes;
    }

    /**
     * @return The logoSizes
     */
    public List<String> getLogoSizes() {
        return logoSizes;
    }

    /**
     * @param logoSizes The logo_sizes
     */
    public void setLogoSizes(List<String> logoSizes) {
        this.logoSizes = logoSizes;
    }

    /**
     * @return The posterSizes
     */
    public List<String> getPosterSizes() {
        return posterSizes;
    }

    /**
     * @param posterSizes The poster_sizes
     */
    public void setPosterSizes(List<String> posterSizes) {
        this.posterSizes = posterSizes;
    }

    /**
     * @return The profileSizes
     */
    public List<String> getProfileSizes() {
        return profileSizes;
    }

    /**
     * @param profileSizes The profile_sizes
     */
    public void setProfileSizes(List<String> profileSizes) {
        this.profileSizes = profileSizes;
    }

    /**
     * @return The stillSizes
     */
    public List<String> getStillSizes() {
        return stillSizes;
    }

    /**
     * @param stillSizes The still_sizes
     */
    public void setStillSizes(List<String> stillSizes) {
        this.stillSizes = stillSizes;
    }

}