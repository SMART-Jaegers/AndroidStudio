package com.example.something;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StationOkko extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_okko);
    }
    public void goodfuel(View view) {
        switch (view.getId()) {
            case R.id.a95:
                Intent intent = new Intent(this,GoodFuelOnOkkoA95Euro.class);
                startActivity(intent);
                break;
            default:
                break;

        }
    }
}
