
package com.georgiecasey.teamworkdemofragments.model.response.tasklists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Tasklists {

    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("tasklists")
    @Expose
    public List<Tasklist> tasklists = null;

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public List<Tasklist> getTasklists() {
        return tasklists;
    }

    public void setTasklists(List<Tasklist> tasklists) {
        this.tasklists = tasklists;
    }

}
