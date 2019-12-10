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
        //double percentt;
        Intent intent = getIntent();
        String volume= intent.getStringExtra("volume");
        String realvolume= intent.getStringExtra("realvolume");

        if (volume==null) {
            volume="0.0";
        }


        volume=volume + " liters";
        //percentt=Double. parseDouble(volume)/Double.parseDouble("55");
        textvolume.setText(volume);
        textresult.setText(realvolume + " liters");
       //percent.setText( Double.toString (percentt));
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
