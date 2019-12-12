package com.example.something;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class SetVolume extends AppCompatActivity {
    EditText numberEditText;
    String nothing;
    String volume="10";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_volume2);
        numberEditText = (EditText) findViewById(R.id.editText);
        numberEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);


        Button button = findViewById(R.id.button);
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

                            volume = obj.getString("volume");
                        }   catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }.execute();


            }

        } );

    }

    public void backtomain(@NotNull View v) {
        switch (v.getId()) {
            case R.id.btnback:

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    public void gotovolumecompare(@NotNull View v) {
        switch (v.getId()) {
            case R.id.btncheckfuel:
                Intent intent = new Intent(this, VolumeCompare.class);

                intent.putExtra("volume", numberEditText.getText().toString());
                intent.putExtra("realvolume", volume);
                if (Double. parseDouble(volume)>=Double. parseDouble(numberEditText.getText().toString())){
                    intent = new Intent(this, VolumeCompareGood.class);
                    intent.putExtra("volume", numberEditText.getText().toString());
                }
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}


