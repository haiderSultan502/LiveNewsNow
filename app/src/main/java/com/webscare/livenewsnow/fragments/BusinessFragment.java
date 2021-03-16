package com.webscare.livenewsnow.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.webscare.livenewsnow.MainActivity;
import com.webscare.livenewsnow.R;
import com.webscare.livenewsnow.adapters.NewsItemAdapter;

public class BusinessFragment extends Fragment {

    View view;
    RecyclerView rvNewsHorizontally,rvNewsVertically,rvNewsHorizontallyMore;
    LinearLayoutManager linearLayoutManager;
    FrameLayout frameLayoutAF;

    public BusinessFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_business, container, false);

//        Log.d("call","all");

        initializeView();
        showDataInView();

        return view;
    }

    private void showDataInView() {

        //        NewsItemAdapter newsItemAdapterTopHr = new NewsItemAdapter(getActivity(),"rvTopHorizontally");
//        rvNewsHorizontally.setAdapter(newsItemAdapterTopHr);
//        NewsItemAdapter newsItemAdapterVr = new NewsItemAdapter(getActivity(),"rvVertically");
//        rvNewsVertically.setAdapter(newsItemAdapterVr);
//        NewsItemAdapter newsItemAdapterHrFeatured = new NewsItemAdapter(getActivity(),"rvHorizontally");
//        rvNewsHorizontallyMore.setAdapter(newsItemAdapterHrFeatured);

    }

    private void initializeView() {

        rvNewsHorizontally=view.findViewById(R.id.rv_news_horizontally);
        rvNewsVertically=view.findViewById(R.id.rv_news_vertically);
        rvNewsHorizontallyMore=view.findViewById(R.id.rv_news_horizontally_more);
        frameLayoutAF = view.findViewById(R.id.frame_layout2);

        setOrientationTopHorizontallRv();
        setOrientationHorizontallRv();
        setOrientationVerticallyRv();

    }

    private void setOrientationTopHorizontallRv() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvNewsHorizontally.setLayoutManager(linearLayoutManager);
    }

    private void setOrientationVerticallyRv() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvNewsVertically.setLayoutManager(linearLayoutManager);
    }

    private void setOrientationHorizontallRv() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvNewsHorizontallyMore.setLayoutManager(linearLayoutManager);
    }
}
