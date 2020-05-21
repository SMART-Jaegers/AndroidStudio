package com.smartjaegers.checkfuel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.smartjaegers.checkfuel.R;

public class TestRunning extends AppCompatActivity {

    private Button button;
    private TextView infoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_running);
        button = findViewById(R.id.drive_back);
        infoView = findViewById(R.id.info_view);
        onDrivingKm();
    }

    private void onDrivingKm() {
        button.setVisibility(View.VISIBLE);
        button.setClickable(true);
        infoView.setText(R.string.info_test_running_second);
    }

    public void startDrivingBack(View view) {
        button.setClickable(false);
        button.setVisibility(View.INVISIBLE);
        infoView.setText(R.string.info_test_running_third);
        onSuccessTest();
    }

    private void onSuccessTest() {

        new SuccessTest().show(getSupportFragmentManager(), "success test");
    }

    public void backToMain(View view) {
        finish();
    }
}
