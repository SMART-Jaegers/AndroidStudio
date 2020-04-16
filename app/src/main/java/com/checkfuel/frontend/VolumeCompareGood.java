package com.checkfuel.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.checkfuel.something.R;

import org.jetbrains.annotations.NotNull;

public class VolumeCompareGood extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_compare_good);

        TextView textExpectedVolume = findViewById(R.id.volumegood);
        Intent intent = getIntent();
        double expectedVolume = intent.getDoubleExtra("expectedVolume", 0);
        textExpectedVolume.setText(String.format("%s liters", expectedVolume));
    }

    public void backToMain(@NotNull View view) {
        if (view.getId() == R.id.btnhome) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
}
