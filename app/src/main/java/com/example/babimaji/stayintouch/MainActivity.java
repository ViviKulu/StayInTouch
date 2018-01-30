package com.example.babimaji.stayintouch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String SHARED_PREFS_KEY = "sharedPrefsTesting";
    private SharedPreferences login;
    private ImageView applogo;
    private EditText userName;
    private EditText userPassword;
    private CheckBox rememberMe;
    private Button loginBtn;
    private TextView notReg;
    private Button regBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        applogo = findViewById(R.id.appLogo);
        userName = findViewById(R.id.userName);
        userPassword = findViewById(R.id.userPassword);
        rememberMe = findViewById(R.id.rememberMe);
        loginBtn = findViewById(R.id.login_btn);
        notReg = findViewById(R.id.notregtv);
        regBtn = findViewById(R.id.registerBtn);
        login = getApplicationContext().getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);

        if(login.getBoolean("isChecked", false)){
            userName.setText(login.getString("username", null));
            userPassword.setText(login.getString("password", null));
            rememberMe.setChecked(login.getBoolean("isChecked", false));

        }


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = login.edit();
                if(rememberMe.isChecked()){
                    editor.putString("username", userName.getText().toString());
                    editor.putString("password", userPassword.getText().toString());
                    editor.putBoolean("isChecked", rememberMe.isChecked());
                }else{
                    editor.putBoolean("isChecked", rememberMe.isChecked());
                    editor.commit();
                }

                String checkUser = "user" + userName.getText().toString();
                String checkUserPassword = "password" + userPassword.getText().toString();
                if(userName.getText().toString().equalsIgnoreCase(login.getString(checkUser, null))
                        && userPassword.getText().toString().equals(login.getString(checkUserPassword, null))){
                    Intent intentToProfileActivity = new Intent(MainActivity.this, PreProfileActivity.class);
                    intentToProfileActivity.putExtra("userName", userName.getText().toString());
                    startActivity(intentToProfileActivity);
                }
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToRegistration = new Intent(MainActivity.this, RegisterActivity.class);
                intentToRegistration.putExtra("test_key", SHARED_PREFS_KEY);
                startActivity(intentToRegistration);
            }
        });
    }

}
