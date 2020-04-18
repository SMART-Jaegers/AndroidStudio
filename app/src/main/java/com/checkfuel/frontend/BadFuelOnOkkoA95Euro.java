package com.checkfuel.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.checkfuel.something.R;

import java.text.DecimalFormat;

public class BadFuelOnOkkoA95Euro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bad_fuel_on_okko_a95_euro);

        TextView textDensity = findViewById(R.id.Density);
        TextView textMinDensity = findViewById(R.id.minDensity);
        TextView textMaxDensity = findViewById(R.id.maxDensity);
        TextView textFuelType = findViewById(R.id.fuelType);
        TextView textNameStation = findViewById(R.id.nameStation);

        Intent intent = getIntent();
        double density = intent.getDoubleExtra("density", 0);
        int minDensity = intent.getIntExtra("minDensity", 0);
        int maxDensity = intent.getIntExtra("maxDensity", 0);
        String nameStation = intent.getStringExtra("nameStation");
        String fuelType = intent.getStringExtra("fuelType");

        textDensity.setText(new DecimalFormat("#").format(density));
        textMinDensity.setText(new DecimalFormat("#").format(minDensity));
        textMaxDensity.setText(new DecimalFormat("#").format(maxDensity));
        textNameStation.setText(nameStation);
        textFuelType.setText(fuelType);
        if (density < maxDensity) {
            textDensity.setPadding(100, 0, 0, 0);
        } else {
            textDensity.setPadding(850, 0, 0, 0);
        }
    }

    public void backToGasStation(View v) {
        if (v.getId() == R.id.btnback) {
            Intent intent = new Intent(this, GasStation.class);
            startActivity(intent);
        }

    }

    public void backToMain(View v) {
        if (v.getId() == R.id.btnhome) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
