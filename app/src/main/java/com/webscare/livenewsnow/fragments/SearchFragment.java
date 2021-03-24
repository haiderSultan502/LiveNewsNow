package com.webscare.livenewsnow.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.webscare.livenewsnow.Interface.InterfaceApi;
import com.webscare.livenewsnow.MainActivity;
import com.webscare.livenewsnow.ModelsClasses.NewsModel;
import com.webscare.livenewsnow.ModelsClasses.SearchNewsModel;
import com.webscare.livenewsnow.R;
import com.webscare.livenewsnow.RetrofitLibrary;
import com.webscare.livenewsnow.adapters.NewsItemAdapter;
import com.webscare.livenewsnow.adapters.SearchItemAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    RecyclerView recyclerViewSearchtNews;
    LinearLayoutManager linearLayoutManager;
    View view;
//    Button click;

    String searchKeyword;
    Context context;
    InterfaceApi interfaceApi;
    Call<List<SearchNewsModel>> callForSearchNews;
    ArrayList<SearchNewsModel> arrayListSearchNews;
    String url = "https://www.livenewsnow.com/wp-json/wp/v2/";




    public SearchFragment(Context context,String searchKeyword) {
        this.context=context;
        this.searchKeyword=searchKeyword;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_search,container,false);

        initializeView();

        return view;
    }

    private void initializeView() {

        recyclerViewSearchtNews=view.findViewById(R.id.rv_search_news);
        arrayListSearchNews = new ArrayList<>();
//        click = view.findViewById(R.id.click);
//        click.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (getFragmentManager().getBackStackEntryCount() != 0) {
//                getFragmentManager().popBackStack();
//            }
//            }
//        });

        setOrientationSearchRv();

        getSearchNews(searchKeyword);

    }

    private void showDataInViews() {

        SearchItemAdapter searchItemAdapter = new SearchItemAdapter(getActivity(),"rvVertically",arrayListSearchNews);
        recyclerViewSearchtNews.setAdapter(searchItemAdapter);

    }

    public void getSearchNews(String searchKeyword)
    {
        try {
            interfaceApi = RetrofitLibrary.connect(url);
            callForSearchNews = interfaceApi.getSearchNews(searchKeyword);

            callForSearchNews.enqueue(new Callback<List<SearchNewsModel>>() {
                @Override
                public void onResponse(Call<List<SearchNewsModel>> call, Response<List<SearchNewsModel>> response) {
                    if (!response.isSuccessful())
                    {
                        Toast.makeText(getActivity(), "Please try later", Toast.LENGTH_SHORT).show();
                    }
                    arrayListSearchNews = (ArrayList<SearchNewsModel>) response.body();

                    if (arrayListSearchNews.size() == 0 )
                    {
                        Toast.makeText(getActivity(), "No data found", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        showDataInViews();
                    }



                    MainActivity.animationHide();

                }

                @Override
                public void onFailure(Call<List<SearchNewsModel>> call, Throwable t) {

                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(), "no data found", Toast.LENGTH_SHORT).show();
        }

    }

    private void setOrientationSearchRv() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewSearchtNews.setLayoutManager(linearLayoutManager);
    }
}
