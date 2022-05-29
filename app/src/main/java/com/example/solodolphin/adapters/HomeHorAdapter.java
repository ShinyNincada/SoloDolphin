package com.example.solodolphin.adapters;

import static com.example.solodolphin.activites.AddProduct.myDB;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.solodolphin.R;
import com.example.solodolphin.models.HomeHorModel;
import com.example.solodolphin.models.HomeVerModel;
import com.example.solodolphin.sqlite.MyProduct;

import java.util.ArrayList;

public class HomeHorAdapter extends RecyclerView.Adapter<HomeHorAdapter.ViewHolder> {

    UpdateVerticalRec updateVerticalRec;
    Activity activity;
    ArrayList<HomeHorModel> list;

    boolean check = true;
    boolean select = true;
    int row_index = -1;

    public HomeHorAdapter(UpdateVerticalRec updateVerticalRec, Activity activity, ArrayList<HomeHorModel> list) {
        this.updateVerticalRec = updateVerticalRec;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_horizontal_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.imageView.setImageResource((list.get(position).getImage()));
        holder.name.setText(list.get(position).getName());
        myDB = new MyProduct(this.activity, "shinyNincada", null , 1);
        if (check) {
            ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
            homeVerModels = myDB.SelectCategoryByType("Pizza");
            Toast.makeText(this.activity, ""+homeVerModels.size(), Toast.LENGTH_SHORT).show();
            updateVerticalRec.callBack(position, homeVerModels);
            check = false;
        }
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    row_index = position;
                    notifyDataSetChanged();

                    if (position == 0){
                        ArrayList<HomeVerModel> homeVerModels;
                        homeVerModels = myDB.SelectCategoryByType("Pizza");
                        updateVerticalRec.callBack(position,homeVerModels);
                    }
                    else if (position == 1){
                        ArrayList<HomeVerModel> homeVerModels = myDB.SelectCategoryByType("Burger");
                        updateVerticalRec.callBack(position,homeVerModels);
                    }

                    else if (position == 2){
                        ArrayList<HomeVerModel> homeVerModels = myDB.SelectCategoryByType("Snack");
                        updateVerticalRec.callBack(position,homeVerModels);
                    }

                    else if (position == 3){
                        ArrayList<HomeVerModel> homeVerModels = myDB.SelectCategoryByType("Cream");
                        updateVerticalRec.callBack(position,homeVerModels);
                    }

                    else if (position == 4){
                        ArrayList<HomeVerModel> homeVerModels = myDB.SelectCategoryByType("Sandwich");
                        updateVerticalRec.callBack(position,homeVerModels);
                    }
                }
            });

            if (select){
                if (position == 0){
                    holder.cardView.setBackgroundResource(R.drawable.change_bg);
                    select = false;
                }
            }
            else {
                if (row_index == position){
                    holder.cardView.setBackgroundResource(R.drawable.change_bg);
                }
                else {
                    holder.cardView.setBackgroundResource(R.drawable.default_bg);
                }
            }
        }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.hor_img);
            name = itemView.findViewById(R.id.hor_text);
            cardView = itemView.findViewById(R.id.cartView);


        }
    }
}
