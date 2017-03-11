package com.example.alexey.newsviewer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alexey on 11/03/17.
 */

public class NewsList {

    @SerializedName("articles")
    @Expose
    private List<NewsItem> news;

    public List<NewsItem> getNews() {
        return news;
    }
}
