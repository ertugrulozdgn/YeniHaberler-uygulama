package com.ertugrulozdogan.yenihaberler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ertugrulozdogan.yenihaberler.api.ApiClient;
import com.ertugrulozdogan.yenihaberler.api.ApiInterface;
import com.ertugrulozdogan.yenihaberler.models.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static Post globalResponse ;

    public static final  String API_KEY = "API İÇİN KEY";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Post> posts = new ArrayList<>();
    private Adapter adapter;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        LoadJson();
    }

    public void LoadJson(){

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        String country = Utils.getCountry();

        Call<Post> call;
        call = apiInterface.getNews(country,API_KEY);   //BURADA NEDEN GETNEWS() ANLAMADIM.NEDEN GETPOST() DEĞİL

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful() && response.body().getId() != null){     //buradada getArticle() yazıyordu.

                    globalResponse = response.body();

                }else{
                    Toast.makeText(MainActivity.this, "Sonuç Yok", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }
}
