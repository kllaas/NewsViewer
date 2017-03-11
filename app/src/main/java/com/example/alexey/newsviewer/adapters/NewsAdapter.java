package com.example.alexey.newsviewer.adapters;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.alexey.newsviewer.databinding.NewsItemBinding;
import com.example.alexey.newsviewer.model.NewsItem;
import com.example.alexey.newsviewer.utils.SquareImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey on 10/03/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder> {

    private List<NewsItem> news = new ArrayList<>();

    private OnItemClickListener<NewsItem> onItemClickListener = (position, item, view) ->
            Toast.makeText(view.getContext(), "onClick" + position, Toast.LENGTH_SHORT).show();

    public NewsAdapter(List<NewsItem> news) {
        this.news = news;
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(SquareImageView imageView, String v) {
        Picasso.Builder builder = new Picasso.Builder(imageView.getContext());
        builder.listener((picasso, uri, exception) -> Log.d("LoadImage", exception.getMessage()));
        builder.build().load(v).into(imageView);
    }

    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(inflater, parent, false);

        return new NewsItemViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(NewsItemViewHolder holder, int position) {
        NewsItem newsItem = news.get(position);
        holder.binding.setNews(newsItem);

        holder.binding.getRoot().setOnClickListener(v -> {
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(position, newsItem, v);
        });
    }

    public void replaceData(List<NewsItem> items) {
        news = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class NewsItemViewHolder extends RecyclerView.ViewHolder {

        NewsItemBinding binding;

        public NewsItemViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
