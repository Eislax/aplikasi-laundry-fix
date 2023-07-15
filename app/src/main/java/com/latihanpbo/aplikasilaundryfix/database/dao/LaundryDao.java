package com.latihanpbo.aplikasilaundryfix.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.latihanpbo.aplikasilaundryfix.model.ModelLaundry;

import java.util.List;


@Dao
public interface LaundryDao {

    @Query("SELECT * FROM tbl_laundry")
    LiveData<List<ModelLaundry>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(ModelLaundry... modelLaundries);

    @Query("DELETE FROM tbl_laundry WHERE uid= :uid")
    void deleteSingleData(int uid);

    @Query("DELETE FROM tbl_laundry")
    void deleteAllData();

    @Query("UPDATE tbl_laundry SET nama_jasa= :nama_jasa, items= :items, alamat = :alamat, harga= :harga WHERE uid= :uid")
    void updateData(String nama_jasa, int items, String alamat, int harga, int uid);
}
