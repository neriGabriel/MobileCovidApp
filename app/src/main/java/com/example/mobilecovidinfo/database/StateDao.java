package com.example.mobilecovidinfo.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mobilecovidinfo.model.State;

import java.util.List;

@Dao
public interface StateDao {

    @Insert
    void insert(List<State> state);

    @Query("SELECT * FROM tb_state ORDER BY st_cases DESC")
    List<State> getLastRegisters();

    @Query("DELETE FROM tb_state")
    void delete();

    @Query("SELECT * FROM tb_state WHERE st_id = :id")
    State getById(Integer id);
}
