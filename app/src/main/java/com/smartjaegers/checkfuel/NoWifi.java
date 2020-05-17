package com.smartjaegers.checkfuel;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartjaegers.checkfuel.R;


public class NoWifi extends DialogFragment implements View.OnClickListener {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_no_wifi, null);

        view.findViewById(R.id.turnOnWifi).setOnClickListener(this);
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