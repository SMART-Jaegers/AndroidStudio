package com.smartjaegers.checkfuel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.smartjaegers.checkfuel.R;


public class ChooseGasStation extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_gas_station);

        ImageView okko = findViewById(R.id.okko);
        ImageView wog = findViewById(R.id.wog);
        FrameLayout ukrNafta = findViewById(R.id.ukrNafta);
        LinearLayout shell = findViewById(R.id.shell);
        FrameLayout socar = findViewById(R.id.socar);
        FrameLayout upg = findViewById(R.id.upg);

        okko.setOnClickListener(this);
        wog.setOnClickListener(this);
        ukrNafta.setOnClickListener(this);
        shell.setOnClickListener(this);
        socar.setOnClickListener(this);
        upg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String nameStation = null;

        switch (v.getId()) {
            case R.id.okko:
                nameStation = "Okko";
                break;
            case R.id.wog:
                nameStation = "Wog";
                break;
            case R.id.ukrNafta:
                nameStation = "UkrNafta";
                break;
            case R.id.shell:
                nameStation = "Shell";
                break;
            case R.id.socar:
                nameStation = "Socar";
                break;
            case R.id.upg:
                nameStation = "UPG";
                break;
            default:
                Toast.makeText(this, "this gas Station don't exist", Toast.LENGTH_SHORT).show();
                //ToDo remove this and do some another exeption
        }

        Intent intent = new Intent(this, ChooseFuelType.class);
        intent.putExtra("nameStation", nameStation);
        startActivity(intent);
    }

    public void backToMain(View v) {
        finish();
    }

}
