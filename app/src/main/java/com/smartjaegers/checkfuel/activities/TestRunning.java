package com.smartjaegers.checkfuel.activities;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.smartjaegers.checkfuel.R;
import com.smartjaegers.checkfuel.bluetooth.BluetoothConnectionService;
import com.smartjaegers.checkfuel.models.ConnectionToBluetoothTest;
import com.smartjaegers.checkfuel.models.OnGetResult;

public class TestRunning extends AppCompatActivity {

    private Button button;
    private TextView infoView;
    private TextView loading;
    private ConnectionToBluetoothTest bluetoothTest;
    private long drivingTime;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_running);

        flag = true;
        bluetoothTest = new ConnectionToBluetoothTest();
        LocalBroadcastManager.getInstance(this).registerReceiver(bluetoothTest.broadcastReceiver, new IntentFilter("bluetoothData"));
        getDataFromBluetooth();

        button = findViewById(R.id.drive_back);
        infoView = findViewById(R.id.info_view);
        loading = findViewById(R.id.loading);

        requestData();
    }

    private void onDrivingKm() {
        button.setVisibility(View.VISIBLE);
        button.setClickable(true);
        infoView.setText(R.string.info_test_running_second);
        loading.setVisibility(View.INVISIBLE);
    }

    public void startDrivingBack(View view) {
        button.setClickable(false);
        button.setVisibility(View.INVISIBLE);
        infoView.setText(R.string.info_test_running_third);
        loading.setVisibility(View.VISIBLE);
        requestData();

    }

    private void onSuccessTest() {
        try {
            new SuccessTest().show(getSupportFragmentManager(), "success test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getDataFromBluetooth() {
        bluetoothTest.setListener(new OnGetResult() {
            @Override
            public void onSuccess() {
                Log.i("Speed data", "onSuccess: " + bluetoothTest.getListOfSpeed());

                if (flag) {
                    onDrivingKm();
                    flag = false;
                } else {
                    onSuccessTest();
                }
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFailure() {

            }
        });
    }

    private void requestData() {
        byte[] ACTION_GET_DATA = {4};
        BluetoothConnectionService.getInstance(this).write(ACTION_GET_DATA);
    }

    public void backToMain(View view) {
        finish();
    }
}
