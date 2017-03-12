package com.example.alexey.newsviewer.adapters;

import android.view.View;

import com.example.alexey.newsviewer.data.NewsItem;
import com.example.alexey.newsviewer.databinding.NewsItemBinding;

/**
 * Created by alexey on 10/03/17.
 */

public interface OnItemClickListener<T> {

    void onItemClick(NewsItem item, View v, NewsItemBinding imageView);

}
