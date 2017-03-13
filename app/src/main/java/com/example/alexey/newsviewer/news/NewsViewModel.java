package com.example.alexey.newsviewer.news;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;

import com.android.databinding.library.baseAdapters.BR;
import com.example.alexey.newsviewer.data.LoadDataCallback;
import com.example.alexey.newsviewer.data.NewsItem;
import com.example.alexey.newsviewer.data.NewsRepository;
import com.example.alexey.newsviewer.utils.CustomObservableList;

import java.util.List;

/**
 * Created by alexey on 11/03/17.
 */

public class NewsViewModel extends BaseObservable {

    public final ObservableList<NewsItem> items = new CustomObservableList<>();

    public final ObservableBoolean dataLoading = new ObservableBoolean(false);

    public final ObservableBoolean mIsDataLoadingError = new ObservableBoolean(false);

    private NewsRepository mRepository;

    public NewsViewModel() {
        mRepository = NewsRepository.getInstance();

        start();
    }

    private void start() {

        dataLoading.set(true);

        mRepository.getNews(new LoadDataCallback() {
            @Override
            public void onNewsLoaded(List<NewsItem> news) {
                items.clear();
                items.addAll(news);

                dataLoading.set(false);

                notifyPropertyChanged(BR.empty);
            }

            @Override
            public void onDataNotAvailable() {
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
