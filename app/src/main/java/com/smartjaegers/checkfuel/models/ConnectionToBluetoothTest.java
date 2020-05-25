package com.smartjaegers.checkfuel.models;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ConnectionToBluetoothTest {

    private OnGetResult listener;

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("theMessage");
            String[] data = message.split(" ");
            try {

                listener.onSuccess();
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


}
