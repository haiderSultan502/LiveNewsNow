package com.webscare.livenewsnow.Interface;

import com.webscare.livenewsnow.ModelsClasses.NewsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceApi {

    @GET("posts")
    Call<List<NewsModel>> getTopStories();

    @GET("posts")
    Call<List<NewsModel>> getAllCategoriesNews(@Query("cat") String categoryIdAndPageNumber);

    @GET("search")
    Call<List<NewsModel>> getSearchNews(@Query("s") String searchKeyword);
}
