package com.example.alexey.newsviewer;

import android.app.Application;

import com.example.alexey.newsviewer.data.BBCApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexey on 11/03/17.
 */

public class App extends Application {

    private static BBCApi bbcApi;

    private Retrofit retrofit;

    public static BBCApi getApi() {
        return bbcApi;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        bbcApi = retrofit.create(BBCApi.class);
    }
}
