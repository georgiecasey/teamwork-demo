package com.georgiecasey.teamworkdemofragments.model.response.project;

/**
 * Created by georgiecasey on 31/01/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Defaults {

    @SerializedName("privacy")
    @Expose
    private String privacy;

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

}
