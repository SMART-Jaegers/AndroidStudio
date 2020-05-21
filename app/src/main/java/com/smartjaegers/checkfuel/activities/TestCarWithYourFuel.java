package com.smartjaegers.checkfuel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.smartjaegers.checkfuel.R;

public class TestCarWithYourFuel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_car_with_your_fuel);
    }


    public void startTesting(View view) {
        Intent intent = new Intent(this, TestRunning.class);
        finish();
        startActivity(intent);
    }

    public void backToMain(View view) {
        finish();
    }
}
