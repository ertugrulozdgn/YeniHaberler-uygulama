package com.ertugrulozdogan.yenihaberler;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;



import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.ertugrulozdogan.yenihaberler.models.Post;

import java.util.List;

import static com.ertugrulozdogan.yenihaberler.MainActivity.globalResponse;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    public static Integer getItemIndex;

    private List<Post> posts;
    private Context context;
    private OnItemClickListener onItemClickListener;       //Her tıkı kontrol ediyor.Bir iteme tıkladğımımda calısan bir fonk



    public Adapter(List<Post> posts, Context context) {     //global response sonuc değeri.this=main.activity.adapterin parametreleri.
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, int position) {
        final  MyViewHolder holder = holders;
        
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();

        holder.title.setText(globalResponse.get(position).getTitle());      //index ine göre veriyi alıp title ını ve summary sini set ediyor.
        holder.summary.setText(globalResponse.get(position).getSummary() );
         holder.created_at.setText(globalResponse.get(position).getCreatedAt());
    }

    @Override
    public int getItemCount() {             //adapterin kaç tane datası(itemi) olduğunu döndüren fonksiyon.
        return globalResponse.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title, created_at, summary;
        OnItemClickListener onItemClickListener;

        public MyViewHolder(View itemView, OnItemClickListener onItemCLickListener) {

            super(itemView);

            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.title);
            created_at = itemView.findViewById(R.id.created_at);
            summary = itemView.findViewById(R.id.summary);
            this.onItemClickListener = onItemCLickListener;

        }

        @Override
        public void onClick(View v) {

            context.startActivity(new Intent(context, DetailActivity.class));  //activity ler arası geçiş için context kullandım.Tıklandığı zaman DetailActivity e gidecek.
            getItemIndex = getLayoutPosition();  // artık hangi iteme tıklandığını detailactivity de çekebilecek şekilde indexi ayarladım.yani hangi habere tıklandığını anlicak.o itemi tutuyor.
        }
    }
}