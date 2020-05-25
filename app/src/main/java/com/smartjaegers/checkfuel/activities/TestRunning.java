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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class TestRunning extends AppCompatActivity {

    private static final SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd", Locale.ROOT);
    private Button button;
    private TextView infoView;
    private ConnectionToBluetoothTest bluetoothTest;
    private long drivingTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_running);

        bluetoothTest = new ConnectionToBluetoothTest();
        LocalBroadcastManager.getInstance(this).registerReceiver(bluetoothTest.broadcastReceiver, new IntentFilter("bluetoothData"));
        getDateCarDrivingForward();

        button = findViewById(R.id.drive_back);
        infoView = findViewById(R.id.info_view);

        requestData();
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

    }

    private void onSuccessTest() {

        new SuccessTest().show(getSupportFragmentManager(), "success test");
    }

    private void getDateCarDrivingForward() {
        bluetoothTest.setListener(new OnGetResult() {
            @Override
            public void onSuccess() {

                String drivingTimeFromBluetooth = "25.05.2020";//ConnectBluetooth
                try {
                    drivingTime = pattern.parse(drivingTimeFromBluetooth).getTime();
                    if (drivingTime == 3 * 60 * 1000) {
                        onDrivingKm();
                    }
                    if (drivingTime == 6.6 * 60 * 1000) {
                        onSuccessTest();
                    }
                    if (drivingTime != 7 * 60 * 1000) {
                        requestData();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    Log.i("----TestRunning----", "Failure to parse exception");
                }
                Log.i("----TestRunning----", "onSuccess");
            }

            @Override
            public void onStart() {
                Log.i("----TestRunning----", "onStart");
            }

            @Override
            public void onFailure() {
                Log.i("----TestRunning----", "onFailure");
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
