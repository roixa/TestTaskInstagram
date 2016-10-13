
package com.roix.testtaskinstagram.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("low_resolution")
    @Expose
    private Resolution lowResolution;
    @SerializedName("thumbnail")
    @Expose
    private Resolution thumbnail;
    @SerializedName("standard_resolution")
    @Expose
    private Resolution standardResolution;

    public Resolution getLowResolution() {
        return lowResolution;
    }
    public void setLowResolution(Resolution lowResolution) {
        this.lowResolution = lowResolution;
    }

    public Resolution getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(Resolution thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Resolution getStandardResolution() {
        return standardResolution;
    }
    public void setStandardResolution(Resolution standardResolution) {
        this.standardResolution = standardResolution;
    }

}
