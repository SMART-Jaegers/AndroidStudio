package com.checkfuel.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.checkfuel.something.R;

import java.text.DecimalFormat;

public class VolumeCompareBad extends AppCompatActivity {
    TextView textExpectedVolume, textRealVolume, textRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_compare_bad);

        textExpectedVolume = findViewById(R.id.expectedvolume);
        textRate = findViewById(R.id.percent);
        textRealVolume = findViewById(R.id.volumeResult);

        Intent intent = getIntent();

        double expectedVolume = intent.getDoubleExtra("expectedVolume", 1);
        double realVolume = intent.getDoubleExtra("realVolume", 0);
        double rate = (1 - realVolume / expectedVolume);

        textExpectedVolume.setText(String.format("%s liters", expectedVolume));
        textRealVolume.setText(String.format("%s liters", realVolume));
        textRate.setText(new DecimalFormat("#0.00%").format(rate));
    }

    public void backToMain(View view) {
        if (view.getId() == R.id.btnhome) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

}
