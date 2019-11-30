package com.example.something;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BadFuelOnOkkoA95Euro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bad_fuel_on_okko_a95_euro);
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
