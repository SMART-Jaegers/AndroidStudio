package com.smartjaegers.checkfuel.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.smartjaegers.checkfuel.R;

import org.jetbrains.annotations.NotNull;

public class SetVolume extends AppCompatActivity {
    private EditText numberEditText;

    private double volumeFill;
    BroadcastReceiver broadcastReceiver2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String text = intent.getStringExtra("theMessage");
            try {
                Log.i("----Bluetooth----", text);
                volumeFill = Double.parseDouble(text);
                Log.i("------Bluetooth------", "" + volumeFill);
            } catch (Exception e) {
                volumeFill = -10;
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_volume);

        numberEditText = findViewById(R.id.editText);
        numberEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver2, new IntentFilter("incomingMessage"));
    }

    public void goToVolumeCompare(@NotNull View view) {
        if (volumeFill <= 0) {
            Toast.makeText(this, "No connection", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.i("--goToVolumeCompare--", "" + volumeFill);
        try {
            double volumeExpected = Double.parseDouble(numberEditText.getText().toString());
            if (view.getId() == R.id.btncheckfuel) {
                Intent intent = new Intent();


                if (volumeFill >= volumeExpected) {
                    intent.setClass(this, VolumeCompareGood.class);
                } else {
                    intent.setClass(this, VolumeCompareBad.class);

                }
                intent.putExtra("expectedVolume", volumeExpected);
                intent.putExtra("realVolume", volumeFill);
                startActivity(intent);
            }
        } catch (Exception e) {
            Toast.makeText(this, "You write wrong number", Toast.LENGTH_SHORT).show();
        }
    }

    public void backToMain(@NotNull View view) {
        finish();
    }

}


