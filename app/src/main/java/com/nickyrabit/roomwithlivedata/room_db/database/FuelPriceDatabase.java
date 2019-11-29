package com.nickyrabit.roomwithlivedata.room_db.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nickyrabit.roomwithlivedata.room_db.dao_interfaces.PetrolPriceDao;
import com.nickyrabit.roomwithlivedata.room_db.model.PetrolPrice;
/**
 *Look at the @Database annotation, we have defined
 * the table we made earlier by calling it entities
 * {PetrolPrice.class} you can name as any tables as you like there
 *
 *
 * VERSION is the version of the table if you alter it you need to add a new version.
 *
 *exportSchema is used when you want to keep track of the  database versions. Turn it off in Production yoooooooo!!!
 *
 *
 *
 */



@Database(entities = {PetrolPrice.class}, version = 2, exportSchema = false)
public abstract class FuelPriceDatabase extends RoomDatabase {
    public abstract PetrolPriceDao petrolPriceDao();

    private static FuelPriceDatabase INSTANCE;
//Inside the code, we have this line inside a constructor which defines the database "Price Database" by using DatabaseBuilder method
    //DatabaseBuilder Creates a RoomDatabase.Builder for a persistent database. Once a database is built, you should keep a reference to it and re-use it.
    public static FuelPriceDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), FuelPriceDatabase.class, "room-database").build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
