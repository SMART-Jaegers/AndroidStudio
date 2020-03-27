package com.example.something;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class StationOkko extends AppCompatActivity {
    String weight = "1";
    String volume = "1";
    String temperature = "1";
    RelativeLayout button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_okko);
        button = (RelativeLayout) findViewById(R.id.a95);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectToRaspberry firsconnectio = new ConnectToRaspberry();
                firsconnectio.execute();
                volume = firsconnectio.volume;

                if (Double.parseDouble(weight) / Double.parseDouble(volume) < 0.8) {
                    Intent intent = new Intent(StationOkko.this, GoodFuelOnOkkoA95Euro.class);
                    intent.putExtra("weight", weight);
                    intent.putExtra("temperature", weight);
                    intent.putExtra("volume", volume);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(StationOkko.this, BadFuelOnOkkoA95Euro.class);
                    intent.putExtra("weight", weight);
                    intent.putExtra("temperature", temperature);
                    intent.putExtra("volume", volume);
                    startActivity(intent);
                }
            }

        });

    }

    public void backToStations(@NotNull View view) {
        if (view.getId() == R.id.btnback) {
            Intent intent = new Intent(this, ChooseTheGasStation.class);
            startActivity(intent);
        }

    }
}
