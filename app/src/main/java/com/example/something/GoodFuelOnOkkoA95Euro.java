package com.example.something;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GoodFuelOnOkkoA95Euro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_fuel_on_okko_a95_euro);
        Intent intent = getIntent();
        String weight= intent.getStringExtra("weight");
        String volume= intent.getStringExtra("volume");
        String temperature= intent.getStringExtra("temperature");

        TextView textforce = (TextView) findViewById(R.id.textView5);
        Double density=(Double.parseDouble(weight)/Double.parseDouble(volume))/(1+Double.parseDouble(temperature)*0.001);
        density=density*1000;
        int i = (int) Math.round(density);
        textforce.setText( Integer.toString(i));
    }
    public void backToOkko(View v) {
        switch (v.getId()) {
            case R.id.btnback:
                Intent intent = new Intent(this, StationOkko.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
    public void backToMain(View v) {
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
