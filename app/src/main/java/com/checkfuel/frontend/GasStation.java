package com.checkfuel.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.checkfuel.models.Post;
import com.checkfuel.something.R;
import com.checkfuel.utils.DatabaseManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GasStation extends AppCompatActivity implements View.OnClickListener {
    private TableRow row1, row2, row3, row4, row5, row6;
    private TextView nameStation, textView1, textView2, textView3, textView4, textView5, textView6;
    private ArrayList<String> fuelTypes = new ArrayList<>();
    private ArrayList<String> fuelDensities = new ArrayList<>();
    private int minDensity, maxDensity;
    private String fuelType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_station);

        Intent intent = getIntent();
        fuelTypes = intent.getStringArrayListExtra("fuelTypes");
        fuelDensities = intent.getStringArrayListExtra("fuelDensities");

        row1 = findViewById(R.id.row1);
        row2 = findViewById(R.id.row2);
        row3 = findViewById(R.id.row3);
        row4 = findViewById(R.id.row4);
        row5 = findViewById(R.id.row5);
        row6 = findViewById(R.id.row6);

        nameStation = findViewById(R.id.nameStation);

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
        if (fuelTypes.size() >= 1) {
            nameStation.setText(fuelTypes.get(0));
        }
        if (fuelTypes.size() >= 2) {
            textView1.setText(fuelTypes.get(1));
        } else {
            row1.setVisibility(View.INVISIBLE);
            row1.setClickable(false);
        }
        if (fuelTypes.size() >= 3) {
            textView2.setText(fuelTypes.get(2));
        } else {
            row2.setVisibility(View.INVISIBLE);
            row2.setClickable(false);
        }
        if (fuelTypes.size() >= 4) {
            textView3.setText(fuelTypes.get(3));
        } else {
            row3.setVisibility(View.INVISIBLE);
            row3.setClickable(false);
        }
        if (fuelTypes.size() >= 5) {
            textView4.setText(fuelTypes.get(4));
        } else {
            row4.setVisibility(View.INVISIBLE);
            row4.setClickable(false);
        }
        if (fuelTypes.size() >= 6) {
            textView5.setText(fuelTypes.get(5));
        } else {
            row5.setVisibility(View.INVISIBLE);
            row5.setClickable(false);
        }
        if (fuelTypes.size() >= 7) {
            textView6.setText(fuelTypes.get(6));
        } else {
            row6.setVisibility(View.INVISIBLE);
            row6.setClickable(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.row1:
                maxDensity = Integer.parseInt(fuelDensities.get(0));
                minDensity = Integer.parseInt(fuelDensities.get(1));
                fuelType = fuelTypes.get(1);
                break;
            case R.id.row2:
                maxDensity = Integer.parseInt(fuelDensities.get(2));
                minDensity = Integer.parseInt(fuelDensities.get(3));
                fuelType = fuelTypes.get(2);
                break;
            case R.id.row3:
                maxDensity = Integer.parseInt(fuelDensities.get(4));                minDensity = Integer.parseInt(fuelDensities.get(5));
                fuelType = fuelTypes.get(3);
                break;
            case R.id.row4:
                maxDensity = Integer.parseInt(fuelDensities.get(6));
                minDensity = Integer.parseInt(fuelDensities.get(7));
                fuelType = fuelTypes.get(4);
                break;
            case R.id.row5:
                maxDensity = Integer.parseInt(fuelDensities.get(8));
                minDensity = Integer.parseInt(fuelDensities.get(9));
                fuelType = fuelTypes.get(5);
                break;
            case R.id.row6:
                maxDensity = Integer.parseInt(fuelDensities.get(10));
                minDensity = Integer.parseInt(fuelDensities.get(11));
                fuelType = fuelTypes.get(6);
                break;
            default:
                Toast.makeText(this, "wrong type of Fuel", Toast.LENGTH_SHORT).show();
        }

        doCompare();
    }

    public void doCompare() {
        Post post = DatabaseManager.getPost();
        double weight;
        double realVolume;
        double temperature;
        if (post == null) {
            Toast toast = Toast.makeText(this, "Failed to connect to database, \n please check the internet connection or sign in in your user account", Toast.LENGTH_SHORT);
            toast.show();
            weight = 1;
            realVolume = 1;
            temperature = 0;
        } else {
            weight = post.getWeight();
            realVolume = post.getVolumeFill();
            temperature = post.getTemperature();
        }

        double density = 1000 * (weight / realVolume) / (1 + temperature * 0.001);

        Intent intent = new Intent();

        if (density <= maxDensity && density >= minDensity) {
            intent.setClass(GasStation.this, GoodFuelOnOkkoA95Euro.class);
            //TODO check: Do will working only this
        } else {
            intent.setClass(GasStation.this, BadFuelOnOkkoA95Euro.class);
        }
        intent.putExtra("density", density);
        intent.putExtra("minDensity", minDensity);
        intent.putExtra("maxDensity", maxDensity);
        intent.putExtra("nameStation", fuelTypes.get(0));
        intent.putExtra("fuelType", fuelType);
        startActivity(intent);
    }


    public void backToStations(@NotNull View view) {
        Intent intent = new Intent(this, ChooseTheGasStation.class);
        startActivity(intent);
    }

}