package com.example.alexey.newsviewer.news_details;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;

import com.example.alexey.newsviewer.data.NewsRepository;
import com.example.alexey.newsviewer.model.NewsItem;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by alexey on 11/03/17.
 */

public class DetailViewModel extends BaseObservable {

    private static ImageReadyCallback mImageReadyCallback;

    public final ObservableField<String> author = new ObservableField<>();

    public final ObservableField<String> title = new ObservableField<>();

    public final ObservableField<String> description = new ObservableField<>();

    public final ObservableField<String> urlToImage = new ObservableField<>();

    public final ObservableField<String> publishedAt = new ObservableField<>();

    private NewsRepository mRepository;

    private String mUrl;

    private NewsItem mCurrentNews;

    public DetailViewModel(String url, ImageReadyCallback callback) {
        mRepository = NewsRepository.getInstance();
        mUrl = url;

        mImageReadyCallback = callback;

        start();
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Picasso.with(imageView.getContext())
                .load(url)
                .fit()
                .noFade()
                .centerCrop()
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        mImageReadyCallback.onImageReady();
                    }

                    @Override
                    public void onError() {
                        mImageReadyCallback.onImageReady();
                    }
                });
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
