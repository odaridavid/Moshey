package com.android.team.moshey.models.entities.mpesa;

import com.google.gson.annotations.SerializedName;

/**
 * Created By blackcoder
 * On 20/04/19
 **/
public final class AuthResponse {
    @SerializedName("access_token")
    protected String accessToken;
    @SerializedName("expires_in")
    protected String expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "accessToken='" + accessToken + '\'' +
                ", expiresIn='" + expiresIn + '\'' +
                '}';
    }
}
