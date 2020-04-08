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

        TextView textDensity = findViewById(R.id.textView5);
        Intent intent = getIntent();
        double density = intent.getDoubleExtra("density", 0);
        textDensity.setText(new DecimalFormat("#").format(density));
        textDensity.setLeft(10);
    }

    public void backToOkko(View v) {
        if (v.getId() == R.id.btnback) {
            Intent intent = new Intent(this, StationOkko.class);
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
