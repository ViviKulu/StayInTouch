package com.example.babimaji.stayintouch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.example.babimaji.stayintouch.model.Fellow;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class FellowsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fellows_recyclerview);


        InputStream inputStream = this.getResources().openRawResource(R.raw.fellows);
        String jsonString = readJsonFile(inputStream);

        Gson gson = new Gson();
        Fellow[] fellows = gson.fromJson(jsonString, Fellow[].class);
        List<Fellow> asList = Arrays.asList(fellows);
        for(Fellow fellow : asList) {
            Log.d("name", fellow.getName());
            Log.d("pict", fellow.getPicture());
        }


    }

    private String readJsonFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte[] bufferByte = new byte[1024];
        int length;
        try{
            while((length = inputStream.read(bufferByte)) != -1) {
                outputStream.write(bufferByte, 0, length);
            }
            outputStream.close();
            inputStream.close();
        }
        catch (IOException e) {

        }
        return  outputStream.toString();
    }
}
