package com.webscare.livenewsnow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.potyvideo.library.AndExoPlayerView;
import com.potyvideo.library.globalEnums.EnumResizeMode;
import com.webscare.livenewsnow.Interface.InterfaceApi;
import com.webscare.livenewsnow.ModelsClasses.LiveChannelsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveNewsChannels extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rvNewsHorizontally,rvNewsVertically;
    LinearLayoutManager linearLayoutManager;
    LinearLayout btnBack,btnShare;

    AndExoPlayerView andExoPlayerView;  //  Remember : if you want that you screen rotate horizontally perfectly then must include config chnges in manifest ,
    InterfaceApi interfaceApi;
    Call<LiveChannelsModel> callForLiveChannels;
    LiveChannelsModel liveChannelsModel = new LiveChannelsModel();

    TextView tvChannelText;
    String channelTitle,streamingLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_news_channels);

        initializeView();
        showDataInView();
    }

    private void showDataInView() {

        getLiveNewsChannels("https://app.newslive.com/newslive/api/");

//        NewsItemAdapter newsItemAdapterHr = new NewsItemAdapter(getActivity(),"rvHorizontally");
//        rvNewsHorizontally.setAdapter(newsItemAdapterHr);
//        NewsItemAdapter newsItemAdapterVr = new NewsItemAdapter(getActivity(),"rvVertically");
//        rvNewsVertically.setAdapter(newsItemAdapterVr);
    }

    public void getLiveNewsChannels(String url)
    {

        try {
            interfaceApi = RetrofitLibrary.connect(url);
            callForLiveChannels = interfaceApi.getLiveChannels();
            callForLiveChannels.enqueue(new Callback<LiveChannelsModel>() {
                @Override
                public void onResponse(Call<LiveChannelsModel> call, Response<LiveChannelsModel> response) {
                    if (!response.isSuccessful())
                    {

                    }

                    liveChannelsModel = response.body();

                    channelTitle = liveChannelsModel.getData().get(0).getTitle();

                    streamingLink = liveChannelsModel.getData().get(0).getVideoUrl();

                    andExoPlayerView.setSource(streamingLink);

                    tvChannelText.setText(channelTitle);


                }

                @Override
                public void onFailure(Call<LiveChannelsModel> call, Throwable t) {
                }
            });
        }
        catch (Exception e)
        {
        }

    }


    private void initializeView() {

        rvNewsHorizontally=findViewById(R.id.rv_news_horizontally);
        rvNewsVertically=findViewById(R.id.rv_news_vertically);
        btnBack = findViewById(R.id.btn_back);
        btnShare = findViewById(R.id.btn_share);
        tvChannelText = findViewById(R.id.title_txt);

        andExoPlayerView = findViewById(R.id.andExoPlayerView);
        andExoPlayerView.setResizeMode(EnumResizeMode.FILL);
        andExoPlayerView.setShowController(true);


        setOrientationNewsHorizontallRv();
        setOrientationNewsVerticallyRv();

        btnBack.setOnClickListener(this);

    }

    private void setOrientationNewsVerticallyRv() {
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvNewsVertically.setLayoutManager(linearLayoutManager);
    }

    private void setOrientationNewsHorizontallRv() {
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvNewsHorizontally.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_back:
                if (getFragmentManager().getBackStackEntryCount() != 0) {
                    getFragmentManager().popBackStack();
                }
                break;
        }
    }
}