package com.example.something;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.jetbrains.annotations.NotNull;

public class StationOkko extends AppCompatActivity {
int i=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_okko);
    }
    public void checkfuel(@NotNull View view) {
        switch (view.getId()) {
            case R.id.a95:
                if (i==1) {Intent intent = new Intent(this,GoodFuelOnOkkoA95Euro.class);
                startActivity(intent);}
                else {Intent intent = new Intent(this,BadFuelOnOkkoA95Euro.class);
                    startActivity(intent);}
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
