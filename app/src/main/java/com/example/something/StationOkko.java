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
    String weight="1";
    String volume="1";
    String temperature="1";
    RelativeLayout button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_okko);
        button= (RelativeLayout) findViewById(R.id.a95);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
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

                            weight = obj.getString("weight");
                            temperature = obj.getString("temperature");
                            volume = obj.getString("volume");
                            if (Double.parseDouble(weight)/Double.parseDouble(volume)<0.8) {
                                Intent intent = new Intent(StationOkko.this,GoodFuelOnOkkoA95Euro.class);
                                intent.putExtra("weight", weight);
                                intent.putExtra("temperature", weight);
                                intent.putExtra("volume", volume);
                                startActivity(intent);}
                            else {Intent intent = new Intent(StationOkko.this,BadFuelOnOkkoA95Euro.class);
                                intent.putExtra("weight", weight);
                                intent.putExtra("temperature", temperature);
                                intent.putExtra("volume", volume);
                                startActivity(intent);}

                        }   catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }.execute();


            }

        } );

    }


  /*  public void checkfuel(@NotNull View view) {
                if (Double.parseDouble(weight)/Double.parseDouble(volume)<0.8) {
                    Intent intent = new Intent(this,GoodFuelOnOkkoA95Euro.class);
                    intent.putExtra("weight", weight);
                    intent.putExtra("volume", volume);
                startActivity(intent);}
                else {Intent intent = new Intent(this,BadFuelOnOkkoA95Euro.class);
                    intent.putExtra("weight", weight);
                    intent.putExtra("volume", volume);
                    startActivity(intent);}

    }*/

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
