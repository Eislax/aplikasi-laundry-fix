package com.latihanpbo.aplikasilaundryfix.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.latihanpbo.aplikasilaundryfix.database.dao.userDao;
import com.latihanpbo.aplikasilaundryfix.model.ModelUser;

@Database(entities = {ModelUser.class}, version = 2)
public abstract class userAppDatabase extends RoomDatabase {
    private static final String dbname = "laundry_db";
    private static userAppDatabase userDatabase;

    public static synchronized userAppDatabase getUserDatabase(Context context){
        if (userDatabase == null){
            userDatabase = Room.databaseBuilder(context, userAppDatabase.class, dbname)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return userDatabase;
    }
    public abstract userDao userDao();
}