package com.webscare.livenewsnow.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.webscare.livenewsnow.R;
import com.webscare.livenewsnow.adapters.NewsItemAdapter;

public class HomeFragment extends Fragment {

    View view;
    RecyclerView rvNewsHomeHorizontally,rvNewsHomeVertically,rvNewsFinanceHorizontally,rvNewsFinanceVertically;
    LinearLayoutManager linearLayoutManager;
    RelativeLayout rlNewsClick;
    PostWebpageFragment postWebpageFragment = new PostWebpageFragment();
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
        rvNewsHomeHorizontally.setAdapter(newsItemAdapterHr);
        NewsItemAdapter newsItemAdapterVr = new NewsItemAdapter(getActivity(),"rvVertically");
        rvNewsHomeVertically.setAdapter(newsItemAdapterVr);
        NewsItemAdapter newsItemAdapterHrFinance = new NewsItemAdapter(getActivity(),"rvHorizontally");
        rvNewsFinanceHorizontally.setAdapter(newsItemAdapterHrFinance);
        NewsItemAdapter newsItemAdapterVrFinance = new NewsItemAdapter(getActivity(),"rvVertically");
        rvNewsFinanceVertically.setAdapter(newsItemAdapterVrFinance);
    }

    private void initializeView() {

        rvNewsHomeHorizontally=view.findViewById(R.id.rv_news_home_horizontally);
        rvNewsHomeVertically=view.findViewById(R.id.rv_news_home_vertically);
        rvNewsFinanceHorizontally=view.findViewById(R.id.rv_news_finance_horizontally);
        rvNewsFinanceVertically=view.findViewById(R.id.rv_news_finance_vertically);
        rlNewsClick= view.findViewById(R.id.news_click);
        rlNewsClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentActivity)getActivity()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, postWebpageFragment).addToBackStack(null)
                        .commit();
            }
        });
        setOrientationNewsHomeHorizontallRv();
        setOrientationNewsHomeVerticallyRv();
        setOrientationFinanceHorizontallRv();
        setOrientationFinanceVerticallyRv();

    }

    private void setOrientationFinanceHorizontallRv() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvNewsFinanceHorizontally.setLayoutManager(linearLayoutManager);
    }
    private void setOrientationFinanceVerticallyRv() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvNewsFinanceVertically.setLayoutManager(linearLayoutManager);
    }

    private void setOrientationNewsHomeVerticallyRv() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvNewsHomeVertically.setLayoutManager(linearLayoutManager);
    }

    private void setOrientationNewsHomeHorizontallRv() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvNewsHomeHorizontally.setLayoutManager(linearLayoutManager);
    }
}
