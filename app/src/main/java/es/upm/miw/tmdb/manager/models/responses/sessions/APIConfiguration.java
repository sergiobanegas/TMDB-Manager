package es.upm.miw.tmdb.manager.models.responses.sessions;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class APIConfiguration {

    @SerializedName("images")
    @Expose
    private Images images;
    @SerializedName("change_keys")
    @Expose
    private List<String> changeKeys = new ArrayList<String>();

    /**
     * No args constructor for use in serialization
     */
    public APIConfiguration() {
    }

    /**
     * @param images
     * @param changeKeys
     */
    public APIConfiguration(Images images, List<String> changeKeys) {
        this.images = images;
        this.changeKeys = changeKeys;
    }

    /**
     * @return The images
     */
    public Images getImages() {
        return images;
    }

    /**
     * @param images The images
     */
    public void setImages(Images images) {
        this.images = images;
    }

    /**
     * @return The changeKeys
     */
    public List<String> getChangeKeys() {
        return changeKeys;
    }

    /**
     * @param changeKeys The change_keys
     */
    public void setChangeKeys(List<String> changeKeys) {
        this.changeKeys = changeKeys;
    }

}