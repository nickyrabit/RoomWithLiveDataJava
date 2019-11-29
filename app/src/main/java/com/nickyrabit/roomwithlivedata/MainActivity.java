package com.nickyrabit.roomwithlivedata;


/**Created By Nickyrabit
 * nickyrabit@gmail.com
 * nicky@nickylegnard.com*/

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.nickyrabit.roomwithlivedata.room_db.database.FuelPriceDatabase;
import com.nickyrabit.roomwithlivedata.room_db.model.PetrolPrice;

import java.lang.ref.WeakReference;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FuelPriceDatabase fuelPriceDatabase;
    EditText PetrolPriceEditText;
    Button buttonChangeData;
    TextView displayTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         * Setup Observer
         * */

        //FIRST CREATE DB INSTANCE
        fuelPriceDatabase = FuelPriceDatabase.getAppDatabase(MainActivity.this);

        //SECOND ATTACH TO OBSERVER
        fuelPriceDatabase.petrolPriceDao().getAllPrices().observe(this, new Observer<List<PetrolPrice>>() {
            @Override
            public void onChanged(List<PetrolPrice> petrolPriceList) {

                for (PetrolPrice petrolPrice : petrolPriceList) {

                    //taking the updated data and display it

                    Log.d("POS_DB", petrolPrice.getFuel_price() + " has been updated");
                    Toast.makeText(MainActivity.this, "" + petrolPrice.getFuel_price() + " has been updated", Toast.LENGTH_SHORT).show();

                    displayTextView.setText("From the Obeserver : " + petrolPrice.getFuel_price());

                }
            }
        });



        PetrolPriceEditText = findViewById(R.id.PetrolPrice);
        buttonChangeData = findViewById(R.id.buttonChangeData);
        displayTextView = findViewById(R.id.displayTextView);



        buttonChangeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get input
                double price = Double.parseDouble(PetrolPriceEditText.getText().toString());
                PetrolPrice petrolPrice = new PetrolPrice(1, price);


                // save or update to the table Async
                new PetrolPriceInsert(MainActivity.this, petrolPrice).execute();


            }
        });

    }


    static class PetrolPriceInsert extends AsyncTask<Void, Void, Void> {
        private PetrolPrice petrolPriceList;
        private WeakReference<Context> contextWeakReference;

        public PetrolPriceInsert(Context contextWeakReference, PetrolPrice petrolPriceList) {
            this.contextWeakReference = new WeakReference<>(contextWeakReference);
            this.petrolPriceList = petrolPriceList;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                FuelPriceDatabase ud = FuelPriceDatabase.getAppDatabase(contextWeakReference.get());

                ud.petrolPriceDao().insert(petrolPriceList);


            } catch (Exception e) {
                Log.e("ERROR", "Exception " + e);
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(contextWeakReference.get(), "List Updated!", Toast.LENGTH_SHORT).show();

        }
    }


}
