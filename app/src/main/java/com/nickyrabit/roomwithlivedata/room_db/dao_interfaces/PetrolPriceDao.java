package com.nickyrabit.roomwithlivedata.room_db.dao_interfaces;

/**Created By Nickyrabit
 * nickyrabit@gmail.com
 * nicky@nickylegnard.com*/
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(PetrolPrice c);

    /*
    * I have set a LIVEDATA on
    *  PetrolPriceList which has the task of alerting all Observer object
    * when there is a change in a particular data in our case,
    * PetrolPrice.
    *
    *
    * A quick explanation on code below,
    *  @Query() is an annotation that will hold all SQLite queries
    *  because Room can perform only some few basic queries
    * without typing them.
    * */


    @Query("SELECT * FROM 'PetrolPrice'")
    @OnConflictStrategy()
    LiveData<List<PetrolPrice>> getAllPrices();

    @Update
    void update(PetrolPrice c);

    @Query("SELECT * FROM `PetrolPrice` WHERE `id` =:id")
    PetrolPrice getConstant(int id);

    @Delete
    void delete(PetrolPrice c);
}
