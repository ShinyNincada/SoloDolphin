package com.example.solodolphin.ui.dailymeal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.solodolphin.R;
import com.example.solodolphin.adapters.DailyMealAdapter;
import com.example.solodolphin.models.DailyMealModel;

import java.util.ArrayList;
import java.util.List;

public class DailyMealFragment extends Fragment {

    RecyclerView recyclerView;
    List<DailyMealModel> dailyMealModels;
    DailyMealAdapter dailyMealAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.daily_meal_fragment,container, false);

        recyclerView = root.findViewById(R.id.daily_meal_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dailyMealModels = new ArrayList<>();

        dailyMealModels.add(new DailyMealModel(R.drawable.breakfast,"Breakfast","30% OFF","Description","breakfast"));
        dailyMealModels.add(new DailyMealModel(R.drawable.lunch,"Lunch","20% OFF","Description","lunch"));
        dailyMealModels.add(new DailyMealModel(R.drawable.dinner,"Dinner","40% OFF","Description","dinner"));
        dailyMealModels.add(new DailyMealModel(R.drawable.sweets,"Sweets","50% OFF","Description","sweets"));
        dailyMealModels.add(new DailyMealModel(R.drawable.coffe,"Coffee","15% OFF","Description","coffee"));


        dailyMealAdapter = new DailyMealAdapter(getContext(),dailyMealModels);
        recyclerView.setAdapter(dailyMealAdapter);
        dailyMealAdapter.notifyDataSetChanged();


        return root;
    }

}