package com.example.solodolphin.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.solodolphin.R;
import com.example.solodolphin.adapters.FoodAdapter;
import com.example.solodolphin.sqlite.Food;
import com.example.solodolphin.sqlite.MyProduct;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class AddProduct extends AppCompatActivity {

    ArrayList<Food> foods = new ArrayList<>();
    ListView foodView;
    FoodAdapter foodAdapter;
    Button btnThem, btnXoa , btnQuayLai;
    EditText searchText;
    public static MyProduct myDB;
    public static byte[] myDefault;
    public static int vitri = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        foods = new ArrayList<>();
        anhxa();

        myDB = new MyProduct(this, "shinyNincada", null , 1);
        foods = myDB.getAllFood();

        foodAdapter = new FoodAdapter(AddProduct.this, foods);
        foodView.setAdapter(foodAdapter);

//        foodView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(AddProduct.this, ""+foods.get(i).getId(), Toast.LENGTH_SHORT).show();
//                vitri = foods.get(i).getId();
//            }
//        });


//        foodView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(AddProduct.this, EditActivity.class);
//                Food tmpFood = foods.get(i);
//                intent.putExtra("myFood", (Serializable) tmpFood);
//                startActivity(intent);
//                return false;
//            }
//        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                myDB.addFood();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.panda);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bitMapdata =stream.toByteArray();
                myDefault =stream.toByteArray();
                myDB.InsertValue("New Product", "Breakfast", 100, bitMapdata);
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                foodAdapter.notifyDataSetChanged();


            }
        });

        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddProduct.this, MainActivity.class));
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vitri== -1){
                    Toast.makeText(AddProduct.this,"Please choose item to delete", Toast.LENGTH_SHORT).show();
                }
                else{
                    AlertDialog.Builder alert = new AlertDialog.Builder(AddProduct.this);
                    alert.setTitle("Delete");
                    alert.setMessage("Are you sure?");
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if(myDB.checkFoodInDetail(vitri)){
                                Toast.makeText(AddProduct.this, "This product has existed in other table!!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                myDB.deleteFood(vitri);
                                foods.removeIf(food -> food.getId() == vitri);
                                vitri = -1;
                                foodAdapter.notifyDataSetChanged();
                                Toast.makeText(AddProduct.this, "Deleted", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }
                    });

                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alert.show();

            }}
        });

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                foodAdapter.getFilter().filter(charSequence.toString());
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void anhxa() {
        foodView = findViewById(R.id.listFood);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        searchText = findViewById(R.id.searchName);
        btnQuayLai = findViewById(R.id.btnQuayLai);
    }



}