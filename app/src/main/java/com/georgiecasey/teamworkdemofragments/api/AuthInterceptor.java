package com.georgiecasey.teamworkdemofragments.api;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by georgiecasey on 31/01/2018.
 */

public class AuthInterceptor implements Interceptor {
    private String credentials;

    public AuthInterceptor() {
        this.credentials = Credentials.basic(ApiUtil.TEAMWORK_USERNAME, ApiUtil.TEAMWORK_PASSWORD);
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder builder = request.newBuilder().
                header("Authorization", credentials);

        Request request2 = builder.build();
        return chain.proceed(request2);
    }
}
