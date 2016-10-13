
package com.roix.testtaskinstagram.pojo;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserMediaResponse {

    @SerializedName("data")
    @Expose
    private List<Photo> data = new ArrayList<Photo>();


    public List<Photo> getData() {
        return data;
    }
    public void setData(List<Photo> data) {
        this.data = data;
    }

}
