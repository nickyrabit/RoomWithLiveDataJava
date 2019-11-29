package com.nickyrabit.roomwithlivedata.room_db.dao_interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.nickyrabit.roomwithlivedata.room_db.model.PetrolPrice;

import java.util.List;

@Dao
public interface PetrolPriceDao {
    @Insert
    Long insert(PetrolPrice c);

    @Query("SELECT * FROM 'PetrolPrice'")
    LiveData<List<PetrolPrice>> getAllPrices();

    @Update
    void update(PetrolPrice c);

    @Query("SELECT * FROM `PetrolPrice` WHERE `id` =:id")
    PetrolPrice getConstant(int id);

    @Delete
    void delete(PetrolPrice c);
}
