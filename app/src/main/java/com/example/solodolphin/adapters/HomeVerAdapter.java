package com.example.solodolphin.adapters;

import static com.example.solodolphin.ui.MyCartFragment.CartList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.solodolphin.R;
import com.example.solodolphin.models.CartModel;
import com.example.solodolphin.models.HomeVerModel;
import com.example.solodolphin.sqlite.Food;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class HomeVerAdapter extends RecyclerView.Adapter<HomeVerAdapter.ViewHolder> implements Filterable {

    private BottomSheetDialog bottomSheetDialog;
    Context context;
    ArrayList<HomeVerModel> list;
    ArrayList<HomeVerModel> dataBackup;

    public HomeVerAdapter(Context context, ArrayList<HomeVerModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_vertical_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String mName = list.get(position).getName();
        String mRating = list.get(position).getCate();
        String mPrice = ""+list.get(position).getPrice();
        int mPrice2 = list.get(position).getPrice();
        byte[] aImg = list.get(position).getImage();
        int aId = list.get(position).getId();

        holder.name.setText(list.get(position).getName());
        holder.rating.setText(list.get(position).getCate());
        holder.price.setText(""+list.get(position).getPrice());

        byte[] hinhanh = list.get(position).getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0,hinhanh.length);
        holder.imageView.setImageBitmap(bitmap);


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog = new BottomSheetDialog(context,R.style.BottomSheetTheme);

                View sheetView = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_layout,null);


                sheetView.findViewById(R.id.add_to_cart).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        CartModel cartModel = new CartModel(aId, aImg, mName, mRating, mPrice2, 1);
                        if(!ExistInCart(aId))
                        {
                            CartList.add(cartModel);
                        }
                        Toast.makeText(context,"Added to a Cart",Toast.LENGTH_LONG).show();
                        bottomSheetDialog.dismiss();
                    }
                });

                ImageView bottomImg = sheetView.findViewById(R.id.bottom_img);
                TextView bottomName = sheetView.findViewById(R.id.bottom_name);
                TextView bottomPrice = sheetView.findViewById(R.id.bottom_price);
                TextView bottomRating = sheetView.findViewById(R.id.bottom_rating);


                bottomImg.setImageBitmap(bitmap);
                bottomName.setText(mName);
                bottomPrice.setText(mPrice);
                bottomRating.setText(mRating);


                bottomSheetDialog.setContentView(sheetView);
                bottomSheetDialog.show();

            }
        });
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


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        Filter f = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults fr = new FilterResults();
                if(dataBackup==null)
                    dataBackup = new ArrayList<>(list);
                if(charSequence==null || charSequence.length()==0){
                    fr.count = dataBackup.size();
                    fr.values = dataBackup;
                }
                else{
                    ArrayList<HomeVerModel> newdata = new ArrayList<>();
                    for(HomeVerModel c: dataBackup)
                        if(c.getName().toLowerCase().contains(charSequence.toString().toLowerCase()))
                            newdata.add(c);
                    fr.count = newdata.size();
                    fr.values = newdata;
                }
                return fr;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                list = new ArrayList<>();
                ArrayList<HomeVerModel> tmp = (ArrayList<HomeVerModel>) filterResults.values;
                for(HomeVerModel c:tmp){
                    HomeVerModel nu = new HomeVerModel( c.getId(), c.getImage(), c.getName(), c.getCate(), c.getDes(), c.getPrice());
                    list.add(nu);
                }
                notifyDataSetChanged();
            }
        };
        return f;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name,rating,price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ver_img);
            name = itemView.findViewById(R.id.name);
            rating = itemView.findViewById(R.id.rating);
            price = itemView.findViewById(R.id.price);
        }
    }


}
