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
    String volume = "10";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_volume);
        numberEditText = (EditText) findViewById(R.id.editText);
        numberEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);


        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectToRaspberry firsconnectio = new ConnectToRaspberry();
                firsconnectio.execute();
                volume=firsconnectio.volume;
            }
        });

    }

    public void backToMain(@NotNull View view) {
        if (view.getId() == R.id.btnback) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void goToVolumeCompare(@NotNull View view) {
        if (view.getId() == R.id.btncheckfuel) {
            Intent intent = new Intent(this, VolumeCompare.class);

            intent.putExtra("volume", numberEditText.getText().toString());
            intent.putExtra("realvolume", volume);
            if (Double.parseDouble(volume) >= Double.parseDouble(numberEditText.getText().toString())) {
                intent = new Intent(this, VolumeCompareGood.class);
                intent.putExtra("volume", numberEditText.getText().toString());
            }
            startActivity(intent);
        }
    }

}


