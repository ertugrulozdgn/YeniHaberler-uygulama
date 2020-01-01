package com.ertugrulozdogan.yenihaberler;

import android.support.v4.widget.SwipeRefreshLayout;
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

public class MainActivity extends AppCompatActivity{

    public static List<Post> globalResponse ;   //projenin heryerinden çekebilecğeim global sonuç değerim.static olmasının sebebi heryerden çekebiliyor olmam.

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Post> posts = new ArrayList<>();
    public Adapter adapter;
    private String TAG = MainActivity.class.getSimpleName();
    public Adapter productAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);


        LoadJson();
        productAdapter = new Adapter(globalResponse,this);          //interneten bulduğum adapter.java yı bağlama kodları.


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void LoadJson(){


        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Post>> call;
        call = apiInterface.getNews();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                globalResponse=response.body();

                recyclerView.setAdapter(productAdapter);  //onResponese nin içinde önce sonucu aldım,globaresponse ye attım.Artık bir sonuç olduğu için recyclerView ın adapterini bağlayabildik.

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                System.out.println("Hata verdi" + t.getMessage());
            }

        });
    }

}