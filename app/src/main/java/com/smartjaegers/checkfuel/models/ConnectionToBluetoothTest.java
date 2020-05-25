package com.smartjaegers.checkfuel.models;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;


public class ConnectionToBluetoothTest {

    private OnGetResult listener;
    private List<Double> listOfSpeed;
    private List<Double> listOfRpm;
    private List<Double> listOfFuelFlowRate;

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("theMessage");
            if (message.equals("Done")) {
                listener.onSuccess();
                return;
            }
            String[] data = message.split(" ");
            try {
                listOfSpeed.add(Double.parseDouble(data[0]));
                listOfFuelFlowRate.add(Double.parseDouble(data[1]));
                listOfRpm.add(Double.parseDouble(data[2]));
                Log.d("---DataBluetooth: ", "broadcastReceiver: onReceive: ");
            } catch (Exception e) {
                e.printStackTrace();
                listener.onFailure();
            }
        }
    };

    public void setListener(OnGetResult listener) {
        this.listener = listener;
        this.listener.onStart();
    }

    public List<Double> getListOfSpeed() {
        return listOfSpeed;
    }

    public List<Double> getListOfRpm() {
        return listOfRpm;
    }

    public List<Double> getListOfFuelFlowRate() {
        return listOfFuelFlowRate;
    }
}
