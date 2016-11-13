package es.upm.miw.tmdb.manager.models.responses.sessions;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class SessionResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("guest_session_id")
    @Expose
    private String guestSessionId;
    @SerializedName("expires_at")
    @Expose
    private String expiresAt;

    /**
     * No args constructor for use in serialization
     */
    public SessionResponse() {
    }

    /**
     * @param guestSessionId
     * @param expiresAt
     * @param success
     */
    public SessionResponse(Boolean success, String guestSessionId, String expiresAt) {
        this.success = success;
        this.guestSessionId = guestSessionId;
        this.expiresAt = expiresAt;
    }

    /**
     * @return The success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * @param success The success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * @return The guestSessionId
     */
    public String getGuestSessionId() {
        return guestSessionId;
    }

    /**
     * @param guestSessionId The guest_session_id
     */
    public void setGuestSessionId(String guestSessionId) {
        this.guestSessionId = guestSessionId;
    }

    /**
     * @return The expiresAt
     */
    public String getExpiresAt() {
        return expiresAt;
    }

    /**
     * @param expiresAt The expires_at
     */
    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

}