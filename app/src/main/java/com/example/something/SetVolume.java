package com.example.something;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import org.json.*;

public class SetVolume extends AppCompatActivity {
    EditText numberEditText;
    String nothing;
    String temp1;
    TextView textRPi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_volume2);
        numberEditText = (EditText) findViewById(R.id.editText);
        numberEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        textRPi = (TextView)findViewById(R.id.textView);

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
                        textRPi.setText(o.toString());

                        JSONObject obj = null;
                        try {
                            obj = new JSONObject((String) o);

                         temp1 = obj.getString("temp1");
                        String temp2 = obj.getString("temp2");
                        textRPi.setText("\n"+temp1+temp2);
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
                intent.putExtra("realvolume", temp1);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}


