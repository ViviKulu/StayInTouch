package com.example.babimaji.stayintouch.backend;

import com.example.babimaji.stayintouch.model.Fellow;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by BabiMaji on 1/30/18.
 */

public interface Networking {

    @GET("vivian-kulumba/StayInTouch/master/Fellows.json")
    Call<List<Fellow>> getFellows();
}
