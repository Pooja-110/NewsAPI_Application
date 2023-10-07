package com.example.newsapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    String BASE_URL = "https://newsapi.org/v2/";

    @GET("top-headlines")
    Call<MainModel> getNews(@Query("country") String country,
                            @Query("pageSize") int pageSize,
                            @Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<MainModel> getCategoryNews(@Query("country") String country,
                                    @Query("category") String category,
                                    @Query("apiKey") String apiKey);

    @GET("everything") // Define a new endpoint for search
    Call<MainModel> searchNews(@Query("q") String query, // Add a query parameter for the search query
                               @Query("apiKey") String apiKey);
}
