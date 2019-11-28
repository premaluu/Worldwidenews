package com.example.amitvikram.worldwidenews.api;

import com.example.amitvikram.worldwidenews.models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("top-headlines")
    Call<News> getNews(
            @Query("country") String country,
            @Query("apiKey") String apikey,
            @Query("category") String category,
            @Query("sources") String sources
    );

    @GET("everything")
    Call<News> getNewsSearch(
            @Query("q") String keyword,
            @Query("language") String language,
            @Query("sortBy") String sortBy,
            @Query("apiKey") String apiKey
    );

    @GET("top-headlines")
    Call<News> getSourceNews(
            @Query("apiKey") String apikey,
            @Query("sources") String sources
    );

}
