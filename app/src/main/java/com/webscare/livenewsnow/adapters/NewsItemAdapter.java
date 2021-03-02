package com.webscare.livenewsnow.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.webscare.livenewsnow.R;

public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemAdapter.ItemViewHolder> {

    View view;
    Context context;

    public NewsItemAdapter(Context context){
        this.context  = context;
    }
    @NonNull
    @Override
    public NewsItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.news_item_home_horizontally,parent,false);
        return new NewsItemAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsItemAdapter.ItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imgNews;
        TextView tvNewsTitle;
        LinearLayout newsItemClick;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imgNews = itemView.findViewById(R.id.img_news_item_title);
            tvNewsTitle = itemView.findViewById(R.id.tv_news_item_title);
            newsItemClick = itemView.findViewById(R.id.news_item_click);

        }
    }
}
