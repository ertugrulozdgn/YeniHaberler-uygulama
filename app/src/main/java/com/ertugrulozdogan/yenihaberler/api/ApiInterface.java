package com.ertugrulozdogan.yenihaberler.api;

import com.ertugrulozdogan.yenihaberler.models.Post;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("post")
    Call<Post> getNews(

      @Query("country") String country ,
      @Query("apiKey") String apiKey

    );

}
