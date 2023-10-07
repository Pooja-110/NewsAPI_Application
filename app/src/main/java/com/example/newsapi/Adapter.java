package com.example.newsapi;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context context;
    private List<ModelClass> originalData;
    private List<ModelClass> filteredData;

    public Adapter(Context context, List<ModelClass> arrayList) {
        this.context = context;
        this.originalData = arrayList;
        this.filteredData = new ArrayList<>(arrayList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.headlines_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, webView.class);
                intent.putExtra("url", filteredData.get(position).getUrl());
                context.startActivity(intent);
            }
        });

        holder.mtime.setText("Published At:-" + filteredData.get(position).getPublishedAt());
        holder.mauthor.setText(filteredData.get(position).getAuthor());
        holder.mheading.setText(filteredData.get(position).getTitle());
        holder.mcontent.setText(filteredData.get(position).getDescription());
        Glide.with(context).load(filteredData.get(position).getUrlToImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return filteredData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mheading, mcontent, mauthor, mtime;
        CardView cardView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mheading = itemView.findViewById(R.id.headline);
            mcontent = itemView.findViewById(R.id.desc);
            mauthor = itemView.findViewById(R.id.author);
            mtime = itemView.findViewById(R.id.dt);
            cardView = itemView.findViewById(R.id.cardview);
            imageView = itemView.findViewById(R.id.img);
        }
    }

    public void setOriginalData(List<ModelClass> data) {
        originalData = data;
        filteredData = new ArrayList<>(originalData);
    }

    public void filter(String query) {
        filteredData.clear();
        if (TextUtils.isEmpty(query)) {
            filteredData.addAll(originalData);
        } else {
            for (ModelClass item : originalData) {
                if (item.getTitle().toLowerCase().contains(query.toLowerCase())) {
                    filteredData.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}
