package com.example.alexey.newsviewer.data;

import com.example.alexey.newsviewer.model.NewsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alexey on 11/03/17.
 */

public interface BBCApi {

    @GET("v1/articles")
    Call<NewsList> getData(@Query("source") String resourceName, @Query("sortBy") String sort, @Query("apiKey") String apiKey);

    interface Constants {

        String RESOURSE_NAME = "bbc-news";
        String SORT_TYPE = "top";
        String API_KEY = "6c07d6dee46b413293dc57cf3de48590";
    }

}
