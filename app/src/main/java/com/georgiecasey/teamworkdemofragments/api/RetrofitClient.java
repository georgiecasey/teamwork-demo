package com.georgiecasey.teamworkdemofragments.api;

import java.net.Proxy;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by georgiecasey on 31/01/2018.
 */

public class RetrofitClient {
    private static Retrofit retrofit = null;
    private static OkHttpClient client = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .client(getOkHttpClient())
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static OkHttpClient getOkHttpClient() {
        if (client==null) {
            try {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.addInterceptor(new AuthInterceptor());
                client = builder.build();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return client;
    }
}
