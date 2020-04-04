package com.checkfuel.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.checkfuel.something.R;

import java.text.DecimalFormat;

public class VolumeCompareBad extends AppCompatActivity {
    TextView textVolume, textresult, textRate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_compare_bad);

        textVolume = findViewById(R.id.expectedvolume);
        textRate = findViewById(R.id.percent);
        textresult = findViewById(R.id.volumeResult);

        Intent intent = getIntent();

        String expectedVolume = intent.getStringExtra("expectedVolume");
        String realVolume = intent.getStringExtra("realVolume");


        assert realVolume != null;
        assert expectedVolume != null;
        double rate = (1 - Double.parseDouble(realVolume) / Double.parseDouble(expectedVolume));

        textVolume.setText(String.format("%s liters", expectedVolume));
        textresult.setText(String.format("%s liters", realVolume));
        textRate.setText(new DecimalFormat("#0.00%").format(rate));
    }


    public void backToMain(View view) {
        if (view.getId() == R.id.btnhome) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

}
