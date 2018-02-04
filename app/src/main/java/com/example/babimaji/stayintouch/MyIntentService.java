package com.example.babimaji.stayintouch;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import com.example.babimaji.stayintouch.R;
import com.example.babimaji.stayintouch.model.Fellow;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import constants.Constants;
import fragments.HomeFragment;


public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        InputStream inputStream = getResources().openRawResource(R.raw.fellows);
        String jsonString = readJsonFile(inputStream);

        Gson gson = new Gson();
        Fellow[] fellows = gson.fromJson(jsonString, Fellow[].class);
        ArrayList<Fellow> arrList = new ArrayList<>();
        for(Fellow fellow: fellows) {
            arrList.add(fellow);

        };

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(
                HomeFragment.ResponseReceiver.LOCAL_ACTION);
        broadcastIntent.putParcelableArrayListExtra(Constants.LIST_OF_FELLOWS, arrList);
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.sendBroadcast(broadcastIntent);
    }

    private String readJsonFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte[] bufferByte = new byte[1024];
        int length;
        try {
            while ((length = inputStream.read(bufferByte)) != -1) {
                outputStream.write(bufferByte, 0, length);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();

    }
}
