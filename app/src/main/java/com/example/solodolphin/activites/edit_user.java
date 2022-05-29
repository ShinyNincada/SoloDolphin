package com.example.solodolphin.activites;

import static com.example.solodolphin.activites.AddProduct.myDB;
import static com.example.solodolphin.activites.LoginActivity.MyUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.solodolphin.R;
import com.example.solodolphin.sqlite.User;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class edit_user extends AppCompatActivity {

    ImageView pickerbtn;
    Button cancelBtn, saveBtn;
    TextView uId, uMail;
    EditText uName, uPass, uAddress;
    Switch uAdmin;
    int REQUEST_LOCATION = 1000;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        anhxa();

        uId.setText(""+MyUser.getUserId());
        uMail.setText(MyUser.getUserMail());
        uName.setText(MyUser.getUserName());
        uPass.setText(MyUser.getUserPass());
        uAddress.setText(MyUser.getUserAddress());
        String check = MyUser.getIsAdmin();
        uAdmin.setChecked(check != null);
        Toast.makeText(edit_user.this, ""+check, Toast.LENGTH_SHORT).show();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String adminOrNot = (uAdmin.isChecked()) ? "Admin" : null;
                String nName, nPass, nMail, nAddress;


                nName = uName.getText().toString();
                nMail = uMail.getText().toString();
                nPass = uPass.getText().toString();
                nAddress = uAddress.getText().toString();


                if(validate(nName, nPass, nAddress)){
                    User tmp = new User(nName, nMail, nPass, nAddress, adminOrNot);
                   if( myDB.updateUser(MyUser.getUserId(), tmp)){
                       MyUser.setUserAddress(tmp.getUserAddress());
                       MyUser.setUserName(tmp.getUserName());
                       MyUser.setIsAdmin(tmp.getIsAdmin());
                       Toast.makeText(edit_user.this, "Update Successfully", Toast.LENGTH_SHORT).show();
                       finish();
                   }
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        pickerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(edit_user.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    showLocation();
                } else {
                    ActivityCompat.requestPermissions(edit_user.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_LOCATION);
                }
            }
        });
    }

    private void showLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    Geocoder geocoder = new Geocoder(edit_user.this, Locale.getDefault());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        uAddress.setText(addressList.get(0).getAddressLine(0));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else
                    Toast.makeText(edit_user.this, "Null locaation", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void anhxa() {
        pickerbtn = (ImageView) findViewById(R.id.addressPickerbtn);
        cancelBtn = (Button) findViewById(R.id.userCancelBtn);
        saveBtn = (Button) findViewById(R.id.userSavebtn);
        uId = (TextView) findViewById(R.id.UIDtxt);
        uMail = (TextView) findViewById(R.id.usMailtxt);
        uName = (EditText) findViewById(R.id.usNametxt);
        uPass = (EditText) findViewById(R.id.usPasstxt);
        uAddress = (EditText) findViewById(R.id.usAddresstxt);
        uAdmin = (Switch) findViewById(R.id.isAdminSwitch);

    }

    private boolean validate(String fullname, String password, String address) {
        String nameRegex = "^[a-zA-Z](\\s?[a-zA-Z]){1,30}$";
        String passRegex =  "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{5,10}$";

        if(fullname.isEmpty() || address.isEmpty() || password.isEmpty())
        {
            Toast.makeText(edit_user.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!fullname.matches(nameRegex))
        {
            Toast.makeText(edit_user.this, "Your name length must 2-30 characters without number", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!password.matches(passRegex)){
            Toast.makeText(edit_user.this, "Your pass length must 5-10 characters with at least 1 or more Uppercase, special char without any blank space", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }

    }
}