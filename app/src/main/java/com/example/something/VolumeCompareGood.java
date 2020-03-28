package com.example.something;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

public class VolumeCompareGood extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_compare_good);
        TextView textVolume = (TextView) findViewById(R.id.volumegood);
        Intent intent = getIntent();
        String volume = intent.getStringExtra("volume");
        textVolume.setText(String.format("%s liters", volume));

    }

    public void backToMain(@NotNull View view) {
        if (view.getId() == R.id.btnhome) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
}
