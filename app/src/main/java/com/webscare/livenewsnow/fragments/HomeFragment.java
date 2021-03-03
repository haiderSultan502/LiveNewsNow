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
    RecyclerView rvTopNewsHorizontally,rvNewsVertically,rvNewsHorizontally,rvFinanceHomeNewsVertically;
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

        NewsItemAdapter newsItemAdapterHr = new NewsItemAdapter(getActivity(),"rvHorizontally");
        rvTopNewsHorizontally.setAdapter(newsItemAdapterHr);
        NewsItemAdapter newsItemAdapterVr = new NewsItemAdapter(getActivity(),"rvVertically");
        rvNewsVertically.setAdapter(newsItemAdapterVr);
        NewsItemAdapter newsItemAdapterHrFinance = new NewsItemAdapter(getActivity(),"rvHorizontally");
        rvNewsHorizontally.setAdapter(newsItemAdapterHrFinance);
        NewsItemAdapter newsItemAdapterVrFinance = new NewsItemAdapter(getActivity(),"rvVertically");
        rvFinanceHomeNewsVertically.setAdapter(newsItemAdapterVrFinance);
    }

    private void initializeView() {

        rvTopNewsHorizontally=view.findViewById(R.id.rv_home_news_horizontally);
        rvNewsVertically=view.findViewById(R.id.rv_home_news_vertically);
        rvNewsHorizontally=view.findViewById(R.id.rv_finance_home_news_horizontally);
        rvFinanceHomeNewsVertically=view.findViewById(R.id.rv_finance_home_news_vertically);
        setOrientationHorizontallRv();
        setOrientationVerticallyRv();
        setOrientationTopHorizontallRv();
        setOrientationFinanceVerticallyRv();

    }

    private void setOrientationTopHorizontallRv() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvNewsHorizontally.setLayoutManager(linearLayoutManager);
    }
    private void setOrientationFinanceVerticallyRv() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvFinanceHomeNewsVertically.setLayoutManager(linearLayoutManager);
    }

    private void setOrientationVerticallyRv() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvNewsVertically.setLayoutManager(linearLayoutManager);
    }

    private void setOrientationHorizontallRv() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvTopNewsHorizontally.setLayoutManager(linearLayoutManager);
    }
}
