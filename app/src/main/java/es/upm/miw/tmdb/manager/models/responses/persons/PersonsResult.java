package es.upm.miw.tmdb.manager.models.responses.persons;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class PersonsResult implements Parcelable, Comparable {

    @SerializedName("profile_path")
    @Expose
    private String profilePath;
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("known_for")
    @Expose
    private List<KnownFor> knownFor = new ArrayList<KnownFor>();
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("popularity")
    @Expose
    private Double popularity;

    /**
     * No args constructor for use in serialization
     */
    public PersonsResult() {
    }

    /**
     * @param id
     * @param profilePath
     * @param knownFor
     * @param name
     * @param adult
     * @param popularity
     */
    public PersonsResult(String profilePath, Boolean adult, Integer id, List<KnownFor> knownFor, String name, Double popularity) {
        this.profilePath = profilePath;
        this.adult = adult;
        this.id = id;
        this.knownFor = knownFor;
        this.name = name;
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
     * @return The knownFor
     */
    public List<KnownFor> getKnownFor() {
        return knownFor;
    }

    /**
     * @param knownFor The known_for
     */
    public void setKnownFor(List<KnownFor> knownFor) {
        this.knownFor = knownFor;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.profilePath);
        dest.writeValue(this.adult);
        dest.writeValue(this.id);
        dest.writeList(this.knownFor);
        dest.writeString(this.name);
        dest.writeValue(this.popularity);
    }

    protected PersonsResult(Parcel in) {
        this.profilePath = in.readString();
        this.adult = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.knownFor = new ArrayList<KnownFor>();
        in.readList(this.knownFor, KnownFor.class.getClassLoader());
        this.name = in.readString();
        this.popularity = (Double) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<PersonsResult> CREATOR = new Parcelable.Creator<PersonsResult>() {
        @Override
        public PersonsResult createFromParcel(Parcel source) {
            return new PersonsResult(source);
        }

        @Override
        public PersonsResult[] newArray(int size) {
            return new PersonsResult[size];
        }
    };

    @Override
    public int compareTo(Object o) {
        if (this.popularity == ((PersonsResult) o).getPopularity())
            return 0;
        else if ((this.popularity) < ((PersonsResult) o).getPopularity())
            return 1;
        else
            return -1;
    }
}
