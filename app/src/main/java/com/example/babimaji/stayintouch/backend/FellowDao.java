package com.example.babimaji.stayintouch.backend;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.babimaji.stayintouch.model.Fellow;

import java.util.ArrayList;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface FellowDao {

    @Query("SELECT * FROM Fellow")
    List<Fellow> getFellow();

    @Query("SELECT * FROM Fellow WHERE id =:id")
    Fellow getFellow(int id);

    @Query("SELECT * FROM Fellow WHERE isFavorite =:isFavorite")
    List<Fellow> getFavoriteFellows(boolean isFavorite);

    @Insert
    void addAll(ArrayList<Fellow> fellows);

    //@Update does not work for some reason
    @Insert
    void isFavorite(Fellow fellow);

    @Delete
    int deleteFellow(Fellow fellow);
}
