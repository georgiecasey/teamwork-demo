package com.georgiecasey.teamworkdemofragments.api;


import com.georgiecasey.teamworkdemofragments.model.response.project.Projects;
import com.georgiecasey.teamworkdemofragments.model.response.tasklists.Tasklists;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by georgiecasey on 31/01/2018.
 */

public interface TeamworkService {
    /*@GET("authenticate.json")
    Call<User> getAccount();*/

    @GET("projects.json")
    Call<Projects> getProjects();

    @GET("projects/{projectId}/tasklists.json")
    Call<Tasklists> getTasklists(@Path("projectId") long projectId);

   /* @GET("projects/{projectId}.json")
    Call<SingleProject> getSingleProject(@Path("projectId") long projectId);

    @POST("pendingfiles.json")
    Call<FileUpload> uploadFile(@Header("Content-Disposition") String content_disposition, @Body RequestBody file);

    @Multipart
    @POST("pendingfiles.json")
    Call<FileUpload> uploadFile2(@Part MultipartBody.Part file);

    @POST("/projects/{projectId}/files.json")
    Call<AddFileToProjectResponse> addFileToProject(@Path("projectId") long projectId, @Body AddFileToProjectRequest addFileToProjectRequest);*/
}