package com.example.alexey.newsviewer.news;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.alexey.newsviewer.R;
import com.example.alexey.newsviewer.adapters.NewsAdapter;
import com.example.alexey.newsviewer.databinding.ActivityNewsBinding;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityNewsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_news);

        initRecyclerView();

        NewsViewModel mViewModel = new NewsViewModel();
        binding.setViewModel(mViewModel);
    }

    private void initRecyclerView() {

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        RecyclerView.Adapter mAdapter = new NewsAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
    }
}
