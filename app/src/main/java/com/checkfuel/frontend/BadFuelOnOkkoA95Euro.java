package com.checkfuel.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.checkfuel.something.R;

import java.text.DecimalFormat;

public class BadFuelOnOkkoA95Euro extends AppCompatActivity {
    TextView textForce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bad_fuel_on_okko_a95_euro);

        Intent intent = getIntent();

        String weight = intent.getStringExtra("weight");
        String temperature = intent.getStringExtra("temperature");
        String volume = intent.getStringExtra("volume");

        textForce = findViewById(R.id.textView5);

        assert weight != null;
        assert volume != null;
        assert temperature != null;
        double density = (Double.parseDouble(weight) / Double.parseDouble(volume)) / (1 + Double.parseDouble(temperature) * 0.001);
        density *= 1000;
        textForce.setText(new DecimalFormat("#").format(density));
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
