package com.checkfuel.utils;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ConnectToServer {
    private static String volume;
    private static String temperature;
    private static String weight;

    public static void connect() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL serverEndpoint = new URL("http://192.168.43.109:8080/data/1");
                    HttpURLConnection myConnection = (HttpURLConnection) serverEndpoint.openConnection();
                    myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
                    if (myConnection.getResponseCode() == 200) {
                        InputStream responseBody = myConnection.getInputStream();
                        InputStreamReader responseBodyReader = new InputStreamReader(responseBody, StandardCharsets.UTF_8);
                        JsonReader jsonReader = new JsonReader(responseBodyReader);
                        jsonReader.beginObject();
                        while (jsonReader.hasNext()) {
                            String key = jsonReader.nextName();
                            switch (key) {
                                case "volume":
                                    volume = jsonReader.nextString();
                                    break;
                                case "temperature":
                                    temperature = jsonReader.nextString();
                                    break;
                                case "weight":
                                    weight = jsonReader.nextString();
                                    break;
                                default:
                                    jsonReader.skipValue();
                                    break;
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

    public static String getVolume() {
        return volume;
    }

    public static String getTemperature() {
        return temperature;
    }

    public static String getWeight() {
        return weight;
    }
}
