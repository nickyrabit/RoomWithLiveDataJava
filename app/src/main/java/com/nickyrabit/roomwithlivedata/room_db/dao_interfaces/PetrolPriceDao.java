package com.nickyrabit.roomwithlivedata.room_db.dao_interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.nickyrabit.roomwithlivedata.room_db.model.PetrolPrice;

import java.util.List;

/**
 *Now there is something called Data Access Object or DAO.
 *  They are the main component in your Room Database.
 *  They process the queries and stuff
 *
 *
 * You declare them by using interfaces.
 * Now I am making a DAO called PetrolPriceDao.java
 * that will perform CRUD operations (Create, Read, Update, Delete)
 */


@Dao
public interface PetrolPriceDao {
    @Insert
    Long insert(PetrolPrice c);

    /*
    * I have set a LIVEDATA on
    *  PetrolPriceList which has the task of alerting all Observer object
    * when there is a change in a particular data in our case,
    * PetrolPrice.*/
        @Query("SELECT * FROM 'PetrolPrice'")
    LiveData<List<PetrolPrice>> getAllPrices();

    @Update
    void update(PetrolPrice c);

    @Query("SELECT * FROM `PetrolPrice` WHERE `id` =:id")
    PetrolPrice getConstant(int id);

    @Delete
    void delete(PetrolPrice c);
}
