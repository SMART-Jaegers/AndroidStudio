package com.example.something;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.jetbrains.annotations.NotNull;

public class StationOkko extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_okko);
    }
    public void goodfuel(@NotNull View view) {
        switch (view.getId()) {
            case R.id.a95:
                Intent intent = new Intent(this,GoodFuelOnOkkoA95Euro.class);
                startActivity(intent);
                break;
            default:
                break;

        }
    }

    public void backtostations(@NotNull View v) {
        switch (v.getId()) {
            case R.id.btnback:
                Intent intent = new Intent(this, ChooseTheGasSation.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
