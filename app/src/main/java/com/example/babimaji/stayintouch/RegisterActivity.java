package com.example.babimaji.stayintouch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    private SharedPreferences registerSharedPrefs;
    private EditText userName;
    private EditText userPassword;
    private EditText confirmPassword;
    private Button submitRegBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        userName = findViewById(R.id.register_username_edittext);
        userPassword = findViewById(R.id.register_password_edittext);
        confirmPassword = findViewById(R.id.confirm_password_edittext);
        submitRegBtn = findViewById(R.id.submit_button);

        Intent intent = getIntent();
        registerSharedPrefs = getApplicationContext().getSharedPreferences(intent.getStringExtra("test_key"), MODE_PRIVATE);

        submitRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = registerSharedPrefs.edit();
                if(userName.getText() != null && userPassword.getText() != null && confirmPassword.getText() != null
                        && userPassword.getText().toString().equals(confirmPassword.getText().toString())){
                    editor.putString("user" + userName.getText().toString(), userName.getText().toString());
                    editor.putString("password" + userPassword.getText().toString(), userPassword.getText().toString());
                    editor.commit();
                    finish();
                }
            }
        });
    }
}
