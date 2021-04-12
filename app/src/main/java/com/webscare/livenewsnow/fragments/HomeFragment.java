package com.webscare.livenewsnow.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
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

public class HomeFragment extends Fragment {

    View view;
    RecyclerView rvNewsHorizontally,rvNewsVertically,rvNewsCategoryHorizontally,rvNewsCategoryVertically;
    LinearLayoutManager linearLayoutManager;
    RelativeLayout rlNewsClick;
    PostWebpageFragment postWebpageFragment = new PostWebpageFragment();
    FrameLayout frameLayoutHF;
    LinearLayout layoutFeaturedTitle;
    InterfaceApi interfaceApi;
    ImageView thumbnailTopStoryImv;
    TextView thumbnailTopStoryTitleTv;
    Boolean isScrooling = false;
    int currentItem,totalItems,scrollOutItems;

    String newsThumbnail,newsUrl;

    ProgressBar progressBar;

    Call<List<NewsModel>> callForNews;
    ArrayList<NewsModel> arrayListTopStories,arrayListTopStoriesHr,arrayListTopStoriesVr,arrayListFeaturedNewsHr,arrayListFeaturedNewsVr;
    String thumbnailTopStoryStr,thumbnailTopStoryTitleStr,categortIDAndPageNumber;
    NewsItemAdapter newsItemAdapterHrFeatured;

    String url= "https://www.livenewsnow.com/wp-json/Newspaper/v2/";
    int pageNumber = 1;

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

        return view;
    }

    private void showDataInView() {

        NewsItemAdapter newsItemAdapterHr = new NewsItemAdapter(getActivity(),"rvHorizontally",arrayListTopStoriesHr);
        rvNewsHorizontally.setAdapter(newsItemAdapterHr);
        NewsItemAdapter newsItemAdapterVr = new NewsItemAdapter(getActivity(),"rvVertically",arrayListTopStoriesVr);
        rvNewsVertically.setAdapter(newsItemAdapterVr);
//        NewsItemAdapter newsItemAdapterVrFinance = new NewsItemAdapter(getActivity(),"rvVertically");
//        rvNewsCategoryVertically.setAdapter(newsItemAdapterVrFinance);

        MainActivity.animationHide();

    }

    private void initializeView() {

        rvNewsHorizontally=view.findViewById(R.id.rv_news_home_horizontally);
        rvNewsVertically=view.findViewById(R.id.rv_news_home_vertically);
        rvNewsCategoryHorizontally=view.findViewById(R.id.rv_news_category_horizontally);
        rvNewsCategoryVertically=view.findViewById(R.id.rv_news_category_vertically);
        rlNewsClick= view.findViewById(R.id.news_click);
        frameLayoutHF = view.findViewById(R.id.frame_layout);
        thumbnailTopStoryImv = view.findViewById(R.id.thumbnail_top_stories);
        thumbnailTopStoryTitleTv = view.findViewById(R.id.news_title_top_stories);
        progressBar = view.findViewById(R.id.progress_bar);

        arrayListTopStories = new ArrayList<>();
        arrayListTopStoriesHr = new ArrayList<>();
        arrayListTopStoriesVr = new ArrayList<>();
        arrayListFeaturedNewsHr = new ArrayList<>();
        arrayListFeaturedNewsVr = new ArrayList<>();


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
        setOrientationFeaturedHorizontallRv();
        setOrientationFeaturedVerticallyRv();

        getTopStories("https://www.livenewsnow.com/wp-json/Newspaper/v2/");

    }

    private void getTopStories(String urlNews) {

        try {
            interfaceApi = RetrofitLibrary.connect(urlNews);
            callForNews = interfaceApi.getTopStories();
            callForNews.enqueue(new Callback<List<NewsModel>>() {
                @Override
                public void onResponse(Call<List<NewsModel>> call, Response<List<NewsModel>> response) {

                    if (!response.isSuccessful())
                    {
                        MainActivity.alertDialogClass.alertDialog("Please try later");
                        MainActivity.animationHide();
                    }

                    arrayListTopStories = (ArrayList<NewsModel>) response.body();

                    thumbnailTopStoryStr = arrayListTopStories.get(0).getFeaturedMedia().get(0);
                    thumbnailTopStoryTitleStr = arrayListTopStories.get(0).getTitle();
                    newsUrl = arrayListTopStories.get(0).getGuid();

                    Bundle bundle = new Bundle();
                    bundle.putString("newsUrl",newsUrl);
                    bundle.putString("newsThumbnail",thumbnailTopStoryStr);
                    postWebpageFragment.setArguments(bundle);


                    Picasso.with(getActivity()).load(thumbnailTopStoryStr).placeholder(R.drawable.loading).error(R.drawable.loading).into(thumbnailTopStoryImv);
                    thumbnailTopStoryTitleTv.setText(thumbnailTopStoryTitleStr);


                    for (int i = 1 ; i <= 5 ; i++)
                    {
                        arrayListTopStoriesHr.add(arrayListTopStories.get(i));
                    }

                    for (int i = 6 ; i <= 10 ; i++)
                    {
                        arrayListTopStoriesVr.add(arrayListTopStories.get(i));
                    }

//                getFeaturedNews(pageNumber);

                    showDataInView();

                }

                @Override
                public void onFailure(Call<List<NewsModel>> call, Throwable t) {

                    MainActivity.alertDialogClass.alertDialog("Please try later" + t.getMessage());
                    Log.d("failure", "onFailure: " + t.getMessage());
                    MainActivity.animationHide();

                }
            });
        }
        catch (Exception e) {

            MainActivity.alertDialogClass.alertDialog("Please try later");
        }



    }

    private void getFeaturedNews(int pageNumber) {

        categortIDAndPageNumber = " 2 | " +  String.valueOf(pageNumber);
        interfaceApi = RetrofitLibrary.connect(url);
        callForNews = interfaceApi.getAllCategoriesNews(categortIDAndPageNumber);
        callForNews.enqueue(new Callback<List<NewsModel>>() {
            @Override
            public void onResponse(Call<List<NewsModel>> call, Response<List<NewsModel>> response) {

                if (!response.isSuccessful())
                {
                    Toast.makeText(getActivity(), "Please try later", Toast.LENGTH_SHORT).show();
                }

                arrayListFeaturedNewsHr = (ArrayList<NewsModel>) response.body();

                if (arrayListFeaturedNewsHr.size() == 0)
                {
                    layoutFeaturedTitle.setVisibility(View.GONE);
                }
                else {

                    if (pageNumber == 1)
                    {
                        showFeaturedNews();
                    }

                    newsItemAdapterHrFeatured.notifyDataSetChanged();
                    newsItemAdapterHrFeatured.notifyItemRangeInserted(newsItemAdapterHrFeatured.getItemCount() , arrayListFeaturedNewsHr.size());

                    loadMore();
                }

            }

            @Override
            public void onFailure(Call<List<NewsModel>> call, Throwable t) {

            }
        });



    }

    private void showFeaturedNews() {

        newsItemAdapterHrFeatured = new NewsItemAdapter(getActivity(),"rvHorizontally",arrayListFeaturedNewsHr);
        rvNewsCategoryHorizontally.setAdapter(newsItemAdapterHrFeatured);
    }

    private void loadMore() {

        rvNewsCategoryHorizontally.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    isScrooling = true;
                }
            }

            @Override   //method called when scrolling end
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItem = linearLayoutManager.getChildCount();
                totalItems = linearLayoutManager.getItemCount();
                scrollOutItems = linearLayoutManager.findFirstVisibleItemPosition();

                if (isScrooling && (currentItem + scrollOutItems == totalItems))
                {
                    isScrooling = false;
                    fetchData();
                }
            }
        });
    }

    private void fetchData() {

        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pageNumber++;
                getFeaturedNews(pageNumber);

                progressBar.setVisibility(View.GONE);

            }
        },5000);
    }

    private void setOrientationFeaturedHorizontallRv() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvNewsCategoryHorizontally.setLayoutManager(linearLayoutManager);
    }

    private void setOrientationFeaturedVerticallyRv() {
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
