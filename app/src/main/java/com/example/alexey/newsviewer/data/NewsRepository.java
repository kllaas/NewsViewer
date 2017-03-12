package com.example.alexey.newsviewer.data;

import com.example.alexey.newsviewer.App;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.alexey.newsviewer.data.BBCApi.Constants.API_KEY;
import static com.example.alexey.newsviewer.data.BBCApi.Constants.RESOURCE_NAME;
import static com.example.alexey.newsviewer.data.BBCApi.Constants.SORT_TYPE;

/**
 * Created by alexey on 11/03/17.
 */

public class NewsRepository {

    private static NewsRepository INSTANCE;

    private static Map<String, NewsItem> mCachedTasks;


    private NewsRepository() {
    }

    public static NewsRepository getInstance() {
        if (INSTANCE == null) {
            return new NewsRepository();
        }
        return INSTANCE;
    }

    public void getNews(LoadDataCallback callback) {
        if (mCachedTasks != null) {
            callback.onNewsLoaded(new ArrayList<>(mCachedTasks.values()));
            return;
        }

        App.getApi().getData(RESOURCE_NAME, SORT_TYPE, API_KEY).enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                if (response.isSuccessful()) {
                    List<NewsItem> newsList = response.body().getNews();

                    refreshCache(newsList);
                    callback.onNewsLoaded(newsList);

                    return;
                }
                callback.onDataNotAvailable();
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshCache(List<NewsItem> news) {
        if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.clear();
        for (NewsItem newsItem : news) {
            mCachedTasks.put(newsItem.getUrl(), newsItem);
        }
    }

    public void removeNews(String url) {
        mCachedTasks.remove(url);
    }

    public NewsItem getNewsWithUrl(String url) {
        if (mCachedTasks == null || mCachedTasks.isEmpty()) {
            return null;
        } else {
            return mCachedTasks.get(url);
        }
    }
}
