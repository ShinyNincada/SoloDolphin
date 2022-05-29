package com.example.solodolphin.ui;

import static com.example.solodolphin.activites.AddProduct.myDB;
import static com.example.solodolphin.activites.LoginActivity.MyUser;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.solodolphin.R;
import com.example.solodolphin.adapters.CartAdapter;
import com.example.solodolphin.models.CartModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment {

    public static List<CartModel> CartList = new ArrayList<>();
    CartAdapter cartAdapter;
    RecyclerView recyclerView;
    TextView priceCart;
    Button removebtn, makeOrder;
    int myPrice = 0;
    public MyCartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart,container,false);

        priceCart = view.findViewById(R.id.cart_price);
        recyclerView = view.findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        removebtn = view.findViewById(R.id.cartRemoveBtn);
        makeOrder = view.findViewById(R.id.OrderBTN);
        cartAdapter = new CartAdapter(CartList);
        recyclerView.setAdapter(cartAdapter);


        cartAdapter.notifyDataSetChanged();


        for (int i = 0; i < CartList.size(); i++) {
            myPrice+=(CartList.get(i).getPrice() * CartList.get(i).getQuantity());
        }
        priceCart.setText(""+myPrice);

        makeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
                bottomSheetDialog.setContentView(R.layout.cart_picker);
                bottomSheetDialog.setCanceledOnTouchOutside(true);
                BottomSheetBehavior behavior = bottomSheetDialog.getBehavior();
                behavior.setPeekHeight(650);

                TextView orderLocation = bottomSheetDialog.findViewById(R.id.OrderLocation);
                TextView orderTotalCost = bottomSheetDialog.findViewById(R.id.order_lastCost);

                orderLocation.setText(MyUser.getUserAddress());
                orderTotalCost.setText(""+costCalculate(CartList)+"$");


                Button finalOrder = bottomSheetDialog.findViewById(R.id.finalOrderBtn);
                finalOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String lction = orderLocation.getText().toString().trim();
                        int finalCost =  costCalculate(CartList);
                        if(CartList.size() == 0)
                        {
                            Toast.makeText(getActivity(),"Your cart is empty!", Toast.LENGTH_SHORT).show();
                        }
                        else if(!myDB.addressIsNotNull(MyUser.getUserMail())){
                            Toast.makeText(getActivity(),"Your address is empty!!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            myDB.MakeOrder(CartList, MyUser);
                            Toast.makeText(getActivity(), "Your request was successfully submitted", Toast.LENGTH_SHORT).show();
                            CartList.clear();
                            cartAdapter.notifyDataSetChanged();
                        }
                    }
                });
                bottomSheetDialog.show();
            }
        });


        return view;
    }

    private int costCalculate(List<CartModel> cartList) {
        int total = 0;
        for (int i = 0; i < cartList.size(); i++) {
            total+=(cartList.get(i).getPrice() * cartList.get(i).getQuantity());
        }
        return total;
    }
}