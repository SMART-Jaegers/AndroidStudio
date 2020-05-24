package com.smartjaegers.checkfuel.activities;

import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.smartjaegers.checkfuel.R;
import com.smartjaegers.checkfuel.bluetooth.BluetoothConnectionService;
import com.smartjaegers.checkfuel.models.DataBluetooth;
import com.smartjaegers.checkfuel.models.OnGetResult;

import java.text.DecimalFormat;


public class CurrentUse extends AppCompatActivity {
    TextView textLiter, textKm, textLitersPerKm, textKmWithThisFuel;

    private DataBluetooth dataBluetooth;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBluetooth = new DataBluetooth();
        setContentView(R.layout.activity_current_use);

        textLiter = findViewById(R.id.liter);
        textKm = findViewById(R.id.km);
        textLitersPerKm = findViewById(R.id.litersPerKm);
        textKmWithThisFuel = findViewById(R.id.kmWithThisFuel);

        LocalBroadcastManager.getInstance(this).registerReceiver(dataBluetooth.broadcastReceiver, new IntentFilter("bluetoothData"));

        requestData();
        dataBluetooth.setListener(new OnGetResult() {
            @Override
            public void onSuccess() {
                double liter = dataBluetooth.getCurrentVolumeInLiters(); //з OBD
                double alreadyKm = dataBluetooth.getAlreadyKm(); //з OBD
                double litersPerKm = dataBluetooth.getLitersPerKm(); //(літриДо - літриПісля)/кілометри з OBD
                double prediction = dataBluetooth.calculatePredictionOnKm(); //з DataBluetooth, зараз там все закомічено

                textLiter.setText(new DecimalFormat("##0.0 L").format(liter));
                textKm.setText(new DecimalFormat("##0.0# Km").format(alreadyKm));
                textLitersPerKm.setText(new DecimalFormat("#0.0# L/Km").format(litersPerKm));
                textKmWithThisFuel.setText(new DecimalFormat("##0 Km").format(prediction));

            }

            @Override
            public void onStart() {
                Log.i("--------", "onStart");
            }

            @Override
            public void onFailure() {
                Log.i("--------", "onFailure");
            }
        });
    }

    public void backToMain(View v) {
        finish();
    }

    public void requestData() {
        byte[] ACTION_GET_DATA = {2};
        BluetoothConnectionService.getInstance(CurrentUse.this).write(ACTION_GET_DATA);
    }

}
