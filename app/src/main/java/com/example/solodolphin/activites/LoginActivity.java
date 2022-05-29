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
import com.example.solodolphin.sqlite.User;

public class LoginActivity extends AppCompatActivity {

    public static User MyUser;
    EditText user, pass;
    Button Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhxa();
        myDB = new MyProduct(this, "shinyNincada", null , 1);

        //set lệnh đăng nhập
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = user.getText().toString().trim();
                String password = pass.getText().toString();
                if(validation(username, password))
                {
                    MyUser = myDB.getUser(username, password);
                    Toast.makeText(LoginActivity.this, "Have a nice day^^"+MyUser.getUserName(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        });
    }

    //điều kiện đăng nhập
    private boolean validation(String username, String password) {
        if(username.isEmpty() || password.isEmpty())
        {
            Toast.makeText(LoginActivity.this, "Please enter full information", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!myDB.checkUserMail(username)){
            Toast.makeText(LoginActivity.this, "Account does not exist", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!myDB.checkUsernameAndPassword(username, password)){
            Toast.makeText(LoginActivity.this, "Password and Account doesn't match", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;

    }

    private void anhxa() {
        user = findViewById(R.id.fullnameEdit2);
        pass = findViewById(R.id.PasswordEdt);
        Login = findViewById(R.id.LoginBtn);
    }

    public void register(View view) {
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }

    public void SignIn(View view) {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}