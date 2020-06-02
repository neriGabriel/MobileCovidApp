package com.example.mobilecovidinfo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mobilecovidinfo.model.State;

@Database(entities = {State.class}, version = 1, exportSchema = false)
public abstract class DatabaseConnection extends RoomDatabase {

    private static DatabaseConnection instance;
    public abstract StateDao stateDao();

    public static DatabaseConnection getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context, DatabaseConnection.class, "covid_app")
                           .allowMainThreadQueries()
                           .build();
            }
        return instance;
    }
}
