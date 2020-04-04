package com.checkfuel.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.checkfuel.something.R;
import com.checkfuel.utils.ConnectToServer;

import org.jetbrains.annotations.NotNull;

public class StationOkko extends AppCompatActivity {
    String weight = ConnectToServer.getWeight();
    String volume = ConnectToServer.getVolume();
    String temperature = ConnectToServer.getTemperature();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_okko);
    }

    public void doCompare(View view) {
        if (Double.parseDouble(weight) / Double.parseDouble(volume) < 0.8) {
            Intent intent = new Intent(StationOkko.this, GoodFuelOnOkkoA95Euro.class);
            intent.putExtra("weight", weight);
            intent.putExtra("temperature", temperature);
            intent.putExtra("volume", volume);
            startActivity(intent);
        } else {
            Intent intent = new Intent(StationOkko.this, BadFuelOnOkkoA95Euro.class);
            intent.putExtra("weight", weight);
            intent.putExtra("temperature", temperature);
            intent.putExtra("volume", volume);
            startActivity(intent);
        }
    }

    public void backToStations(@NotNull View view) {
        if (view.getId() == R.id.btnback) {
            Intent intent = new Intent(this, ChooseTheGasStation.class);
            startActivity(intent);
        }

    }


}
