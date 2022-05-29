package com.example.solodolphin.activites;

import static com.example.solodolphin.activites.AddProduct.myDB;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.solodolphin.R;
import com.example.solodolphin.sqlite.Food;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;


public class    EditActivity extends AppCompatActivity {


    TextView myID;
    EditText myPrice, myDes, myDis, myName;
    Spinner myCate;
    ImageView myImg;
    Button saveMy, cancelMy;
    final int REQUEST_CODE_FOLDER = 456;

//thay thế cho startActivityOnresult  đã bị deprecate.-. chưa test
//    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//
//                }
//            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        anhxa();
        Intent intent = getIntent();
        Food myFoood = (Food) intent.getSerializableExtra("myFood");
        myID.setText(""+ myFoood.getId());
        myName.setText(myFoood.getName());
        myPrice.setText(""+myFoood.getPrice());
        myDes.setText(myFoood.getDes());
        myDis.setText(myFoood.getDis());
        byte[] hinhanh = myFoood.getImgID();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0,hinhanh.length);
        myImg.setImageBitmap(bitmap);
        int eID = Integer.parseInt((String) myID.getText());

        saveMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(valiadate(myName, myPrice, myCate, myDes, myDis)){
                    String tmpName = myName.getText().toString().trim();
                    String tmpCate = myCate.getSelectedItem().toString().trim();
                    int tmpPrice = Integer.parseInt(myPrice.getText().toString().trim());
                    String tmpDes = myDes.getText().toString().trim();
                    String tmpDis = myDis.getText().toString().trim();

                    //convert từ ảnh trong imageView về byte[] trong dữ liệu
                    Bitmap bitmap1 = ((BitmapDrawable)myImg.getDrawable()).getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap1.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] myBytes = stream.toByteArray();

                    Food temp = new Food(tmpName, tmpCate, tmpPrice, tmpDes, tmpDis, myBytes);
                    if(myDB.updateFood(eID, temp))
                    {
                        Toast.makeText(EditActivity.this, "Edit Successfully^^", Toast.LENGTH_SHORT).show();
                        Intent veAdd = new Intent(EditActivity.this, AddProduct.class);
                        startActivity(veAdd);
                    }

                }
            }
        });

        cancelMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(EditActivity.this, "Cya!", Toast.LENGTH_SHORT).show();
            }
        });



        myImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(EditActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_FOLDER);
//                Intent intent1 = new Intent(Intent.ACTION_PICK);
//                intent1.setType("image/*");
//                startActivityForResult(intent1, REQUEST_CODE_FOLDER);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case REQUEST_CODE_FOLDER:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Intent intent1 = new Intent(Intent.ACTION_PICK);
                    intent1.setType("image/*");
                    startActivityForResult(intent1, REQUEST_CODE_FOLDER);
                }
                else{
                    Toast.makeText(this, "Nevermind :<<", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        lấy ảnh từ camera -- chưa test
//        if(requestCode == REQUEST_CODE_CAM && resultCode == RESULT_OK && data != null){
//            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//            myImg.setImageBitmap(bitmap);
//        }
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
           Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                //chuyển từ byte[] -> bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                myImg.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private boolean valiadate(EditText myName, EditText myPrice, Spinner myCate, EditText myDes, EditText myDis) {
        String numberOnly = "[0-9]+";
//        String rateRegex = "^[0-5]?\\.[0-9]+$";
        if(myName.getText().toString().trim().equals("") || myPrice.getText().toString().trim().equals("") || myCate.getSelectedItem().toString().trim().equals(""))
        {
            myName.setError("Name, Category and Price cannot be null!!");
            return false;
        }
        else if(!myPrice.getText().toString().trim().matches(numberOnly)){
            myPrice.setError("Price can only be integer>0");
            return false;
        }
        else{
            myName.setError(null);
            myPrice.setError(null);
            return true;
        }

    }


    private void anhxa() {
        myID = (TextView) findViewById(R.id.myID);
        myCate = (Spinner) findViewById(R.id.myCate);
        myDes = (EditText) findViewById(R.id.myDes);
        myName = (EditText) findViewById(R.id.myName);
        myDis = (EditText) findViewById(R.id.myDis);
        myPrice = (EditText) findViewById(R.id.myPrice);
        myImg = (ImageView) findViewById(R.id.MyImage);
        saveMy = (Button) findViewById(R.id.SaveButt);
        cancelMy = (Button) findViewById(R.id.CancelButt);

//        ArrayList<String> spinList = new ArrayList<>();
//        spinList.add("Pizza");  spinList.add("Burger");  spinList.add("Snack"); spinList.add("Cream");  spinList.add("Sandwich");
//        spinList.add("Breakfast");  spinList.add("Sweets");  spinList.add("Lunch"); spinList.add("Dinner");  spinList.add("Coffee");

        String[] AllCate = {"Pizza","Burger", "Snack", "Cream", "Sandwich", "Breakfast", "Sweets", "Lunch", "Dinner", "Coffee"};
        ArrayAdapter<String> categories =new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, AllCate);
        myCate.setAdapter(categories);
    }


}