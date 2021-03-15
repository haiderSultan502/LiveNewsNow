package com.webscare.livenewsnow.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.webscare.livenewsnow.Interface.InterfaceApi;
import com.webscare.livenewsnow.ModelsClasses.NewsModel;
import com.webscare.livenewsnow.R;
import com.webscare.livenewsnow.RetrofitLibrary;
import com.webscare.livenewsnow.adapters.NewsItemAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    View view;
    RecyclerView rvNewsHorizontally,rvNewsVertically,rvNewsCategoryHorizontally,rvNewsCategoryVertically;
    LinearLayoutManager linearLayoutManager;
    RelativeLayout rlNewsClick;
    PostWebpageFragment postWebpageFragment = new PostWebpageFragment();
    FrameLayout frameLayoutHF;
    InterfaceApi interfaceApi;
    Call<List<NewsModel>> callForTopStories;

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
        rvNewsHorizontally.setAdapter(newsItemAdapterHr);
        NewsItemAdapter newsItemAdapterVr = new NewsItemAdapter(getActivity(),"rvVertically");
        rvNewsVertically.setAdapter(newsItemAdapterVr);
        NewsItemAdapter newsItemAdapterHrFinance = new NewsItemAdapter(getActivity(),"rvHorizontally");
        rvNewsCategoryHorizontally.setAdapter(newsItemAdapterHrFinance);
        NewsItemAdapter newsItemAdapterVrFinance = new NewsItemAdapter(getActivity(),"rvVertically");
        rvNewsCategoryVertically.setAdapter(newsItemAdapterVrFinance);
    }

    private void initializeView() {

        rvNewsHorizontally=view.findViewById(R.id.rv_news_home_horizontally);
        rvNewsVertically=view.findViewById(R.id.rv_news_home_vertically);
        rvNewsCategoryHorizontally=view.findViewById(R.id.rv_news_category_horizontally);
        rvNewsCategoryVertically=view.findViewById(R.id.rv_news_category_vertically);
        rlNewsClick= view.findViewById(R.id.news_click);
        frameLayoutHF = view.findViewById(R.id.frame_layout);
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

        getTopStories("https://www.livenewsnow.com/wp-json/newspaper/v2/");

    }

    private void getTopStories(String url) {
        interfaceApi = RetrofitLibrary.connect(url);
        callForTopStories = interfaceApi.getTopStories();
        callForTopStories.enqueue(new Callback<List<NewsModel>>() {
            @Override
            public void onResponse(Call<List<NewsModel>> call, Response<List<NewsModel>> response) {

                if (!response.isSuccessful())
                {
                    Toast.makeText(getActivity(), "Please try later", Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<List<NewsModel>> call, Throwable t) {

            }
        });

    }

    private void setOrientationFinanceHorizontallRv() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvNewsCategoryHorizontally.setLayoutManager(linearLayoutManager);
    }

    private void setOrientationFinanceVerticallyRv() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvNewsCategoryVertically.setLayoutManager(linearLayoutManager);
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
