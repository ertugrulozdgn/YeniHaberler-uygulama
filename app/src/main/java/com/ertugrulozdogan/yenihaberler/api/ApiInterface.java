package com.ertugrulozdogan.yenihaberler.api;

import com.ertugrulozdogan.yenihaberler.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {     //retrofitin kurulumu için olması gerek ApiInterfaces

    @GET("api/post")
    Call<List<Post>> getNews();  //tek bir data olmadığı için list<post> yaptım sıralı haber geleceği için

}
