package com.SmartJeagers.CheckFuel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.checkfuel.something.R;

public class NoBluetooth extends DialogFragment implements View.OnClickListener {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_no_bluetooth, null);

        view.findViewById(R.id.turnOnBluetooth).setOnClickListener(this);
        view.findViewById(R.id.ignore).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.turnOnWifi:
                Log.i("---------------", "turn on");
                //TODO turn on Wifi
                dismiss();
                break;
            case R.id.ignore:
                Log.i("---------------", "ignore");
                dismiss();
                break;
        }
    }


    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
    }

}