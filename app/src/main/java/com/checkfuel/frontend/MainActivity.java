package com.checkfuel.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.checkfuel.something.R;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void refueling(@NotNull View view) {
        if (view.getId() == R.id.refuling) {
            Intent intent = new Intent(this, SetVolume.class);
            startActivity(intent);
        }
    }

    public void chooseTheGasStation(@NotNull View view) {
        if (view.getId() == R.id.quality) {
            Intent intent = new Intent(this, ChooseTheGasStation.class);
            startActivity(intent);
        }
    }


}