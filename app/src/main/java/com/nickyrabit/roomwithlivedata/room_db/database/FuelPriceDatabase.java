package com.nickyrabit.roomwithlivedata.room_db.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nickyrabit.roomwithlivedata.room_db.dao_interfaces.PetrolPriceDao;
import com.nickyrabit.roomwithlivedata.room_db.model.PetrolPrice;

@Database(entities = {PetrolPrice.class}, version = 2, exportSchema = false)
public abstract class FuelPriceDatabase extends RoomDatabase {
    public abstract PetrolPriceDao petrolPriceDao();

    private static FuelPriceDatabase INSTANCE;

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
