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

public class AmericanFragment extends Fragment {

    View view;
    RecyclerView rvNewsHomeHorizontally,rvNewsHomeVertically,rvNewsFinanceHorizontally;
    LinearLayoutManager linearLayoutManager;
    public AmericanFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_american, container, false);

        initializeView();
        showDataInView();

        return view;
    }

    private void showDataInView() {

        NewsItemAdapter newsItemAdapterTopHr = new NewsItemAdapter(getActivity(),"rvTopHorizontally");
        rvNewsHomeHorizontally.setAdapter(newsItemAdapterTopHr);
        NewsItemAdapter newsItemAdapterVr = new NewsItemAdapter(getActivity(),"rvVertically");
        rvNewsHomeVertically.setAdapter(newsItemAdapterVr);
        NewsItemAdapter newsItemAdapterHrFinance = new NewsItemAdapter(getActivity(),"rvHorizontally");
        rvNewsFinanceHorizontally.setAdapter(newsItemAdapterHrFinance);

    }

    private void initializeView() {

        rvNewsHomeHorizontally=view.findViewById(R.id.rv_top_news_american_horizontally);
        rvNewsHomeVertically=view.findViewById(R.id.rv_news_american_vertically);
        rvNewsFinanceHorizontally=view.findViewById(R.id.rv_news_american_horizontally);

        setOrientationTopHorizontallRv();
        setOrientationHorizontallRv();
        setOrientationVerticallyRv();

    }

    private void setOrientationTopHorizontallRv() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvNewsHomeHorizontally.setLayoutManager(linearLayoutManager);
    }

    private void setOrientationVerticallyRv() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvNewsHomeVertically.setLayoutManager(linearLayoutManager);
    }

    private void setOrientationHorizontallRv() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvNewsFinanceHorizontally.setLayoutManager(linearLayoutManager);
    }
}
