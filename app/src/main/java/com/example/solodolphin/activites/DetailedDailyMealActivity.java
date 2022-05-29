package com.example.solodolphin.activites;

import static com.example.solodolphin.activites.AddProduct.myDB;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.solodolphin.R;
import com.example.solodolphin.adapters.NewDetailDailyAdapter;
import com.example.solodolphin.models.DetailedDailyModel;
import com.example.solodolphin.sqlite.MyProduct;

import java.util.ArrayList;

public class DetailedDailyMealActivity extends AppCompatActivity {


    ArrayList<DetailedDailyModel> detailedDailyModelList;
    ImageView imageView,imageView2;
    NewDetailDailyAdapter newDetailDailyAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_daily_meal);


        String type = getIntent().getStringExtra("type");
        myDB = new MyProduct(this, "shinyNincada", null , 1);
        imageView = findViewById(R.id.detailed_img);
        imageView2 = findViewById(R.id.imageView);
        detailedDailyModelList = new ArrayList<>();
        listView = findViewById(R.id.detailed_rec2);

        if(type != null && type.equalsIgnoreCase("Breakfast")){
            imageView2.setImageResource(R.drawable.breakfast);
            detailedDailyModelList = myDB.SelectCategoryForDaily("Breakfast");
            newDetailDailyAdapter = new NewDetailDailyAdapter(DetailedDailyMealActivity.this,detailedDailyModelList);
            listView.setAdapter(newDetailDailyAdapter);
            newDetailDailyAdapter.notifyDataSetChanged();
            Toast.makeText(this, ""+detailedDailyModelList.size(), Toast.LENGTH_SHORT).show();
        }

        else if(type != null && type.equalsIgnoreCase("Sweets")){

            imageView2.setImageResource(R.drawable.sweets);
            detailedDailyModelList = myDB.SelectCategoryForDaily("Sweets");
            newDetailDailyAdapter = new NewDetailDailyAdapter(DetailedDailyMealActivity.this,detailedDailyModelList);
            listView.setAdapter(newDetailDailyAdapter);
            newDetailDailyAdapter.notifyDataSetChanged();
            Toast.makeText(this, ""+detailedDailyModelList.size(), Toast.LENGTH_SHORT).show();
        }

        else if(type != null && type.equalsIgnoreCase("Lunch")){
            imageView2.setImageResource(R.drawable.lunch);
            detailedDailyModelList = myDB.SelectCategoryForDaily("Lunch");
            newDetailDailyAdapter = new NewDetailDailyAdapter(DetailedDailyMealActivity.this,detailedDailyModelList);
            listView.setAdapter(newDetailDailyAdapter);
            newDetailDailyAdapter.notifyDataSetChanged();
            Toast.makeText(this, ""+detailedDailyModelList.size(), Toast.LENGTH_SHORT).show();
        }

        else if(type != null && type.equalsIgnoreCase("Dinner")){
            detailedDailyModelList = myDB.SelectCategoryForDaily("Dinner");
            newDetailDailyAdapter = new NewDetailDailyAdapter(DetailedDailyMealActivity.this,detailedDailyModelList);
            listView.setAdapter(newDetailDailyAdapter);
            newDetailDailyAdapter.notifyDataSetChanged();
            Toast.makeText(this, ""+detailedDailyModelList.size(), Toast.LENGTH_SHORT).show();

        }

        else if(type != null && type.equalsIgnoreCase("Coffee")){
            detailedDailyModelList = myDB.SelectCategoryForDaily("Coffee");
            newDetailDailyAdapter = new NewDetailDailyAdapter(DetailedDailyMealActivity.this,detailedDailyModelList);
            listView.setAdapter(newDetailDailyAdapter);
            newDetailDailyAdapter.notifyDataSetChanged();
            Toast.makeText(this, ""+detailedDailyModelList.size(), Toast.LENGTH_SHORT).show();

        }

    }
}