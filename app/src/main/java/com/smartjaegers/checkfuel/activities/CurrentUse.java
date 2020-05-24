package com.smartjaegers.checkfuel.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.smartjaegers.checkfuel.R;
import com.smartjaegers.checkfuel.models.DataBluetooth;

import java.text.DecimalFormat;


public class CurrentUse extends AppCompatActivity {
    TextView textLiter, textKm, textLitersPerKm, textKmWithThisFuel;

    private DataBluetooth dataBluetooth;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataBluetooth = new DataBluetooth();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_use);

        textLiter = findViewById(R.id.liter);
        textKm = findViewById(R.id.km);
        textLitersPerKm = findViewById(R.id.litersPerKm);
        textKmWithThisFuel = findViewById(R.id.kmWithThisFuel);

        textLiter.setText(new DecimalFormat("##0.0 L").format(dataBluetooth.getLiter()));
        textKm.setText(new DecimalFormat("##0.0# Km").format(dataBluetooth.getAlreadyKm()));
        textLitersPerKm.setText(new DecimalFormat("#0.0# L/Km").format(dataBluetooth.getLitersPerKm()));
        textKmWithThisFuel.setText(new DecimalFormat("##0 Km").format(dataBluetooth.calculatePredictionOnKm()));
    }

    public void backToMain(View v) {
        finish();
    }
}
