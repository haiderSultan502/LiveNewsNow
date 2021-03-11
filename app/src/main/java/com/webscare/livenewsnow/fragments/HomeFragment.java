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

import com.webscare.livenewsnow.MainActivity;
import com.webscare.livenewsnow.R;
import com.webscare.livenewsnow.adapters.NewsItemAdapter;

public class HomeFragment extends Fragment {

    View view;
    RecyclerView rvNewsHorizontally,rvNewsVertically,rvNewsFinanceHorizontally,rvNewsFinanceVertically;
    LinearLayoutManager linearLayoutManager;
    RelativeLayout rlNewsClick;
    PostWebpageFragment postWebpageFragment = new PostWebpageFragment();
    MainActivity mainActivity = new MainActivity();
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

        mainActivity.checkFragStatus = "home";
        initializeView();
        showDataInView();

        return view;
    }

    private void showDataInView() {

        NewsItemAdapter newsItemAdapterHr = new NewsItemAdapter(getActivity(),"rvHorizontally");
        rvNewsHorizontally.setAdapter(newsItemAdapterHr);
        NewsItemAdapter newsItemAdapterVr = new NewsItemAdapter(getActivity(),"rvVertically");
        rvNewsVertically.setAdapter(newsItemAdapterVr);
        NewsItemAdapter newsItemAdapterHrFinance = new NewsItemAdapter(getActivity(),"rvHorizontally");
        rvNewsFinanceHorizontally.setAdapter(newsItemAdapterHrFinance);
        NewsItemAdapter newsItemAdapterVrFinance = new NewsItemAdapter(getActivity(),"rvVertically");
        rvNewsFinanceVertically.setAdapter(newsItemAdapterVrFinance);
    }

    private void initializeView() {

        rvNewsHorizontally=view.findViewById(R.id.rv_news_home_horizontally);
        rvNewsVertically=view.findViewById(R.id.rv_news_home_vertically);
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
        setOrientationNewsHorizontallRv();
        setOrientationNewsVerticallyRv();
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
}
