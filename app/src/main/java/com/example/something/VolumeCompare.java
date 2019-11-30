package com.example.something;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class VolumeCompare extends AppCompatActivity {
TextView textvolume;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_compare);
        textvolume = (TextView) findViewById(R.id.expectedvolume);
        Intent intent = getIntent();
        String volume= intent.getStringExtra("volume");
        if (volume==null) {
            volume="0.0";
        }

        volume=volume + " liters";
        textvolume.setText(volume);
    }
    public void backtomain(View v) {
        switch (v.getId()) {
            case R.id.btnhome:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
