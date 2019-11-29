package com.nickyrabit.roomwithlivedata.room_db.model;


/**Created By Nickyrabit
 * nickyrabit@gmail.com
 * nicky@nickylegnard.com*/
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PetrolPrice {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "fuel_price")
    double fuel_price;

    public PetrolPrice(int id, double fuel_price) {
        this.id = id;
        this.fuel_price = fuel_price;
    }

    public PetrolPrice() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getFuel_price() {
        return fuel_price;
    }

    public void setFuel_price(double fuel_price) {
        this.fuel_price = fuel_price;
    }
}
