package com.georgiecasey.teamworkdemofragments.api;

/**
 * Created by georgiecasey on 31/01/2018.
 */

public class ApiUtil {
    public static final String TEAMWORK_URL = "https://yat.teamwork.com/";
    public static final String TEAMWORK_USERNAME = "yat@triplespin.com";
    public static final String TEAMWORK_PASSWORD = "yatyatyat27";

    public static TeamworkService getTeamworkService() {
        return RetrofitClient.getClient(TEAMWORK_URL).create(TeamworkService.class);
    }
}
