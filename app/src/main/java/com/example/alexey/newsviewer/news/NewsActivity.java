package com.example.alexey.newsviewer.news;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
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

    private static final String LIST_STATE_KEY = "list_state";

    private static Bundle mBundleRecyclerViewState;

    private LinearLayoutManager mLayoutManager;

    private RecyclerView mRecyclerView;

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

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        getWindow().setEnterTransition(new Explode());

        getWindow().setExitTransition(new Explode());
    }

    private void initRecyclerView() {

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        RecyclerView.Adapter mAdapter = new NewsAdapter(new ArrayList<>(), this);
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new MyItemTouchHelper();
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // save RecyclerView state
        mBundleRecyclerViewState = new Bundle();
        Parcelable listState = mRecyclerView.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable(LIST_STATE_KEY, listState);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // restore RecyclerView state
        if (mBundleRecyclerViewState != null) {
            Parcelable listState = mBundleRecyclerViewState.getParcelable(LIST_STATE_KEY);
            mRecyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }
}
