package com.example.alexey.newsviewer.adapters;

import android.view.View;

import com.example.alexey.newsviewer.model.NewsItem;
import com.example.alexey.newsviewer.utils.SquareImageView;

/**
 * Created by alexey on 10/03/17.
 */

public interface OnItemClickListener<T> {

    void onItemClick(NewsItem item, View v, SquareImageView imageView);

}
