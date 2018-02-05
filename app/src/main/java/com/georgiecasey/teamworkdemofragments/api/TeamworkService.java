package com.georgiecasey.teamworkdemofragments.api;

import com.georgiecasey.teamworkdemofragments.model.request.tasklists.ReorderTasklists;
import com.georgiecasey.teamworkdemofragments.model.response.project.Projects;
import com.georgiecasey.teamworkdemofragments.model.response.tasklists.Tasklists;
import com.georgiecasey.teamworkdemofragments.model.response.user.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by georgiecasey on 31/01/2018.
 */

public interface TeamworkService {
    @GET("authenticate.json")
    Call<User> getAccount();

    @GET("projects.json")
    Call<Projects> getProjects();

    @GET("projects/{projectId}/tasklists.json")
    Call<Tasklists> getTasklists(@Path("projectId") long projectId);

    @PUT("/projects/{projectId}/tasklists/reorder.json")
    Call<Void> reorderTasklists(@Path("projectId") long projectId, @Body ReorderTasklists tasklistIds);
}
