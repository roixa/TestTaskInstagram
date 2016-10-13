package com.roix.testtaskinstagram.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by u5 on 10/10/16.
 */
public class TokenResponse {

    @SerializedName("user")
    private User user;

    @SerializedName("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
