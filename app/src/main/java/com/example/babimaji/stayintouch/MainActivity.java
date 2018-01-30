package com.example.babimaji.stayintouch;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences login;
    private ImageView applogo;
    private EditText userName;
    private EditText userPassword;
    private CheckBox rememberMe;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        applogo = findViewById(R.id.appLogo);
        userName = findViewById(R.id.userName);
        userPassword = findViewById(R.id.userPassword);
        rememberMe = findViewById(R.id.rememberMe);
        loginBtn = findViewById(R.id.login_btn);
    }


}
