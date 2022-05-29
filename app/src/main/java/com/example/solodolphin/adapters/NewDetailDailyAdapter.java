package com.example.solodolphin.adapters;

import static com.example.solodolphin.ui.MyCartFragment.CartList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.solodolphin.R;
import com.example.solodolphin.models.CartModel;
import com.example.solodolphin.models.DetailedDailyModel;

import java.util.ArrayList;

public class NewDetailDailyAdapter extends BaseAdapter {

    Activity activity;
    ArrayList<DetailedDailyModel> data;
    LayoutInflater inflater;

    public NewDetailDailyAdapter(Activity activity, ArrayList<DetailedDailyModel> data) {
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
        return data.get(i).getPrice();
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if(v==null) {
//            phân tích layout
            v = inflater.inflate(R.layout.detailed_daily_meal_item, null);
        }

        ImageView imageView = v.findViewById(R.id.detailed_img);
        TextView name = v.findViewById(R.id.detailed_name);
        TextView description = v.findViewById(R.id.detailed_description);
        TextView price = v.findViewById(R.id.detailed_price);
        TextView rating = v.findViewById(R.id.detailed_rating);
        Button btnadd = v.findViewById(R.id.addCartbutt1);

        byte[] hinhanh = data.get(i).getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0,hinhanh.length);
        imageView.setImageBitmap(bitmap);

        imageView.setImageBitmap(bitmap);
        name.setText(data.get(i).getName());
        description.setText(data.get(i).getDescription());
        rating.setText(data.get(i).getRating());
        price.setText(""+data.get(i).getPrice());

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int aId = data.get(i).getId();
                byte[] aImg = data.get(i).getImage();
                String aName = data.get(i).getName();
                String aCate = data.get(i).getRating();
                int aPrice = data.get(i).getPrice();
                CartModel cartModel = new CartModel(aId, aImg, aName, aCate, aPrice, 1);
                if(!ExistInCart(aId))
                {
                    CartList.add(cartModel);
                }
                Toast.makeText(activity,"Added to a Cart",Toast.LENGTH_LONG).show();


            }
        });
        return v;
    }

    private boolean ExistInCart(int aId) {
        int flagCheck = 0;
        for (int i = 0; i<CartList.size();i++){
            if(CartList.get(i).getId() == aId)
            {
                int tempQuanti = CartList.get(i).getQuantity();
                tempQuanti++;
                CartList.get(i).setQuantity(tempQuanti);
                flagCheck++;
            }
        }
        if(flagCheck >0) return true;
        else return false;
    }
}
