package com.example.solodolphin.adapters;

import static com.example.solodolphin.ui.MyCartFragment.CartList;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.solodolphin.R;
import com.example.solodolphin.models.CartModel;
import com.example.solodolphin.ui.MyCartFragment;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    List<CartModel> list;
    int vitri = -1;
    TextView cart_pr;
    Context myContext;
    public CartAdapter(List<CartModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        vitri = list.get(position).getId();
        holder.name.setText(list.get(position).getName());
        holder.cate.setText(list.get(position).getCate());
        holder.price.setText(""+list.get(position).getPrice());
        holder.quantity.setText(""+list.get(position).getQuantity());

        byte[] hinhanh = list.get(position).getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0,hinhanh.length);
        holder.imageView.setImageBitmap(bitmap);

        holder.removebtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                CartList.removeIf(cartModel -> cartModel.getId() == vitri);
                notifyDataSetChanged();

            }
        });

    }



    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,price,cate, quantity;
        Button removebtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.mycart_img);
            name = itemView.findViewById(R.id.mycart_name);
            price = itemView.findViewById(R.id.mycart_price);
            cate = itemView.findViewById(R.id.mycart_rating);
            removebtn = itemView.findViewById(R.id.cartRemoveBtn);
            quantity = itemView.findViewById(R.id.mycart_quantity);


        }
    }

}
