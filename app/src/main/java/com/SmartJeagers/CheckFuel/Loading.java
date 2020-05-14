package com.SmartJeagers.CheckFuel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.SmartJeagers.CheckFuel.utils.DatabaseManagerForDayOfUse;
import com.SmartJeagers.CheckFuel.utils.DatabaseManagerForRefill;
import com.checkfuel.something.R;

public class Loading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        boolean goForward = true;
        goForward = goForward & DatabaseManagerForRefill.readRefill();
        goForward = goForward & DatabaseManagerForDayOfUse.readDayOfUse();
        Intent intent = new Intent(this, UsageStatistic.class);
        startActivity(intent);
    }
}
