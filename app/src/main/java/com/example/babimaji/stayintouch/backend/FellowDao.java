package com.example.babimaji.stayintouch.backend;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.babimaji.stayintouch.model.Fellow;

import java.util.List;

@Dao
public interface FellowDao {

    @Query("SELECT * FROM Fellow")
    List<Fellow> getFellow();

    @Query("SELECT * FROM Fellow WHERE id =:id")
    Fellow getFellow(int id);

    @Insert
    void addAll(Fellow ...fellows);

    @Insert
    void insertFellow(Fellow fellow);

    @Update
    int updateFellow(Fellow fellow);

    @Delete
    int deleteFellow(Fellow fellow);
}
