package com.checkfuel.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.checkfuel.something.R;

import org.jetbrains.annotations.NotNull;

public class VolumeCompareGood extends AppCompatActivity {
    TextView textVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_compare_good);

        textVolume = (TextView) findViewById(R.id.volumegood);

        Intent intent = getIntent();

        String volume = intent.getStringExtra("expectedVolume");

        textVolume.setText(String.format("%s liters", volume));

    }

    public void backToMain(@NotNull View view) {
        if (view.getId() == R.id.btnhome) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
}
