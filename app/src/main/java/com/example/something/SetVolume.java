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
import java.net.MalformedURLException;
import java.net.URL;

public class SetVolume extends AppCompatActivity {
    EditText numberEditText;
    String nothing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_volume2);
        numberEditText = (EditText) findViewById(R.id.editText);
        numberEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

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
                intent.putExtra("realvolume", nothing);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}


