package com.example.alexey.newsviewer.news_details;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.util.Log;
import android.widget.ImageView;

import com.example.alexey.newsviewer.data.NewsRepository;
import com.example.alexey.newsviewer.model.NewsItem;
import com.squareup.picasso.Picasso;

/**
 * Created by alexey on 11/03/17.
 */

public class DetailViewModel extends BaseObservable {

    public final ObservableField<String> author = new ObservableField<>();

    public final ObservableField<String> title = new ObservableField<>();

    public final ObservableField<String> description = new ObservableField<>();

    public final ObservableField<String> urlToImage = new ObservableField<>();

    public final ObservableField<String> publishedAt = new ObservableField<>();

    private NewsRepository mRepository;

    private String mUrl;

    private NewsItem mCurrentNews;

    public DetailViewModel(String url) {
        mRepository = NewsRepository.getInstance();
        mUrl = url;

        start();
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String v) {
        Picasso.Builder builder = new Picasso.Builder(imageView.getContext());
        builder.listener((picasso, uri, exception) -> Log.d("LoadImage", exception.getMessage()));
        builder.build().load(v).into(imageView);
    }

    private void start() {

        mCurrentNews = mRepository.getNewsWithUrl(mUrl);

        configureValues();
    }

    private void configureValues() {

        author.set(mCurrentNews.getAuthor());
        title.set(mCurrentNews.getTitle());
        description.set(mCurrentNews.getDescription());
        urlToImage.set(mCurrentNews.getUrlToImage());
        publishedAt.set(mCurrentNews.getPublishedAt());
    }

}
