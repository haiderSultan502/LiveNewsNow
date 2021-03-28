package com.webscare.livenewsnow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.potyvideo.library.AndExoPlayerView;
import com.potyvideo.library.globalEnums.EnumResizeMode;
import com.webscare.livenewsnow.Interface.InterfaceApi;
import com.webscare.livenewsnow.ModelsClasses.LiveChannelsModel;
import com.webscare.livenewsnow.adapters.LiveChannelAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveNewsChannels extends AppCompatActivity  implements View.OnClickListener  {

    RecyclerView recyclerViewRelatedNewsChannel;
    GridLayoutManager gridLayoutManager;
    LinearLayout btnBack,btnShare;

    AndExoPlayerView andExoPlayerView;  //  Remember : if you want that you screen rotate horizontally perfectly then must include config chnges in manifest ,
    InterfaceApi interfaceApi;
    Call<LiveChannelsModel> callForLiveChannels;

    TextView tvChannelText;
    String channelTitle,streamingLink;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_news_channels);

        initializeView();
        showDataInView();
    }

    private void showDataInView() {

        position = getIntent().getIntExtra("position",0);

        channelTitle = MainActivity.liveChannelsModel.getData().get(position).getTitle();

        streamingLink = MainActivity.liveChannelsModel.getData().get(position).getVideoUrl();

        andExoPlayerView.setSource(streamingLink);

        tvChannelText.setText(channelTitle);

//        getLiveNewsChannels("https://app.newslive.com/newslive/api/");

        LiveChannelAdapter liveChannelsAdapter = new LiveChannelAdapter(getApplicationContext(),MainActivity.liveChannelsModel);
        recyclerViewRelatedNewsChannel.setAdapter(liveChannelsAdapter);

        liveChannelsAdapter.setOnItemClickListener(new LiveChannelAdapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                finish();
                Intent intent = new Intent(LiveNewsChannels.this, LiveNewsChannels.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }

//    public void getLiveNewsChannels(String url)
//    {
//
//        try {
//            interfaceApi = RetrofitLibrary.connect(url);
//            callForLiveChannels = interfaceApi.getLiveChannels();
//            callForLiveChannels.enqueue(new Callback<LiveChannelsModel>() {
//                @Override
//                public void onResponse(Call<LiveChannelsModel> call, Response<LiveChannelsModel> response) {
//                    if (!response.isSuccessful())
//                    {
//
//                    }
//
//                    liveChannelsModel = response.body();
//
//                    channelTitle = liveChannelsModel.getData().get(0).getTitle();
//
//                    streamingLink = liveChannelsModel.getData().get(0).getVideoUrl();
//
//                    andExoPlayerView.setSource(streamingLink);
//
//                    tvChannelText.setText(channelTitle);
//
//
//                }
//
//                @Override
//                public void onFailure(Call<LiveChannelsModel> call, Throwable t) {
//                }
//            });
//        }
//        catch (Exception e)
//        {
//        }
//
//    }


    private void initializeView() {

        recyclerViewRelatedNewsChannel = findViewById(R.id.recycler_view_realted_live_channels);
        btnBack = findViewById(R.id.btn_back);
        btnShare = findViewById(R.id.btn_share);
        tvChannelText = findViewById(R.id.title_txt);

        andExoPlayerView = findViewById(R.id.andExoPlayerView);
        andExoPlayerView.setResizeMode(EnumResizeMode.FILL);
        andExoPlayerView.setShowController(true);

        setGridrvRv();

        btnBack.setOnClickListener(this);

    }

    private void setGridrvRv() {
        gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewRelatedNewsChannel.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_back:

                finish();

                break;
        }
    }
}