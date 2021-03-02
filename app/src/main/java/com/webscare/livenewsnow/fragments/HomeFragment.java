package com.webscare.livenewsnow.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.webscare.livenewsnow.R;
import com.webscare.livenewsnow.adapters.NewsItemAdapter;

public class HomeFragment extends Fragment {

    View view;
    RecyclerView rvHomeNewsHorizontally;
    LinearLayoutManager linearLayoutManager;
    public HomeFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        initializeView();
        showDataInView();

        return view;
    }

    private void showDataInView() {

        NewsItemAdapter newsItemAdapter = new NewsItemAdapter(getActivity());
        rvHomeNewsHorizontally.setAdapter(newsItemAdapter);
    }

    private void initializeView() {

        rvHomeNewsHorizontally=view.findViewById(R.id.rv_home_news_horizontally);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvHomeNewsHorizontally.setLayoutManager(linearLayoutManager);

    }
}
