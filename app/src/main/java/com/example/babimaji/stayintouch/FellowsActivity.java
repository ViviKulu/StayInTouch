package com.example.babimaji.stayintouch;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.example.babimaji.stayintouch.model.Fellow;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.babimaji.stayintouch.backend.AppDatabase;
import com.example.babimaji.stayintouch.controller.FellowsAdapter;

public class FellowsActivity extends AppCompatActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fellows_recyclerview);


        InputStream inputStream = this.getResources().openRawResource(R.raw.fellows);
        String jsonString = readJsonFile(inputStream);

        Gson gson = new Gson();
        Fellow[] fellows = gson.fromJson(jsonString, Fellow[].class);

        List<Fellow> fellowsList = Arrays.asList(fellows);
        for(Fellow fellow : fellowsList) {
            Log.d("name", fellow.getName());
            Log.d("pict", fellow.getPicture());
            Log.d("linkedin", fellow.getLinkedin());
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.fellows_RV);
        FellowsAdapter fellowsAdapter = new FellowsAdapter(fellowsList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), GridLayoutManager.VERTICAL);
        recyclerView.setAdapter(fellowsAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);


         db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "Fellows")
                 .fallbackToDestructiveMigration()
                 .build();

        getDataFromDB(fellows);

    }

    public void getDataFromDB(final Fellow[] fellows) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void ... voids) {
                    db.fellowDao().addAll(fellows);
                    Log.d("database created", db.fellowDao().getFellow(2).getName());
                    return null;
                }

        }.execute();
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
