package com.webscare.livenewsnow.Interface;

import com.webscare.livenewsnow.ModelsClasses.NewsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceApi {
    @GET("posts")
    Call<List<NewsModel>> getTopStories();
}
