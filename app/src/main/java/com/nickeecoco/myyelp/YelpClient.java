package com.nickeecoco.myyelp;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YelpClient {

    OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @NonNull
                @Override
                public Response intercept(@NonNull Chain chain) throws IOException {
                    return chain.proceed(chain.request()
                            .newBuilder()
                            .addHeader("Authorization", "Bearer -p4xJgNGmwvasGNy3TWw42Nev6ySnAYJF0iG0hazPltDE8mo8JhyjBO7uB4UrvglzN0R89g1r6og5EXeMOztd3okOyOlbXDuiaYSl-sm9WFrXgAMIcYhKg9uR_6kYnYx")
                            .build());
                }
            }).build();

    public YelpAPI create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.yelp.com/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        return retrofit.create(YelpAPI.class);
    }
}
