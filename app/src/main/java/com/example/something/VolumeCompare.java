package com.example.something;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VolumeCompare extends AppCompatActivity {
TextView textvolume, textresult, percent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_compare_bad);
        textvolume = (TextView) findViewById(R.id.expectedvolume);
        percent = (TextView) findViewById(R.id.percent);
        textresult = (TextView) findViewById(R.id.volumeResult);
        double percentt=3.4;
        Intent intent = getIntent();
        String volume= intent.getStringExtra("volume");
        String realvolume= intent.getStringExtra("realvolume");
        String s="32";
        if (volume=="null") {
            volume="0.0";
        }


        percentt=Double.parseDouble(realvolume)/Double.parseDouble(volume);
        percentt=10000-percentt*10000;
        int i = (int) Math.round(percentt);
        percentt =(double)i / 100;
        textvolume.setText(volume+" liters");
        textresult.setText(realvolume + " liters");
        percent.setText( Double.toString(percentt)+"%" );
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
