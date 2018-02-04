package com.georgiecasey.teamworkdemofragments.model.request.tasklists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by georgiecasey on 04/02/2018.
 */

public class Tasklists {
    @SerializedName("tasklists")
    @Expose
    public List<TasklistId> tasklists = null;

    public List<TasklistId> getTasklists() {
        return tasklists;
    }

    public void setTasklists(List<TasklistId> tasklists) {
        this.tasklists = tasklists;
    }

}
