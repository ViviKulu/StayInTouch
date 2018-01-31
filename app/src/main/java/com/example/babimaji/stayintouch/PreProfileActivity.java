package com.example.babimaji.stayintouch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PreProfileActivity extends AppCompatActivity {

    private TextView userName;
    private EditText mantra;
    private Button postBtn;
    private Button profileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_pre);

        userName = findViewById(R.id.profile_welcome_tv);
        mantra = findViewById(R.id.user_mantra);

        Intent intentToPreProfileActivity = getIntent();
        String user = intentToPreProfileActivity.getStringExtra("userName");
        userName.setText("Welcome: " + user);

//        postBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intoFellowsFeed = new Intent(PreProfileActivity.this, FellowsFeedActivity.class);
//                intoFellowsFeed.putExtra("mantra", mantra.getText().toString());
//                startActivity(intoFellowsFeed);
//            }
//        });


    }
}
