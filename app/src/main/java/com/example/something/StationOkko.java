package com.example.something;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

public class StationOkko extends AppCompatActivity {
    TextView textvolume;
    String force;
    int i=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_okko);
        Intent intent1 = getIntent();
        force= intent1.getStringExtra("force");
        textvolume = (TextView) findViewById(R.id.textView3);
        textvolume.setText(force);

    }

    public void checkfuel(@NotNull View view) {
        switch (view.getId()) {
            case R.id.a95:
                if (i==1) {Intent intent = new Intent(this,GoodFuelOnOkkoA95Euro.class);
                startActivity(intent);}
                else {Intent intent = new Intent(this,BadFuelOnOkkoA95Euro.class);
                    startActivity(intent);}
                break;
            default:
                break;

        }
    }

    public void backtostations(@NotNull View v) {
        switch (v.getId()) {
            case R.id.btnback:
                Intent intent = new Intent(this, ChooseTheGasSation.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
