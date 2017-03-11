package com.example.alexey.newsviewer.news;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;

import com.example.alexey.newsviewer.App;
import com.example.alexey.newsviewer.model.NewsItem;
import com.example.alexey.newsviewer.model.NewsList;
import com.example.alexey.newsviewer.utils.CustomObservableList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.alexey.newsviewer.data.BBCApi.Constants.API_KEY;
import static com.example.alexey.newsviewer.data.BBCApi.Constants.RESOURSE_NAME;
import static com.example.alexey.newsviewer.data.BBCApi.Constants.SORT_TYPE;

/**
 * Created by alexey on 11/03/17.
 */

public class NewsViewModel extends BaseObservable {

    public final CustomObservableList<NewsItem> items = new CustomObservableList<>();

    public final ObservableBoolean dataLoading = new ObservableBoolean(false);

    public final ObservableBoolean mIsDataLoadingError = new ObservableBoolean(false);

    NewsViewModel() {
        start();
    }

    private void start() {

        dataLoading.set(true);

        App.getApi().getData(RESOURSE_NAME, SORT_TYPE, API_KEY).enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                if (response.isSuccessful()) {

                    items.clear();
                    items.addAll(response.body().getNews());

                    dataLoading.set(false);
                }
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                dataLoading.set(false);
                mIsDataLoadingError.set(true);
            }
        });
    }

    @Bindable
    public boolean isEmpty() {
        return items.isEmpty();
    }

}
