package es.upm.miw.tmdb.manager.models.responses.movies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Credits {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cast")
    @Expose
    private List<Cast> cast = new ArrayList<Cast>();
    @SerializedName("crew")
    @Expose
    private List<MovieCrew> crew = new ArrayList<MovieCrew>();

    /**
     * No args constructor for use in serialization
     */
    public Credits() {
    }

    /**
     * @param id
     * @param cast
     * @param crew
     */
    public Credits(Integer id, List<Cast> cast, List<MovieCrew> crew) {
        this.id = id;
        this.cast = cast;
        this.crew = crew;
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
     * @return The cast
     */
    public List<Cast> getCast() {
        return cast;
    }

    /**
     * @param cast The cast
     */
    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    /**
     * @return The crew
     */
    public List<MovieCrew> getCrew() {
        return crew;
    }

    /**
     * @param crew The crew
     */
    public void setCrew(List<MovieCrew> crew) {
        this.crew = crew;
    }

}


