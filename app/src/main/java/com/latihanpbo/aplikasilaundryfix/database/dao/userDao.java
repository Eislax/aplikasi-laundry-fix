package com.latihanpbo.aplikasilaundryfix.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.latihanpbo.aplikasilaundryfix.model.ModelUser;

import java.util.List;

@Dao
public interface userDao {

    @Query("SELECT * FROM tbl_user")
    LiveData<List<ModelUser>> getAllUser();

    @Query("SELECT * FROM tbl_user WHERE username = :username AND password = :password")
    ModelUser getUserByUsernameAndPassword(String username, String password);

    @Insert
    void registerUser(ModelUser userEntity);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertDataUser(ModelUser... modelUsers);

    @Query("UPDATE tbl_user SET username= :username, password= :password WHERE uid= :uid")
    void updateDataUser(String username, String password, int uid);

}

