package com.example.alexey.newsviewer.data;

import com.example.alexey.newsviewer.model.NewsItem;

import java.util.List;

/**
 * Created by alexey on 11/03/17.
 */

public interface LoadDataCallback {

    void onNewsLoaded(List<NewsItem> news);

    void onDataNotAvailable();

}
