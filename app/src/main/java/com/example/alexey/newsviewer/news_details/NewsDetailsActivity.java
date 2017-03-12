package com.example.alexey.newsviewer.news_details;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.transition.Explode;
import android.view.Window;

import com.example.alexey.newsviewer.BaseActivity;
import com.example.alexey.newsviewer.Constants;
import com.example.alexey.newsviewer.R;
import com.example.alexey.newsviewer.databinding.ActivityNewsDetailBinding;

/**
 * Created by alexey on 11/03/17.
 */

public class NewsDetailsActivity extends BaseActivity implements ImageReadyCallback {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            initAppearAnimation();
        }

        ActivityNewsDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_news_detail);

        String newsUrl = getIntent().getExtras().getString(Constants.NEWS_URL);

        DetailViewModel mViewModel = new DetailViewModel(newsUrl, this);
        binding.setViewModel(mViewModel);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initAppearAnimation() {
        ActivityCompat.postponeEnterTransition(this);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        getWindow().setExitTransition(new Explode());
    }

    @Override
    public void onImageReady() {
        ActivityCompat.startPostponedEnterTransition(this);
    }
}
