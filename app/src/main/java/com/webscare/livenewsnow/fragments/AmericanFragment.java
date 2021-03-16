package com.webscare.livenewsnow.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.webscare.livenewsnow.Interface.InterfaceApi;
import com.webscare.livenewsnow.MainActivity;
import com.webscare.livenewsnow.ModelsClasses.NewsModel;
import com.webscare.livenewsnow.R;
import com.webscare.livenewsnow.RetrofitLibrary;
import com.webscare.livenewsnow.adapters.NewsItemAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AmericanFragment extends Fragment {

    View view;
    RecyclerView rvNewsHorizontally,rvNewsVertically,rvNewsHorizontallyMore;
    LinearLayoutManager linearLayoutManager;
    FrameLayout frameLayoutAF;
    InterfaceApi interfaceApi;
    Boolean isScrooling = false;
    int currentItem,totalItems,scrollOutItems;
    String categortIDAndPageNumber;

    ProgressBar progressBar;
    Call<List<NewsModel>> callForNews;
    ArrayList<NewsModel> arrayListAmericanNews,arrayListTopAmericanNewsHr,arrayListAmericanNewsVr,arrayListAmericanNewsHr;

    String url= "https://www.livenewsnow.com/wp-json/newspaper/v2/";
    int pageNumber = 1;

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

//        Log.d("call","all");

        initializeView();

        return view;
    }

    private void showDataInView() {

        NewsItemAdapter newsItemAdapterTopHr = new NewsItemAdapter(getActivity(),"rvTopHorizontally",arrayListTopAmericanNewsHr);
        rvNewsHorizontally.setAdapter(newsItemAdapterTopHr);
        NewsItemAdapter newsItemAdapterVr = new NewsItemAdapter(getActivity(),"rvVertically",arrayListAmericanNewsVr);
        rvNewsVertically.setAdapter(newsItemAdapterVr);
        NewsItemAdapter newsItemAdapterHrFeatured = new NewsItemAdapter(getActivity(),"rvHorizontally",arrayListAmericanNewsHr);
        rvNewsHorizontallyMore.setAdapter(newsItemAdapterHrFeatured);

    }

    private void initializeView() {

        rvNewsHorizontally=view.findViewById(R.id.rv_news_horizontally);
        rvNewsVertically=view.findViewById(R.id.rv_news_vertically);
        rvNewsHorizontallyMore=view.findViewById(R.id.rv_news_horizontally_more);
        frameLayoutAF = view.findViewById(R.id.frame_layout2);

        arrayListAmericanNews = new ArrayList<>();
        arrayListTopAmericanNewsHr = new ArrayList<>();
        arrayListAmericanNewsVr = new ArrayList<>();
        arrayListAmericanNewsHr = new ArrayList<>();


        setOrientationTopHorizontallRv();
        setOrientationHorizontallRv();
        setOrientationVerticallyRv();

//        getAmericanNews(pageNumber);


    }

    private void getAmericanNews(int pageNumber) {

        categortIDAndPageNumber = " 4 | " +  String.valueOf(pageNumber);
        interfaceApi = RetrofitLibrary.connect(url);
        callForNews = interfaceApi.getAllCategoriesNews(categortIDAndPageNumber);
        callForNews.enqueue(new Callback<List<NewsModel>>() {
            @Override
            public void onResponse(Call<List<NewsModel>> call, Response<List<NewsModel>> response) {

                if (!response.isSuccessful())
                {
                    Toast.makeText(getActivity(), "Please try later", Toast.LENGTH_SHORT).show();
                }

                arrayListAmericanNews = (ArrayList<NewsModel>) response.body();

                for (int i = 0 ; i < 4 ; i++)
                {
                    arrayListTopAmericanNewsHr.add(arrayListAmericanNews.get(i));
                }

                for (int i = 4 ; i < 8 ; i++)
                {
                    arrayListAmericanNewsVr.add(arrayListAmericanNews.get(i));
                }

                for (int i = 8 ; i < 11 ; i++)
                {
                    arrayListAmericanNewsHr.add(arrayListAmericanNews.get(i));
                }

                showDataInView();




            }

            @Override
            public void onFailure(Call<List<NewsModel>> call, Throwable t) {

            }
        });

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
