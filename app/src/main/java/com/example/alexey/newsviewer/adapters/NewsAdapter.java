package com.example.alexey.newsviewer.adapters;

import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alexey.newsviewer.Constants;
import com.example.alexey.newsviewer.data.NewsRepository;
import com.example.alexey.newsviewer.databinding.NewsItemBinding;
import com.example.alexey.newsviewer.model.NewsItem;
import com.example.alexey.newsviewer.news_details.NewsDetailsActivity;
import com.example.alexey.newsviewer.utils.SquareImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey on 10/03/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ItemViewHolder> implements OnItemClickListener<NewsItem> {

    private List<NewsItem> news = new ArrayList<>();

    private Context mContext;

    public NewsAdapter(List<NewsItem> news, Context context) {
        this.news = news;
        mContext = context;
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(SquareImageView imageView, String url) {
        Picasso.with(imageView.getContext())
                .load(url)
                .noFade()
                .fit()
                .centerInside()
                .into(imageView);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(inflater, parent, false);

        return new ItemViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        NewsItem newsItem = news.get(position);
        holder.binding.setNews(newsItem);

        holder.binding.getRoot().setOnClickListener(v -> this.onItemClick(newsItem, v, holder.binding));
    }

    public void replaceData(List<NewsItem> items) {
        news = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    @Override
    public void onItemClick(NewsItem item, View v, NewsItemBinding binding) {
        Intent intent = new Intent(v.getContext(), NewsDetailsActivity.class);
        intent.putExtra(Constants.NEWS_URL, item.getUrl());

        // To use Shared Element Transition
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            Pair<View, String> pair1 = Pair.create(binding.imageView, binding.imageView.getTransitionName());

            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((AppCompatActivity) mContext, pair1);

            mContext.startActivity(intent, options.toBundle());

            return;
        }

        mContext.startActivity(intent);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        NewsItemBinding binding;

        ItemViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        void onSwiped() {
            NewsRepository.getInstance().removeNews(news.get(this.getAdapterPosition()).getUrl());

            news.remove(this.getAdapterPosition());
            notifyItemRemoved(this.getAdapterPosition());
        }
    }
}
