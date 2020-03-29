package com.example.something;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.JsonReader;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SetVolume extends AppCompatActivity {
    private EditText numberEditText;
    private String volume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_volume);

        numberEditText = (EditText) findViewById(R.id.editText);
        numberEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        connectToServer();
    }

    public void backToMain(@NotNull View view) {
        if (view.getId() == R.id.btnback) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void goToVolumeCompare(@NotNull View view) {
        if (view.getId() == R.id.btncheckfuel) {

            Intent intent;

            if (Double.parseDouble(volume) >= Double.parseDouble(numberEditText.getText().toString())) {
                intent = new Intent(this, VolumeCompareGood.class);
            } else {
                intent = new Intent(this, VolumeCompareBad.class);
            }

            intent.putExtra("expectedVolume", numberEditText.getText().toString());
            intent.putExtra("realVolume", volume);

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
                            if (key.equals("volume")) {
                                volume = jsonReader.nextString();

                                break;
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


