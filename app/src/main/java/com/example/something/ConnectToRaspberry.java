package com.example.something;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ConnectToRaspberry extends AsyncTask {
    public String volume, weight, temperature;

    @Override
    protected Object doInBackground(Object[] objects) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://192.168.43.87:5000")
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        JSONObject obj = null;
        try {
            obj = new JSONObject((String) o);

            volume = obj.getString("volume");
            weight = obj.getString("weight");
            temperature = obj.getString("temperature");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
