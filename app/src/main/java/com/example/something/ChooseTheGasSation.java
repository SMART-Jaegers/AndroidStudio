package com.example.something;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.jetbrains.annotations.NotNull;

public class ChooseTheGasSation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_the_gas_station);
    }
    public void stationOkko(@NotNull View v) {
        switch (v.getId()) {
            case R.id.okko:
                Intent intent = new Intent(this, StationOkko.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    public void backtomain(View v) {
        switch (v.getId()) {
            case R.id.btnback:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }

}
