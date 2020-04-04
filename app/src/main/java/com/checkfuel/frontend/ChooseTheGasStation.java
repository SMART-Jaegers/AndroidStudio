package com.checkfuel.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.checkfuel.something.R;

public class ChooseTheGasStation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_the_gas_station);
    }

    public void stationOkko(View v) {
        if (v.getId() == R.id.okko) {
            Intent intent = new Intent(this, StationOkko.class);
            startActivity(intent);
        }
    }

    public void backToMain(View v) {
        if (v.getId() == R.id.btnback) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
