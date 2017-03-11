package com.example.alexey.newsviewer.news;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.transition.Explode;
import android.view.Window;

import com.example.alexey.newsviewer.R;
import com.example.alexey.newsviewer.adapters.MyItemTouchHelper;
import com.example.alexey.newsviewer.adapters.NewsAdapter;
import com.example.alexey.newsviewer.databinding.ActivityNewsBinding;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            initAppearAnimation();
        }

        ActivityNewsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_news);

        initRecyclerView();

        NewsViewModel mViewModel = new NewsViewModel();
        binding.setViewModel(mViewModel);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initAppearAnimation() {

        // inside your activity (if you did not enable transitions in your theme)
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
// set an enter transition
        getWindow().setEnterTransition(new Explode());
// set an exit transition
        getWindow().setExitTransition(new Explode());
    }

    private void initRecyclerView() {

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        RecyclerView.Adapter mAdapter = new NewsAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new MyItemTouchHelper();
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);
    }
}
