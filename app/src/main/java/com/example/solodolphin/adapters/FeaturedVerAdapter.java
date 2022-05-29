package com.example.solodolphin.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.solodolphin.R;
import com.example.solodolphin.models.FeaturedVerModel;

import java.util.List;

public class FeaturedVerAdapter extends RecyclerView.Adapter<FeaturedVerAdapter.ViewHolder> {

    List<FeaturedVerModel> list;


    public FeaturedVerAdapter(List<FeaturedVerModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_ver_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.image.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.desc.setText(list.get(position).getDesc());
        holder.rating.setText(list.get(position).getRating());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name,desc,rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.featured_ver_img);
            name = itemView.findViewById(R.id.featured_ver_name);
            desc = itemView.findViewById(R.id.featured_ver_desc);
            rating = itemView.findViewById(R.id.featured_ver_rating);



        }
    }
}