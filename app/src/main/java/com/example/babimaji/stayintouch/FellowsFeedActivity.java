package com.example.babimaji.stayintouch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.babimaji.stayintouch.backend.Networking;
import com.example.babimaji.stayintouch.model.Fellow;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FellowsFeedActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private static final String TAG = MainActivity.class.getSimpleName();
    private String fellowsResponse;
    private ImageView picture;
    private TextView email;
    private TextView mantra;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fellows_feed);
        getFellowsApi();

        recyclerView = findViewById(R.id.fellowsFeedRV);
        picture = findViewById(R.id.picture);
        email = findViewById(R.id.email);
        mantra = findViewById(R.id.mantra);
    }


    public void getFellowsApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Networking fellowsNetworking = retrofit.create(Networking.class);

        final Call<List<Fellow>> fellowsCall = fellowsNetworking.getFellows();
        fellowsCall.enqueue(new Callback<List<Fellow>>() {
            @Override
            public void onResponse(Call<List<Fellow>> call, Response<List<Fellow>> response) {
                fellowsResponse = response.body().toString();
                Log.d(TAG, "onResponse: Is this what you're looking for?" + fellowsResponse);
                Picasso.with(getApplicationContext())
                        .load(response.body().toString())
                        .into(picture);
            }

            @Override
            public void onFailure(Call<List<Fellow>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }

}
