package com.ertugrulozdogan.yenihaberler;


import android.content.Context;
import android.content.DialogInterface;
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


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Post> posts;
    private Context context;
    private OnItemClickListener onItemClickListener;



    public Adapter(List<Post> posts, Context context) {
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
        Post model = posts.get(position);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();

        holder.title.setText(model.getTitle());
        holder.summary.setText(model.getSummary() );
        holder.created_at.setText(Utils.DateFormat(model.getCreated_at()));
    }

    @Override
    public int getItemCount() {
        return posts.size();
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

            onItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }
}
