package com.example.alexey.newsviewer.adapters;

import android.view.View;

/**
 * Created by alexey on 10/03/17.
 */

public interface OnItemClickListener<T> {

    void onItemClick(int position, T item, View view);
}
