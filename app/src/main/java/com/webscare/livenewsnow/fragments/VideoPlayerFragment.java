package com.webscare.livenewsnow.fragments;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.webscare.livenewsnow.R;
import com.webscare.livenewsnow.adapters.NewsItemAdapter;

public class VideoPlayerFragment extends Fragment implements View.OnClickListener{

    View view;
    RecyclerView rvNewsHorizontally,rvNewsVertically;
    LinearLayoutManager linearLayoutManager;
    LinearLayout btnBack,btnShare;
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

//        NewsItemAdapter newsItemAdapterHr = new NewsItemAdapter(getActivity(),"rvHorizontally");
//        rvNewsHorizontally.setAdapter(newsItemAdapterHr);
//        NewsItemAdapter newsItemAdapterVr = new NewsItemAdapter(getActivity(),"rvVertically");
//        rvNewsVertically.setAdapter(newsItemAdapterVr);
    }

    private void initializeView() {

        rvNewsHorizontally=view.findViewById(R.id.rv_news_horizontally);
        rvNewsVertically=view.findViewById(R.id.rv_news_vertically);
        btnBack = view.findViewById(R.id.btn_back);
        btnShare = view.findViewById(R.id.btn_share);
        setOrientationNewsHorizontallRv();
        setOrientationNewsVerticallyRv();

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
