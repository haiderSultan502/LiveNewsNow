package com.webscare.livenewsnow.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import com.webscare.livenewsnow.ModelsClasses.LiveChannelsModel;
import com.webscare.livenewsnow.R;

public class LiveChannelAdapter extends RecyclerView.Adapter<LiveChannelAdapter.ItemViewHolder> {

    View view;
    Context context;
    LiveChannelsModel liveChannelsModel;
    String thumbnailLiveNewsChannelCompleteUrl,thumbnailLiveNewsChannelBaseUrl;
    int size;
    private onRecyclerViewItemClickListener mItemClickListener;


    public LiveChannelAdapter(Context context,LiveChannelsModel liveChannelsModel) {
        this.context = context;
        this.liveChannelsModel = liveChannelsModel;
    }

    @NonNull
    @Override
    public LiveChannelAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        view = LayoutInflater.from(context).inflate(R.layout.live_channel_item, parent, false);

        return new LiveChannelAdapter.ItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull LiveChannelAdapter.ItemViewHolder holder, int position) {

        thumbnailLiveNewsChannelBaseUrl = "https://app.newslive.com/newslive/storage/app/public/NewsTimeimages/";
        thumbnailLiveNewsChannelCompleteUrl = thumbnailLiveNewsChannelBaseUrl +  liveChannelsModel.getData().get(position).getCardImageUrl();
        Picasso.with(context).load(thumbnailLiveNewsChannelCompleteUrl).placeholder(R.drawable.loading).error(R.drawable.loading).into(holder.thumbnialLiveChannel);

    }


    @Override
    public int getItemCount() {
        try {
            size =  liveChannelsModel.getData().size();
        }
        catch (Exception e)
        {
        }
        return size;
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener
    {
        ImageView thumbnialLiveChannel;
        CardView itemClick;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnialLiveChannel = itemView.findViewById(R.id.thumbnail_live_channels);
            itemClick=itemView.findViewById(R.id.item_click);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            switch (view.getId()) {


                case R.id.item_click:
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClickListener(v, getAdapterPosition());
                    }
                    break;


            }
        }
    }

    public void setOnItemClickListener(onRecyclerViewItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
    public interface onRecyclerViewItemClickListener {
        void onItemClickListener(View view, int position);
    }

}

