package com.georgiecasey.teamworkdemofragments.api;

import java.net.InetSocketAddress;
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
                    //.client(getOkHttpClient())
                    .client(getUnsafeOkHttpClient())
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

    public static OkHttpClient getUnsafeOkHttpClient() {
        if (client==null) {
            try {
                // Create a trust manager that does not validate certificate chains
                final TrustManager[] trustAllCerts = new TrustManager[]{
                        new X509TrustManager() {
                            @Override
                            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                return new java.security.cert.X509Certificate[]{};
                            }
                        }
                };

                // Install the all-trusting trust manager
                final SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                // Create an ssl socket factory with our all-trusting manager
                final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.1.1", 8888)));
                builder.addInterceptor(new AuthInterceptor());
            /*builder.authenticator(new Authenticator() {
                @Override
                public Request authenticate(Route route, Response response) throws IOException {
                    String credential = Credentials.basic("twp_IMnCahEk1LklnTx8-8HbwovVi4uH", "");
                    return response.request().newBuilder().header("Authorization", credential).build();
                }
            });*/
                builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
                builder.hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });

                client = builder.build();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return client;
    }
}
