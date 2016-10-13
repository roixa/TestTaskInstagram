package com.roix.testtaskinstagram.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by u5 on 10/10/16.
 */
public class User {

    @SerializedName("username")
    private String username;


    @SerializedName("profile_picture")
    private String picture;

    @SerializedName("id")
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }






}
