package com.example.something;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class StationOkko extends AppCompatActivity {
    TextView textvolume;
    String temp2="3";
    String temp1="0.5";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_okko);
        textvolume = (TextView) findViewById(R.id.textView3);

    }

    public void checkfuel(@NotNull View view) {
        switch (view.getId()) {
            case R.id.a95:
                AsyncTask asyncTask = new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] objects) {
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url("http://192.168.43.87:5000")
                                .build();

                        Response response = null;
                        try{
                            response = client.newCall(request).execute();
                            return response.body().string();

                        } catch (IOException e){
                            e.printStackTrace();
                        }
                        return null;
                    }
                    @Override
                    protected void onPostExecute(Object o){

                        JSONObject obj = null;
                        try {
                            obj = new JSONObject((String) o);


                            temp2 = obj.getString("temp2");
                            temp1 = obj.getString("temp1");
                            textvolume.setText(temp2);
                        }   catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }.execute();

                if (Double.parseDouble(temp2)/Double.parseDouble(temp1)<8) {Intent intent = new Intent(this,GoodFuelOnOkkoA95Euro.class);
                    intent.putExtra("force", temp2);
                    intent.putExtra("volume", temp1);
                startActivity(intent);}
                else {Intent intent = new Intent(this,BadFuelOnOkkoA95Euro.class);
                    intent.putExtra("force", temp2);
                    intent.putExtra("volume", temp1);
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
