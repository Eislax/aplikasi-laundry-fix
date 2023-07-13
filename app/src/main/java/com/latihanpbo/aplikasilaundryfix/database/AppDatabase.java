package com.latihanpbo.aplikasilaundryfix.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.latihanpbo.aplikasilaundryfix.database.dao.LaundryDao;
import com.latihanpbo.aplikasilaundryfix.model.ModelLaundry;

/**
 * Created by Azhar Rivaldi on 19-11-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * LinkedIn : https://www.linkedin.com/in/azhar-rivaldi
 */

@Database(entities = {ModelLaundry.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LaundryDao laundryDao();
}
