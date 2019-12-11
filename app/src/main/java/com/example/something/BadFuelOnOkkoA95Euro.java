package com.example.something;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BadFuelOnOkkoA95Euro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bad_fuel_on_okko_a95_euro);
        Intent intent = getIntent();
        String force= intent.getStringExtra("force");
        String volume= intent.getStringExtra("volume");
        TextView textforce = (TextView) findViewById(R.id.textView5);
        Double density=Double.parseDouble(force)/Double.parseDouble(volume);
        density=density*100;
        int i = (int) Math.round(density);
        textforce.setText( Integer.toString(i));
    }
    public void backToOkko(View v) {
        switch (v.getId()) {
            case R.id.btnback:
                Intent intent = new Intent(this, StationOkko.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
    public void backToMain(View v) {
        switch (v.getId()) {
            case R.id.btnhome:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
