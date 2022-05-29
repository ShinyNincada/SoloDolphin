package com.example.solodolphin.activites;

import static com.example.solodolphin.activites.AddProduct.myDB;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.solodolphin.R;
import com.example.solodolphin.sqlite.MyProduct;


public class RegistrationActivity extends AppCompatActivity {

    EditText fullname, email, password;
    Button regisBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        myDB = new MyProduct(this, "shinyNincada", null , 1);
        anhxa();
        regisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urName = fullname.getText().toString();
                String urMail = email.getText().toString();
                String urPass = password.getText().toString();
                if(validate(urName, urMail,urPass)){
                   if( myDB.RegisterAccount(urName,urMail,urPass))
                       Toast.makeText(RegistrationActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private boolean validate(String fullname, String email, String password) {
        String nameRegex = "^[a-zA-Z](\\s?[a-zA-Z]){1,30}$";
        String mailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String passRegex =  "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{5,20}$";

        if(fullname.isEmpty() || email.isEmpty() || password.isEmpty())
        {
            Toast.makeText(RegistrationActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!fullname.matches(nameRegex))
        {
            Toast.makeText(RegistrationActivity.this, "Your name length must 2-30 characters without number", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!email.matches(mailRegex)){
            Toast.makeText(RegistrationActivity.this, "Your mail must follow regex abc@def.xyz", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!password.matches(passRegex)){
            Toast.makeText(RegistrationActivity.this, "Your pass length must 2-30 characters with at least 1 or more Uppercase, special char without any blank space", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(myDB.checkUserMail(email)){
            Toast.makeText(RegistrationActivity.this, "Your username has been taken :3", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }

    }

    private void anhxa() {
        fullname = findViewById(R.id.fullnameEdit);
        email = findViewById(R.id.EmailEdit);
        password = findViewById(R.id.PasswordEdit);
        regisBtn = findViewById(R.id.RegisBtn2);

    }

    public void SignIn(View view) {
        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    }
}