package com.example.something;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class StationOkko extends AppCompatActivity {
    String weight;
    String volume;
    String temperature;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_okko);

        connectToServer();
    }

    public void doCompare(View view) {
        if (Double.parseDouble(weight) / Double.parseDouble(volume) < 0.8) {
            Intent intent = new Intent(StationOkko.this, GoodFuelOnOkkoA95Euro.class);
            intent.putExtra("weight", weight);
            intent.putExtra("temperature", temperature);
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

    public void backToStations(@NotNull View view) {
        if (view.getId() == R.id.btnback) {
            Intent intent = new Intent(this, ChooseTheGasStation.class);
            startActivity(intent);
        }

    }

    public void connectToServer() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL serverEndpoint = new URL("http://192.168.25.106:8080/data/1");
                    HttpURLConnection myConnection = (HttpURLConnection) serverEndpoint.openConnection();
                    myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
                    if (myConnection.getResponseCode() == 200) {
                        InputStream responseBody = myConnection.getInputStream();
                        InputStreamReader responseBodyReader = new InputStreamReader(responseBody, StandardCharsets.UTF_8);
                        JsonReader jsonReader = new JsonReader(responseBodyReader);
                        jsonReader.beginObject();
                        while (jsonReader.hasNext()) {
                            String key = jsonReader.nextName();
                            if (key.equals("volume")) {                         //TODO find easiest way
                                volume = jsonReader.nextString();
                            } else if (key.equals("temperature")) {
                                temperature = jsonReader.nextString();
                            } else if (key.equals("weight")) {
                                weight = jsonReader.nextString();
                            } else {
                                jsonReader.skipValue();
                            }
                        }
                        jsonReader.close();
                        myConnection.disconnect();
                    } else {
                        //ToDO   myConnection.getResponseCode();
                    }
                } catch (IOException e) {
                    //TODO message connection isn't install
                    e.printStackTrace();
                }
            }
        });
    }
}
