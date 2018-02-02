
package com.georgiecasey.teamworkdemofragments.model.response.tasklists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tasklist {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("pinned")
    @Expose
    private Boolean pinned;
    @SerializedName("milestone-id")
    @Expose
    private String milestoneId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("uncompleted-count")
    @Expose
    private Integer uncompletedCount;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("complete")
    @Expose
    private Boolean complete;
    @SerializedName("private")
    @Expose
    private Boolean _private;
    @SerializedName("isTemplate")
    @Expose
    private Boolean isTemplate;
    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("projectId")
    @Expose
    private String projectId;
    @SerializedName("projectName")
    @Expose
    private String projectName;
    @SerializedName("DLM")
    @Expose
    private Object dLM;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPinned() {
        return pinned;
    }

    public void setPinned(Boolean pinned) {
        this.pinned = pinned;
    }

    public String getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(String milestoneId) {
        this.milestoneId = milestoneId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUncompletedCount() {
        return uncompletedCount;
    }

    public void setUncompletedCount(Integer uncompletedCount) {
        this.uncompletedCount = uncompletedCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public Boolean getPrivate() {
        return _private;
    }

    public void setPrivate(Boolean _private) {
        this._private = _private;
    }

    public Boolean getIsTemplate() {
        return isTemplate;
    }

    public void setIsTemplate(Boolean isTemplate) {
        this.isTemplate = isTemplate;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Object getDLM() {
        return dLM;
    }

    public void setDLM(Object dLM) {
        this.dLM = dLM;
    }

}
