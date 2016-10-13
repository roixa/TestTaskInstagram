
package com.roix.testtaskinstagram.pojo;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentsResponse {

    @SerializedName("data")
    @Expose
    private List<Comment> data = new ArrayList<Comment>();

    public List<Comment> getData() {
        return data;
    }
    public void setData(List<Comment> data) {
        this.data = data;
    }

}
