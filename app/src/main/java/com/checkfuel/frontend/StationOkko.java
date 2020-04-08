package com.checkfuel.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.checkfuel.models.Post;
import com.checkfuel.something.R;
import com.checkfuel.utils.DatabaseManager;

import org.jetbrains.annotations.NotNull;

public class StationOkko extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_okko);
    }

    public void doCompare(View view) {
        Post post = DatabaseManager.getPost();
        double weight = post.getWeight();
        double realVolume = post.getVolumeFill();
        double temperature = post.getTemperature();
        double density = 1000 * (weight / realVolume) / (1 + temperature * 0.001);

        Intent intent = new Intent();

        if (density <= 800) {
            intent.setClass(StationOkko.this, GoodFuelOnOkkoA95Euro.class);
        } else {
            intent.setClass(StationOkko.this, BadFuelOnOkkoA95Euro.class);
        }
        intent.putExtra("density", density);
        startActivity(intent);
    }

    public void backToStations(@NotNull View view) {
        if (view.getId() == R.id.btnback) {
            Intent intent = new Intent(this, ChooseTheGasStation.class);
            startActivity(intent);
        }

    }


}
