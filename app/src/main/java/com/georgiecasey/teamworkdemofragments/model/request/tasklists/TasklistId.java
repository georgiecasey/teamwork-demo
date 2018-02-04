package com.georgiecasey.teamworkdemofragments.model.request.tasklists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by georgiecasey on 04/02/2018.
 */

public class TasklistId {
    @SerializedName("id")
    @Expose
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TasklistId(long id) {
        this.id = id;
    }
}
