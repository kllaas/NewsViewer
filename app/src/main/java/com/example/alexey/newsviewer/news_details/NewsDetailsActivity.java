package com.example.alexey.newsviewer.news_details;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.alexey.newsviewer.Constants;
import com.example.alexey.newsviewer.R;
import com.example.alexey.newsviewer.databinding.ActivityNewsDetailBinding;

/**
 * Created by alexey on 11/03/17.
 */

public class NewsDetailsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityNewsDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_news_detail);

        String newsUrl = getIntent().getExtras().getString(Constants.NEWS_URL);

        DetailViewModel mViewModel = new DetailViewModel(newsUrl);
        binding.setViewModel(mViewModel);
    }
}
