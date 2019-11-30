package com.example.something;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GoodFuelOnOkkoA95Euro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_fuel_on_okko_a95_euro);
    }
    public void backtookko(View v) {
        switch (v.getId()) {
            case R.id.btnback:
                Intent intent = new Intent(this, StationOkko.class);
                startActivity(intent);
                break;
            default:
                break;
        }

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
