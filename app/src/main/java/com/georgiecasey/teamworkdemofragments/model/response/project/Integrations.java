package com.georgiecasey.teamworkdemofragments.model.response.project;

/**
 * Created by georgiecasey on 31/01/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Integrations {

    @SerializedName("microsoftConnectors")
    @Expose
    private MicrosoftConnectors microsoftConnectors;
    @SerializedName("onedrivebusiness")
    @Expose
    private Onedrivebusiness onedrivebusiness;

    public MicrosoftConnectors getMicrosoftConnectors() {
        return microsoftConnectors;
    }

    public void setMicrosoftConnectors(MicrosoftConnectors microsoftConnectors) {
        this.microsoftConnectors = microsoftConnectors;
    }

    public Onedrivebusiness getOnedrivebusiness() {
        return onedrivebusiness;
    }

    public void setOnedrivebusiness(Onedrivebusiness onedrivebusiness) {
        this.onedrivebusiness = onedrivebusiness;
    }

}