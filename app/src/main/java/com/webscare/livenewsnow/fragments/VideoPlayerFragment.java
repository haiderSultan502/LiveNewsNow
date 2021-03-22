package com.webscare.livenewsnow.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.potyvideo.library.AndExoPlayerView;
import com.potyvideo.library.globalEnums.EnumResizeMode;
import com.webscare.livenewsnow.Interface.InterfaceApi;
import com.webscare.livenewsnow.ModelsClasses.LiveChannelsModel;
import com.webscare.livenewsnow.R;
import com.webscare.livenewsnow.RetrofitLibrary;
import com.webscare.livenewsnow.adapters.NewsItemAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoPlayerFragment extends Fragment implements View.OnClickListener{

    View view;
    RecyclerView rvNewsHorizontally,rvNewsVertically;
    LinearLayoutManager linearLayoutManager;
    LinearLayout btnBack,btnShare;

    AndExoPlayerView andExoPlayerView;
    InterfaceApi interfaceApi;
    Call<LiveChannelsModel> callForLiveChannels;
    LiveChannelsModel liveChannelsModel = new LiveChannelsModel();

    TextView tvChannelText;
    String channelTitle,streamingLink;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_video_player, container, false);

        initializeView();
        showDataInView();

        return view;
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

//        rvNewsHorizontally=view.findViewById(R.id.rv_news_horizontally);
//        rvNewsVertically=view.findViewById(R.id.rv_news_vertically);
        btnBack = view.findViewById(R.id.btn_back);
        btnShare = view.findViewById(R.id.btn_share);
        tvChannelText = view.findViewById(R.id.title_txt);

        andExoPlayerView = view.findViewById(R.id.andExoPlayerView);
        andExoPlayerView.setResizeMode(EnumResizeMode.FILL);
        andExoPlayerView.setShowController(true);


//        setOrientationNewsHorizontallRv();
//        setOrientationNewsVerticallyRv();

        btnBack.setOnClickListener(this);

    }

    private void setOrientationNewsVerticallyRv() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvNewsVertically.setLayoutManager(linearLayoutManager);
    }

    private void setOrientationNewsHorizontallRv() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
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
