package com.SmartJeagers.CheckFuel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import com.checkfuel.something.R;
import com.SmartJeagers.CheckFuel.utils.TextReader;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ChooseFuelType extends AppCompatActivity implements View.OnClickListener {
    private TableRow row1, row2, row3, row4, row5, row6;
    private TextView textVievNameStation, textView1, textView2, textView3, textView4, textView5, textView6;
    private ArrayList<String> fuelTypes = new ArrayList<>();
    private ArrayList<String> fuelDensities = new ArrayList<>();
    private int minDensity, maxDensity;
    private String nameStation;
    private Intent goToQualityResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_fuel_type);

        goToQualityResult = new Intent();

        Intent intent = getIntent();
        nameStation = intent.getStringExtra("nameStation");

        TextReader reader = new TextReader(this);
        fuelTypes = reader.getFuelTypes(nameStation);
        fuelDensities = reader.getFuelDensities(nameStation);

        row1 = findViewById(R.id.row1);
        row2 = findViewById(R.id.row2);
        row3 = findViewById(R.id.row3);
        row4 = findViewById(R.id.row4);
        row5 = findViewById(R.id.row5);
        row6 = findViewById(R.id.row6);

        textVievNameStation = findViewById(R.id.nameStation);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.Density);
        textView6 = findViewById(R.id.textView6);

        row1.setOnClickListener(this);
        row2.setOnClickListener(this);
        row3.setOnClickListener(this);
        row4.setOnClickListener(this);
        row5.setOnClickListener(this);
        row6.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        textVievNameStation.setText(nameStation);

        if (fuelTypes.size() >= 1) {
            textView1.setText(fuelTypes.get(0));
        } else {
            row1.setVisibility(View.INVISIBLE);
            row1.setClickable(false);
        }
        if (fuelTypes.size() >= 2) {
            textView2.setText(fuelTypes.get(1));
        } else {
            row2.setVisibility(View.INVISIBLE);
            row2.setClickable(false);
        }
        if (fuelTypes.size() >= 3) {
            textView3.setText(fuelTypes.get(2));
        } else {
            row3.setVisibility(View.INVISIBLE);
            row3.setClickable(false);
        }
        if (fuelTypes.size() >= 4) {
            textView4.setText(fuelTypes.get(3));
        } else {
            row4.setVisibility(View.INVISIBLE);
            row4.setClickable(false);
        }
        if (fuelTypes.size() >= 5) {
            textView5.setText(fuelTypes.get(4));
        } else {
            row5.setVisibility(View.INVISIBLE);
            row5.setClickable(false);
        }
        if (fuelTypes.size() >= 6) {
            textView6.setText(fuelTypes.get(5));
        } else {
            row6.setVisibility(View.INVISIBLE);
            row6.setClickable(false);
        }
    }

    @Override
    public void onClick(View v) {
        int fuelTypeNumber;

        switch (v.getId()) {
            case R.id.row1:
                fuelTypeNumber = 0;
                break;
            case R.id.row2:
                fuelTypeNumber = 1;
                break;
            case R.id.row3:
                fuelTypeNumber = 2;
                break;
            case R.id.row4:
                fuelTypeNumber = 3;
                break;
            case R.id.row5:
                fuelTypeNumber = 4;
                break;
            case R.id.row6:
                fuelTypeNumber = 5;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }

        maxDensity = Integer.parseInt(fuelDensities.get(fuelTypeNumber * 2));
        minDensity = Integer.parseInt(fuelDensities.get(fuelTypeNumber * 2 + 1));
        goToQualityResult.putExtra("minDensity", minDensity);
        goToQualityResult.putExtra("maxDensity", maxDensity);
        goToQualityResult.putExtra("nameStation", nameStation);
        goToQualityResult.putExtra("fuelType", fuelTypes.get(fuelTypeNumber));

        doCompare();
    }

    private void doCompare() {

        double weight = 0.5;
        double realVolume = 1;//TODO (hardcode), take date with bluetooth and create exeption when can't connect
        double temperature = 0;

        double density = calculateDensity(weight, realVolume, temperature);
        goToQualityResult.putExtra("density", density);

        if (density <= maxDensity && density >= minDensity) {
            goToQualityResult.setClass(this, GoodFuel.class);
        } else {
            goToQualityResult.setClass(this, BadFuel.class);
        }

        startActivity(goToQualityResult);
    }

    private double calculateDensity(double weight, double realVolume, double temperature) {
        return 1000 * (weight / realVolume) / (1 + temperature * 0.001);
    }

    public void backToStations(@NotNull View view) {
        finish();
    }

}