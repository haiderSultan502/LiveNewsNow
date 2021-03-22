package com.webscare.livenewsnow.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.webscare.livenewsnow.MainActivity;
import com.webscare.livenewsnow.ModelsClasses.NewsModel;
import com.webscare.livenewsnow.R;
import com.webscare.livenewsnow.fragments.PostWebpageFragment;

import java.util.ArrayList;

public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemAdapter.ItemViewHolder> {

    View view;
    Context context;
    String checkView,newsThumbnail,newsTitle,newsUrl;
    MainActivity mainActivity = new MainActivity();
    ArrayList<NewsModel> arrayListHomeNews;
    PostWebpageFragment postWebpageFragment = new PostWebpageFragment();

    public NewsItemAdapter(Context context, String checkView, ArrayList<NewsModel> arrayListHomeNews){

        this.arrayListHomeNews = new ArrayList<>();

        this.context  = context;
        this.checkView = checkView;
        this.arrayListHomeNews = arrayListHomeNews;
    }
    @NonNull
    @Override
    public NewsItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (checkView.equals("rvHorizontally"))
        {
            view = LayoutInflater.from(context).inflate(R.layout.news_item_home_horizontally,parent,false);
        }
        else if (checkView.equals("rvVertically"))
        {
            view = LayoutInflater.from(context).inflate(R.layout.news_item_home_vertically,parent,false);
        }
        else if (checkView.equals("rvTopHorizontally"))
        {
            view = LayoutInflater.from(context).inflate(R.layout.news_item_horizontally,parent,false);
        }
        return new NewsItemAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsItemAdapter.ItemViewHolder holder, int position) {


        newsThumbnail = arrayListHomeNews.get(position).getFeaturedMedia().get(0);
        newsTitle = arrayListHomeNews.get(position).getTitle();


        Picasso.with(context).load(newsThumbnail).placeholder(R.drawable.loading).error(R.drawable.loading).into(holder.imgNews);
        holder.tvNewsTitle.setText(newsTitle);



        holder.newsItemClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newsUrl = arrayListHomeNews.get(position).getGuid();
                newsThumbnail = arrayListHomeNews.get(position).getFeaturedMedia().get(0);  // if you use above newsThumbnail then you will get wrong data corresponding item click therefore I get data against position on item click again

                Bundle bundle = new Bundle();
                bundle.putString("newsUrl",newsUrl);
                bundle.putString("newsThumbnail",newsThumbnail);
                postWebpageFragment.setArguments(bundle);


                String checkFragmentStatus = mainActivity.checkFragStatus;
                switch (checkFragmentStatus)
                {
                    case "homeFragment":
                        postWebPageFragment(R.id.frame_layout);
                        break;
                    case "americanFragment":
                        postWebPageFragment(R.id.frame_layout2);
                        break;
                    case "updatesFragment":
                        postWebPageFragment(R.id.frame_layout3);
                        break;
                    case "businessFragment":
                        postWebPageFragment(R.id.frame_layout4);
                        break;
                    case "searchFragment":
                        postWebPageFragment(R.id.frame_layout_at_viewpager);
                        break;

                }
            }
        });


    }

    private void postWebPageFragment(int frameLayoutID) {

        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                .replace(frameLayoutID, postWebpageFragment).addToBackStack(null)
                .commit();
    }



    @Override
    public int getItemCount() {
        return arrayListHomeNews.size();
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
