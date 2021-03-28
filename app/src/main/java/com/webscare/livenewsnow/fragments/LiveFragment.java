package com.webscare.livenewsnow.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.webscare.livenewsnow.Interface.InterfaceApi;
import com.webscare.livenewsnow.LiveNewsChannels;
import com.webscare.livenewsnow.MainActivity;
import com.webscare.livenewsnow.ModelsClasses.LiveChannelsModel;
import com.webscare.livenewsnow.R;
import com.webscare.livenewsnow.RetrofitLibrary;
import com.webscare.livenewsnow.adapters.LiveChannelAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveFragment extends Fragment {

    View view;
    RecyclerView rvLiveItemGridview;
    GridLayoutManager gridLayoutManager;

    InterfaceApi interfaceApi;
    Call<LiveChannelsModel> callForLiveChannels;

    String channelTitle,streamingLink;
    static Context context;

    public LiveFragment(){

    }

    public LiveFragment(Context context) {

        this.context= context;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_live, container, false);

//        Log.d("call","all");

        initializeView();
        showDataInView();
        return view;

    }

    private void showDataInView() {

        getLiveNewsChannels("https://app.newslive.com/newslive/api/");



//        NewsItemAdapter newsItemAdapterTopHr = new NewsItemAdapter(getActivity(),"rvTopHorizontally");
//        rvNewsHorizontally.setAdapter(newsItemAdapterTopHr);

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

                    MainActivity.liveChannelsModel = response.body();

                    LiveChannelAdapter liveChannelsAdapter = new LiveChannelAdapter(getActivity(),MainActivity.liveChannelsModel);
                    rvLiveItemGridview.setAdapter(liveChannelsAdapter);
                    liveChannelsAdapter.setOnItemClickListener(new LiveChannelAdapter.onRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClickListener(View view, int position) {
                            Intent intent = new Intent(context, LiveNewsChannels.class);
                            intent.putExtra("position", position);
                            context.startActivity(intent);
                        }
                    });



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

        rvLiveItemGridview=view.findViewById(R.id.rv_live_item_gridview);

        setGridRv();

    }

    private void setGridRv() {

        gridLayoutManager = new GridLayoutManager(getActivity(),3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvLiveItemGridview.setLayoutManager(gridLayoutManager);
    }
}