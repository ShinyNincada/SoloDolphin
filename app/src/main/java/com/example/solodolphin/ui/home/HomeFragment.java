package com.example.solodolphin.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.solodolphin.R;
import com.example.solodolphin.adapters.HomeHorAdapter;
import com.example.solodolphin.adapters.HomeVerAdapter;
import com.example.solodolphin.adapters.UpdateVerticalRec;
import com.example.solodolphin.models.HomeHorModel;
import com.example.solodolphin.models.HomeVerModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements UpdateVerticalRec {

    RecyclerView homeHorizontalRec,homeVerticalRec;
    ArrayList<HomeHorModel> homeHorModelList;
    HomeHorAdapter homeHorAdapter;

    // Vertical
    ArrayList<HomeVerModel> homeVerModelList;
    public static HomeVerAdapter homeVerAdapter;

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_home,container, false);

            homeHorizontalRec = root.findViewById(R.id.home_hor_rec);
            homeVerticalRec = root.findViewById(R.id.home_ver_rec);


            // Horizontal RecyclerView

            homeHorModelList = new ArrayList<>();

            homeHorModelList.add(new HomeHorModel(R.drawable.pizza,"Pizza"));
            homeHorModelList.add(new HomeHorModel(R.drawable.hamburger,"Hamburger"));
            homeHorModelList.add(new HomeHorModel(R.drawable.fried_potatoes,"Fries"));
            homeHorModelList.add(new HomeHorModel(R.drawable.ice_cream,"Ice Cream"));
            homeHorModelList.add(new HomeHorModel(R.drawable.sandwich,"Sandwich"));

            homeHorAdapter = new HomeHorAdapter(this,getActivity(),homeHorModelList);
            homeHorizontalRec.setAdapter(homeHorAdapter);

            homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
            homeHorizontalRec.setHasFixedSize(true);
            homeHorizontalRec.setNestedScrollingEnabled(false);


            // Vertical RecyclerView
            homeVerModelList = new ArrayList<>();

            homeVerAdapter = new HomeVerAdapter(getActivity(),homeVerModelList);
            homeVerticalRec.setAdapter(homeVerAdapter);

            homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));

            return root;
        }

    @Override
    public void callBack(int position, ArrayList<HomeVerModel> list) {
     homeVerAdapter = new HomeVerAdapter(getContext(),list);
     homeVerAdapter.notifyDataSetChanged();
     homeVerticalRec.setAdapter(homeVerAdapter);
    }
}