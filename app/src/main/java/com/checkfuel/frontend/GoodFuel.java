package com.checkfuel.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.checkfuel.something.R;

import java.text.DecimalFormat;

public class GoodFuel extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_fuel);

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
    }

    public void backToGasStation(View view) {
       finish();
    }

    public void backToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
