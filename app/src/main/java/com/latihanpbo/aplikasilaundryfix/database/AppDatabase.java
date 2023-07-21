package com.latihanpbo.aplikasilaundryfix.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.latihanpbo.aplikasilaundryfix.database.dao.LaundryDao;
import com.latihanpbo.aplikasilaundryfix.model.ModelLaundry;



@Database(entities = {ModelLaundry.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LaundryDao laundryDao();
}