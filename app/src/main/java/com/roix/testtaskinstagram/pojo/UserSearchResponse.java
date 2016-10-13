
package com.roix.testtaskinstagram.pojo;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserSearchResponse {

    @SerializedName("data")
    @Expose
    private List<User> data = new ArrayList<User>();

    public List<User> getData() {
        return data;
    }
    public void setData(List<User> data) {
        this.data = data;
    }

}
