package com.example.solodolphin.adapters;

import static com.example.solodolphin.activites.AddProduct.vitri;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.solodolphin.R;
import com.example.solodolphin.activites.AddProduct;
import com.example.solodolphin.activites.EditActivity;
import com.example.solodolphin.sqlite.Food;

import java.io.Serializable;
import java.util.ArrayList;

public class FoodAdapter extends BaseAdapter implements Filterable {
    Activity activity;
    ArrayList<Food> data;
    LayoutInflater inflater;
    ArrayList<Food> dataBackup;
    public FoodAdapter(Activity activity, ArrayList<Food> data) {
        this.activity = activity;
        this.data = data;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return data.get(i).getId();
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if(v==null) {
//            phân tích layout
            v = inflater.inflate(R.layout.food_items, null);
        }
        TextView txtName = v.findViewById(R.id.foodName);
        TextView txtRate = v.findViewById(R.id.foodRate);
        TextView Price = v.findViewById(R.id.foodPrice);
        ImageView img = v.findViewById(R.id.foodImage);

        int id = data.get(i).getId();
        txtName.setText(data.get(i).getName());
        txtRate.setText(data.get(i).getCate());
        Price.setText(""+data.get(i).getPrice());
        //chuyển byte[] về bitmap
        byte[] hinhanh = data.get(i).getImgID();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0,hinhanh.length);
        img.setImageBitmap(bitmap);


        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, ""+  id  , Toast.LENGTH_SHORT).show();
                vitri = id;
            }
        });

        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(activity, EditActivity.class);
                Food tmpFood = data.get(i);
                intent.putExtra("myFood", (Serializable) tmpFood);
                activity.startActivity(intent);
                return false;
            }
        });
        return v;
    }

    @Override
    public Filter getFilter() {
        Filter f = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults fr = new FilterResults();
                if(dataBackup==null)
                    dataBackup = new ArrayList<>(data);
                if(charSequence==null || charSequence.length()==0){
                    fr.count = dataBackup.size();
                    fr.values = dataBackup;
                }
                else{
                    ArrayList<Food> newdata = new ArrayList<>();
                    for(Food c: dataBackup)
                        if(c.getName().toLowerCase().contains(charSequence.toString().toLowerCase()))
                            newdata.add(c);
                    fr.count = newdata.size();
                    fr.values = newdata;
                }
                return fr;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                data = new ArrayList<>();
                ArrayList<Food> tmp = (ArrayList<Food>) filterResults.values;
                for(Food c:tmp){
                    Food nu = new Food(c.getId(), c.getName(), c.getCate(), c.getPrice(), c.getDes(), c.getDis(), c.getImgID());
                    data.add(nu);
                }
                notifyDataSetChanged();
            }
        };
        return f;
            }
        }
